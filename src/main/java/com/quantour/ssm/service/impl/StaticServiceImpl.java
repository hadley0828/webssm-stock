package com.quantour.ssm.service.impl;

import com.quantour.ssm.dto.*;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.StockBasicInfo;
import com.quantour.ssm.service.StaticService;
import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.StockCalculator;
import com.quantour.ssm.util.StockChangeHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.calendar.LocalGregorianCalendar;

import javax.annotation.Resource;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


/**
 * Created by zhangzy on 2017/5/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StaticServiceImpl implements StaticService {

    @Resource
    private DayKLineMapper dayklinemapper;

    @Override
    public strategyResultDTO getStraOneResult(int formDays, int holdDays, String sDate, String lDate, int type, ArrayList<String> codeList, String blockCode) {
        strategyResultDTO strategyResultdto=new strategyResultDTO();
        strategyResultdto.setStraId("1");
        ArrayList<oneDayProfitDTO> oneDayProfitList=new ArrayList<oneDayProfitDTO>();

        //c图的数据
        indexProfitDTO indexprofitdto=new indexProfitDTO();
        int plusCycles=0;
        int minusCycles=0;
        double winRate=0.0;
        HashMap<Double,Integer> cycleChangeMap=new HashMap<Double, Integer>();//Double表示涨跌幅 int表示数量

        //当前的本金率 用来计算累计收益率
        double currentStraCapital=1.0;
        double currentStandardCapital=1.0;

        ArrayList<String> stockCodeList=new ArrayList<String>(); //选择的股票池 没有根据日期做筛选

        if(type==1){
            ArrayList<StockBasicInfo> allStockInfoList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
            for(int count=0;count<allStockInfoList.size();count++){
                stockCodeList.add(allStockInfoList.get(count).getCode());
            }
        }else if(type==2){
            String plateCode=codeList.get(0);

            if(plateCode.equals("sh000016")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeBySz50Block();
            }else if(plateCode.equals("sh000300")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeByHs300Block();
            }else if(plateCode.equals("sh000905")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeByZz500Block();
            }else if(plateCode.equals("sz399005")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeBySmeBlock();
            }else if(plateCode.equals("sz399006")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeByGemBlock();
            }


        }else if(type==3){
//            //自定义股票池中的股票数量需要大于等于100
//            if(codeList.size()>100||codeList.size()==100){
//                stockCodeList=codeList;
//            }else{
//                //跳出提示框提示自选股票数量不足100
//            }

            for(int count=0;count<codeList.size();count++){
                stockCodeList.add(codeList.get(count).split("  ")[0]);
            }
        }else{
            //跳出提示框 提示传入的参数不对
        }





        //获得一段时间的所有股票的信息  开始日期的前formdays-结束日期
        //allStockMap

        //获得这段时间的版块的全部信息
        HashMap<String,DayKLine> blockMap=new HashMap<String, DayKLine>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realSDate=DateConvert.getRealStartDate(sDate,allDateList);
        String realLDate=DateConvert.getRealEndDate(lDate,allDateList);

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("block", blockCode);
        map.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realSDate,14)));
        map.put("end",Date.valueOf(realLDate));

        ArrayList<DayKLine> blockList= (ArrayList<DayKLine>) dayklinemapper.getTimesBlockInfo(map);


//        ArrayList<BlockPO> blockList=new ArrayList<BlockPO>();
//        try {
//            blockList=staticDataService.getTimesBlockInfo(blockCode,getMarketLastDate(sDate,allDateList,formDays),lDate);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
        //blockList中的时间格式是XXXX-XX-XX


        for(int count=0;count<blockList.size();count++){

            blockMap.put(DateConvert.dateToString(blockList.get(count).getStockDate()),blockList.get(count));
        }

        String currentDate=realSDate;
        String changeDate=realSDate;  //当天需要改变持有股票的日期
        //对currentDate进行循环 需要每天循环 每隔持有期的日期 需要改变持有的股票池



        double currentStraProfit=1.0;  //当天的策略累计收益率
        double currentStandardProfit=1.0;   //当天的基准累计收益率
        int number=0; //持有股票的支数
        ArrayList<Double> straProfitList=new ArrayList<Double>();  //每天的策略收益率的列表
        ArrayList<Double> standardProfitList=new ArrayList<Double>(); //每天的基准收益率的列表
        ArrayList<String> holdStockCodeList=new ArrayList<String>(); //持有期内持有的股票编号的列表
        ArrayList<Double> currentStraProfitList=new ArrayList<Double>(); //每天策略的累计收益率列表
        double cycleChangePercent=0.0; //在整个上个持有期的收益率

        for(int count=allDateList.indexOf(realSDate);count<=allDateList.indexOf(realLDate);count++){
            currentDate=allDateList.get(count);

            ArrayList<String> validCodeList=new ArrayList<String>(); //用来存储当天有信息的所有股票编号
            ArrayList<DayKLine> currentStockList=new ArrayList<DayKLine>(); //当天的有信息的股票列表
            ArrayList<DayKLine> yesterdayStockList=new ArrayList<DayKLine>(); //前一个交易日的有信息的股票列表
            ArrayList<DayKLine> beforeFormdaysStockList=new ArrayList<DayKLine>(); //形成期之前的有信息的股票列表

            currentStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(currentDate));

            HashMap<String,DayKLine> currentStockMap=new HashMap<String,DayKLine>();
            for(int index=0;index<currentStockList.size();index++){
                currentStockMap.put(currentStockList.get(index).getStockCode(),currentStockList.get(index));
            }

            yesterdayStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(DateConvert.getLastNDate(allDateList,currentDate,1)));
            HashMap<String,DayKLine> yesterdayStockMap=new HashMap<String,DayKLine>();
            for(int index=0;index<yesterdayStockList.size();index++){
                yesterdayStockMap.put(yesterdayStockList.get(index).getStockCode(),yesterdayStockList.get(index));
            }



            for(int index=0;index<stockCodeList.size();index++){
                String stockCode=stockCodeList.get(index);
                if(currentStockMap.containsKey(stockCode)){
                    validCodeList.add(stockCode);  //当天可供选择的股票列表
                }

            }

            beforeFormdaysStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(DateConvert.getLastNDate(allDateList,currentDate,formDays)));

            if(currentDate.equals(changeDate)){
                HashMap<String,DayKLine> beforeFormdaysStockMap=new HashMap<String,DayKLine>();
                for(int index=0;index<beforeFormdaysStockList.size();index++){
                    beforeFormdaysStockMap.put(beforeFormdaysStockList.get(index).getStockCode(),beforeFormdaysStockList.get(index));
                }

                //进行持有股票池的重新选择
                double stockSize=validCodeList.size();

                number=(new Double(Math.ceil(stockSize/5.0)).intValue());



                ArrayList<waveDTO> topNList=new ArrayList<waveDTO>();
                topNList=getTopNCodesByHashmap(number,beforeFormdaysStockMap,currentStockMap,validCodeList);



                holdStockCodeList.clear();

                for(int index=0;index<topNList.size();index++){
                    holdStockCodeList.add(topNList.get(index).getStockCode());
                }


                //有可能有问题
                if(allDateList.indexOf(lDate)-allDateList.indexOf(currentDate)>holdDays-1){
                    changeDate=DateConvert.getLastNDate(allDateList,currentDate,-holdDays);
                }

                if(cycleChangePercent>0.0){
                    plusCycles++;
                    double ToDownPercent=Math.floor(100*cycleChangePercent)/100;
                    if(cycleChangeMap.containsKey(ToDownPercent)){
                        int oldNumber=cycleChangeMap.get(ToDownPercent);
                       cycleChangeMap.replace(ToDownPercent, oldNumber + 1);//??????有可能有问题
                    }else{
                        cycleChangeMap.put(ToDownPercent,1);
                    }
                }else if(cycleChangePercent<0.0){
                    minusCycles++;
                    double ToUpPercent=Math.ceil(100*cycleChangePercent)/100;
                    if(cycleChangeMap.containsKey(ToUpPercent)){
                        int oldNumer=cycleChangeMap.get(ToUpPercent);
                        cycleChangeMap.replace(ToUpPercent,oldNumer+1);
                    }else{
                        cycleChangeMap.put(ToUpPercent,1);
                    }
                }

                currentStraCapital=currentStraProfit;
                currentStandardCapital=currentStandardProfit;

                cycleChangePercent=0.0;
            }

            double standardUp=0.0;
            if(type==3){

                for(int index=0;index<validCodeList.size();index++){
                    String stockCode=validCodeList.get(index);
                    if(currentStockMap.containsKey(stockCode)&&yesterdayStockMap.containsKey(stockCode)){
                        standardUp=standardUp+StockCalculator.getIncrease(yesterdayStockMap.get(stockCode).getClosePrice(),currentStockMap.get(stockCode).getClosePrice());
                    }
                }

                standardUp=standardUp/validCodeList.size();
            }else{
                System.out.println(currentDate);
                System.out.println(DateConvert.getLastNDate(allDateList,currentDate,1));


                DayKLine nowStockPo=blockMap.get(currentDate);
                System.out.println(nowStockPo.getClosePrice());


                System.out.println(blockMap.containsKey(currentDate));
                System.out.println(blockMap.containsKey(DateConvert.getLastNDate(allDateList,currentDate,1)));


                DayKLine yesterdayBlockPo=blockMap.get(DateConvert.getLastNDate(allDateList,currentDate,1));
                System.out.println(yesterdayBlockPo.getClosePrice());

                standardUp=StockCalculator.getIncrease(yesterdayBlockPo.getClosePrice(),nowStockPo.getClosePrice());

            }

            currentStandardProfit=currentStandardProfit+currentStandardCapital*standardUp;

            standardProfitList.add(standardUp);


            double totalUp=0.0;
            for(int index=0;index<holdStockCodeList.size();index++){
                String code=holdStockCodeList.get(index);
                if(currentStockMap.containsKey(code)&&yesterdayStockMap.containsKey(code)){
                    double nowPrice=currentStockMap.get(code).getClosePrice();
                    double lastPrice=yesterdayStockMap.get(code).getClosePrice();
                    totalUp=totalUp+StockCalculator.getIncrease(lastPrice,nowPrice);
                }
            }
            currentStraProfit=currentStraProfit+currentStraCapital*(totalUp/number);
            straProfitList.add(totalUp/number);
            currentStraProfitList.add(currentStraProfit);




            cycleChangePercent=cycleChangePercent+totalUp/number;


            oneDayProfitDTO onedayprofitvo=new oneDayProfitDTO();
            onedayprofitvo.setDate(currentDate);
            onedayprofitvo.setStraProfit(currentStraProfit-1.0);
            onedayprofitvo.setStandardProfit(currentStandardProfit-1.0);

            oneDayProfitList.add(onedayprofitvo);


        }

        //统计环节
        //分别计算 年化收益率 基准年化收益率 阿尔法 贝塔 夏普比率 收益波动率 信息比率 最大回撤 换手率
        //        最终的基准收益率currentStandardProfit;    每一天的基准收益率列表standardProfitList;
        //        最终的策略收益率currentStraProfit;        每一天的策略收益率列表straProfitList;
        //        每天的累计收益率列表 currentStraProfitList
        int totalCycles=plusCycles+minusCycles;
        double plusTimes=plusCycles;
        double totalTimes=totalCycles;
        winRate=plusTimes/totalTimes;

        double straYearlyBenifit;
        double standardYearlyBenifit;
        double alpha;
        double beta;
        double sharpRate;
        double profitWaveRate;
        double infoRate;
        double maxBack;
        double turnOverRate;

        straYearlyBenifit=((currentStraProfit-1.0)/straProfitList.size())*250;
        standardYearlyBenifit=((currentStandardProfit-1.0)/standardProfitList.size())*250;

        beta=StockCalculator.getCovariance(straProfitList,standardProfitList)/StockCalculator.getVariance(standardProfitList);
        alpha=straYearlyBenifit-StockCalculator.getOneYearRate()-beta*(standardYearlyBenifit-StockCalculator.getOneYearRate());
        profitWaveRate=Math.sqrt(StockCalculator.getVariance(straProfitList))*Math.sqrt(straProfitList.size());
        sharpRate=(straYearlyBenifit-StockCalculator.getOneYearRate())/profitWaveRate;

        ArrayList<Double> minusList=new ArrayList<Double>();
        for(int count=0;count<straProfitList.size();count++){
            minusList.add(straProfitList.get(count)-standardProfitList.get(count));
        }
        infoRate=(currentStraProfit-currentStandardProfit)/(Math.sqrt(StockCalculator.getVariance(minusList)));

        ArrayList<Double> allBackList=new ArrayList<Double>();


        double maxProfit=currentStraProfitList.get(0);
        for(int count=1;count<currentStraProfitList.size();count++){
            if(currentStraProfitList.get(count)>maxProfit){
                maxProfit=currentStraProfitList.get(count);
            }
            allBackList.add((maxProfit-currentStraProfitList.get(count))/(1+maxProfit));
        }

        maxBack= Collections.max(allBackList);

        strategyResultdto.setYearProfit(straYearlyBenifit);
        strategyResultdto.setStandardProfit(standardYearlyBenifit);
        strategyResultdto.setAlpha(alpha);
        strategyResultdto.setBeta(beta);
        strategyResultdto.setSharpRate(sharpRate);
        strategyResultdto.setProfitWaveRate(profitWaveRate);
        strategyResultdto.setInfoPercent(infoRate);
        strategyResultdto.setMaxBack(maxBack);
        strategyResultdto.setDaysProfitList(oneDayProfitList);

        indexprofitdto.setPlusCycles(plusCycles);
        indexprofitdto.setMinusCycles(minusCycles);
        indexprofitdto.setWinRate(winRate);
        indexprofitdto.setCycleChangeMap(cycleChangeMap);
        strategyResultdto.setIndexprofitvo(indexprofitdto);

        strategyResultdto.setCurrentStraProfit(oneDayProfitList.get(oneDayProfitList.size()-1).getStraProfit());
        strategyResultdto.setCurrentStandardProfit(oneDayProfitList.get(oneDayProfitList.size()-1).getStandardProfit());

        return strategyResultdto;
    }

    @Override
    public ArrayList<oneExtraProfitDTO> getOneExtraProfit(int daysType, int days, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        ArrayList<oneExtraProfitDTO> extraProfitList=new ArrayList<oneExtraProfitDTO>();
        if(daysType==1){
            //传入的是形成期
            int formDays=days;
            int holdDays;
            for(int count=6;count<=50;count++){
                holdDays=count;

                strategyResultDTO straresultvo=getStraOneResult(formDays,holdDays,sDate,lDate,stockType,codeList,blockCode);
                double straProfit=straresultvo.getCurrentStraProfit();
                double standardProfit=straresultvo.getStandardProfit();
                double excessProfit=straProfit-standardProfit;
                double winRate=straresultvo.getIndexprofitvo().getWinRate();

                oneExtraProfitDTO oneextraprofitvo=new oneExtraProfitDTO();
                oneextraprofitvo.setCycle(count);
                oneextraprofitvo.setExtraProfit(excessProfit);
                oneextraprofitvo.setWinRate(winRate);

                extraProfitList.add(oneextraprofitvo);
            }

        }else if(daysType==2){
            //传入的是持有期
            int holdDays=days;
            int formDays;
            for(int count=6;count<=50;count++){
                formDays=count;

                strategyResultDTO straresultvo=getStraOneResult(formDays,holdDays,sDate,lDate,stockType,codeList,blockCode);
                double straProfit=straresultvo.getCurrentStraProfit();
                double standardProfit=straresultvo.getStandardProfit();
                double excessProfit=straProfit-standardProfit;
                double winRate=straresultvo.getIndexprofitvo().getWinRate();

                oneExtraProfitDTO oneextraprofitvo=new oneExtraProfitDTO();
                oneextraprofitvo.setCycle(count);
                oneextraprofitvo.setExtraProfit(excessProfit);
                oneextraprofitvo.setWinRate(winRate);

                extraProfitList.add(oneextraprofitvo);

            }
        }


        return extraProfitList;
    }

    @Override
    public strategyResultDTO getStraTwoResult(int averageDays, int holdDays, int stockNumbers, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        strategyResultDTO straresultvo=new strategyResultDTO();
        straresultvo.setStraId("2");
        ArrayList<oneDayProfitDTO> oneDayProfitList=new ArrayList<oneDayProfitDTO>();

        //c图的数据
        indexProfitDTO indexprofitvo=new indexProfitDTO();
        int plusCycles=0;
        int minusCycles=0;
        double winRate=0.0;
        HashMap<Double,Integer> cycleChangeMap=new HashMap<Double, Integer>(); //Double表示涨跌幅 int表示数量

        //当前的本金率 用来计算累计收益率
        double currentStraCapital=1.0;
        double currentStandardCapital=1.0;

        HashMap<String,HashMap<String,DayKLine>> averageDaysMap=new HashMap<String, HashMap<String, DayKLine>>();



        ArrayList<String> stockCodeList=new ArrayList<String>(); //选择的股票池 没有根据日期做筛选

        if(stockType==1){
            ArrayList<StockBasicInfo> allStockInfoList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
            for(int count=0;count<allStockInfoList.size();count++){
                stockCodeList.add(allStockInfoList.get(count).getCode());
            }
        }else if(stockType==2){
            String plateCode=codeList.get(0);

            if(plateCode.equals("sh000016")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeBySz50Block();
            }else if(plateCode.equals("sh000300")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeByHs300Block();
            }else if(plateCode.equals("sh000905")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeByZz500Block();
            }else if(plateCode.equals("sz399005")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeBySmeBlock();
            }else if(plateCode.equals("sz399006")){
                stockCodeList= (ArrayList<String>) dayklinemapper.getAllCodeByGemBlock();
            }


        }else if(stockType==3){
//            //自定义股票池中的股票数量需要大于等于100
//            if(codeList.size()>100||codeList.size()==100){
//                stockCodeList=codeList;
//            }else{
//                //跳出提示框提示自选股票数量不足100
//            }

            for(int count=0;count<codeList.size();count++){
                stockCodeList.add(codeList.get(count).split("  ")[0]);
            }
        }else{
            //跳出提示框 提示传入的参数不对
        }





        //获得一段时间的所有股票的信息  开始日期的前formdays-结束日期
        //allStockMap

        //获得这段时间的版块的全部信息
        HashMap<String,DayKLine> blockMap=new HashMap<String, DayKLine>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realSDate=DateConvert.getRealStartDate(sDate,allDateList);
        String realLDate=DateConvert.getRealEndDate(lDate,allDateList);

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("block", blockCode);
        map.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realSDate,14)));
        map.put("end",Date.valueOf(realLDate));

        ArrayList<DayKLine> blockList= (ArrayList<DayKLine>) dayklinemapper.getTimesBlockInfo(map);


//        ArrayList<BlockPO> blockList=new ArrayList<BlockPO>();
//        try {
//            blockList=staticDataService.getTimesBlockInfo(blockCode,getMarketLastDate(sDate,allDateList,formDays),lDate);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
        //blockList中的时间格式是XXXX-XX-XX
        for(int count=0;count<blockList.size();count++){
            blockMap.put(DateConvert.dateToString(blockList.get(count).getStockDate()),blockList.get(count));
        }

        String currentDate=realSDate;
        String changeDate=realSDate;

        double currentStraProfit=1.0;  //当天的策略累计收益率
        double currentStandardProfit=1.0;   //当天的基准累计收益率
        int number=stockNumbers; //持有股票的支数
        ArrayList<Double> straProfitList=new ArrayList<Double>();  //每天的策略收益率的列表
        ArrayList<Double> standardProfitList=new ArrayList<Double>(); //每天的基准收益率的列表
        ArrayList<String> holdStockCodeList=new ArrayList<String>(); //持有期内持有的股票编号的列表
        ArrayList<Double> currentStraProfitList=new ArrayList<Double>(); //每天策略的累计收益率列表
        double cycleChangePercent=0.0; //在整个上个持有期的收益率


        for(int count=allDateList.indexOf(sDate);count<=allDateList.indexOf(lDate);count++){
            currentDate=allDateList.get(count);

            ArrayList<String> validCodeList=new ArrayList<String>(); //用来存储当天有信息的所有股票编号
            ArrayList<DayKLine> currentStockList=new ArrayList<DayKLine>(); //当天的有信息的股票列表
            ArrayList<DayKLine> yesterdayStockList=new ArrayList<DayKLine>(); //前一个交易日的有信息的股票列表

            currentStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(currentDate));

            HashMap<String,DayKLine> currentStockMap=new HashMap<String,DayKLine>();
            for(int index=0;index<currentStockList.size();index++){
                currentStockMap.put(currentStockList.get(index).getStockCode(),currentStockList.get(index));
            }

            yesterdayStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(DateConvert.getLastNDate(allDateList,currentDate,1)));

            HashMap<String,DayKLine> yesterdayStockMap=new HashMap<String,DayKLine>();
            for(int index=0;index<yesterdayStockList.size();index++){
                yesterdayStockMap.put(yesterdayStockList.get(index).getStockCode(),yesterdayStockList.get(index));
            }

            for(int index=0;index<stockCodeList.size();index++){
                String stockCode=stockCodeList.get(index);
                if(currentStockMap.containsKey(stockCode)){
                    validCodeList.add(stockCode);  //当天可供选择的股票列表
                }

            }


            if(currentDate.equals(changeDate)){
                ArrayList<waveDTO> DeviationList=new ArrayList<waveDTO>();

                averageDaysMap=new HashMap<String, HashMap<String, DayKLine>>();
                for(int index=0;index<=averageDays-1;index++){
                    String nowDate=DateConvert.getLastNDate(allDateList,currentDate,index);
                    ArrayList<DayKLine> nowDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(nowDate));
                    HashMap<String,DayKLine> stockMap=new HashMap<String, DayKLine>();
                    for(int j=0;j<nowDayKLineList.size();j++){
                        stockMap.put(nowDayKLineList.get(j).getStockCode(),nowDayKLineList.get(j));
                    }

                    averageDaysMap.put(DateConvert.getLastNDate(allDateList,currentDate,index),stockMap);
                }


                //对所有有效股票进行循环
                for(int index=0;index<validCodeList.size();index++){
                    double totalAdj=0.0;
                    int whetherExist=1;  //1代表每天都存在 0表示这几天的均线有一天信息并不存在
                    double average=0.0;
                    double deviation=0.0; //如果用P代表现在的股价，用MA代表均线的价格，那么这个度量是 (MA-P)/MA

                    String currentStockNode=validCodeList.get(index);

                    for(int j=count-averageDays+1;j<=count;j++){
                        HashMap<String, DayKLine> oneDayStockMap=averageDaysMap.get(allDateList.get(j));
                        if(!oneDayStockMap.containsKey(currentStockNode)){
                            whetherExist=0;
                        }else{
                            totalAdj=totalAdj+oneDayStockMap.get(currentStockNode).getClosePrice();
                        }


                    }

                    if(whetherExist==1){
                        average=totalAdj/averageDays;
                        double nowPrice=averageDaysMap.get(currentDate).get(currentStockNode).getClosePrice();
                        deviation=(average-nowPrice)/average;

                        waveDTO wavevo=new waveDTO();
                        wavevo.setStockCode(currentStockNode);
                        wavevo.setChangePercent(deviation);
                        DeviationList.add(wavevo);
                    }

                }


                ArrayList<waveDTO> topNList=new ArrayList<waveDTO>();
                topNList=getTopNCodesByDeviationList(number,DeviationList);

                holdStockCodeList.clear();

                for(int index=0;index<topNList.size();index++){
                    holdStockCodeList.add(topNList.get(index).getStockCode());
                }

                if(allDateList.indexOf(lDate)-allDateList.indexOf(currentDate)>holdDays-1){
                    changeDate=DateConvert.getLastNDate(allDateList,currentDate,-holdDays);
                }

                if(cycleChangePercent>0.0){
                    plusCycles++;
                    double ToDownPercent=Math.floor(100*cycleChangePercent)/100;
                    if(cycleChangeMap.containsKey(ToDownPercent)){
                        int oldNumber=cycleChangeMap.get(ToDownPercent);
                        cycleChangeMap.replace(ToDownPercent,oldNumber+1);
                    }else{
                        cycleChangeMap.put(ToDownPercent,1);
                    }
                }else if(cycleChangePercent<0.0){
                    minusCycles++;
                    double ToUpPercent=Math.ceil(100*cycleChangePercent)/100;
                    if(cycleChangeMap.containsKey(ToUpPercent)){
                        int oldNumer=cycleChangeMap.get(ToUpPercent);
                        cycleChangeMap.replace(ToUpPercent,oldNumer+1);
                    }else{
                        cycleChangeMap.put(ToUpPercent,1);
                    }
                }


                currentStraCapital=currentStraProfit;
                currentStandardCapital=currentStandardProfit;

                cycleChangePercent=0.0;
            }

            //下面开始处理每天的数据 要注意有可能后面没有数据的股票
//            double standardUp=0.0;
//            BlockPO nowBlockPo=blockMap.get(currentDate);
//
//            BlockPO yesterdayBlockPo=blockMap.get(getMarketLastDate(currentDate,allDateList,1));
//
//            standardUp=CalculateHelper.getIncrease(yesterdayBlockPo.getClosePrice(),nowBlockPo.getClosePrice());
//            standardProfitList.add(standardUp);
//
//            currentStandardProfit=currentStandardProfit+standardUp;
//
//            double totalUp=0.0;
//            for(int index=0;index<holdStockCodeList.size();index++){
//                String code=holdStockCodeList.get(index);
//                if(currentStockMap.containsKey(code)&&yesterdayStockMap.containsKey(code)){
//                    double nowPrice=currentStockMap.get(code).getAdjClose();
//                    double lastPrice=yesterdayStockMap.get(code).getAdjClose();
//                    totalUp=totalUp+CalculateHelper.getIncrease(lastPrice,nowPrice);
//
//                }
//            }
//            straProfitList.add(totalUp/number);
//
//            currentStraProfit=currentStraProfit+totalUp/number;
//            currentStraProfitList.add(currentStraProfit);
            double standardUp=0.0;




            if(stockType==3){

                for(int index=0;index<validCodeList.size();index++){
                    String stockCode=validCodeList.get(index);
                    if(currentStockMap.containsKey(stockCode)&&yesterdayStockMap.containsKey(stockCode)){
                        standardUp=standardUp+StockCalculator.getIncrease(yesterdayStockMap.get(stockCode).getClosePrice(),currentStockMap.get(stockCode).getClosePrice());
                    }
                }

                standardUp=standardUp/validCodeList.size();
            }else{
                DayKLine nowStockPo=blockMap.get(currentDate);
                DayKLine yesterdayBlockPo=blockMap.get(DateConvert.getLastNDate(allDateList,currentDate,1));
                standardUp=StockCalculator.getIncrease(yesterdayBlockPo.getClosePrice(),nowStockPo.getClosePrice());

            }

            currentStandardProfit=currentStandardProfit+currentStandardCapital*standardUp;

            standardProfitList.add(standardUp);


            double totalUp=0.0;
            for(int index=0;index<holdStockCodeList.size();index++){
                String code=holdStockCodeList.get(index);
                if(currentStockMap.containsKey(code)&&yesterdayStockMap.containsKey(code)){
                    double nowPrice=currentStockMap.get(code).getClosePrice();
                    double lastPrice=yesterdayStockMap.get(code).getClosePrice();
                    totalUp=totalUp+StockCalculator.getIncrease(lastPrice,nowPrice);
                }
            }
            currentStraProfit=currentStraProfit+currentStraCapital*(totalUp/number);
            straProfitList.add(totalUp/number);
            currentStraProfitList.add(currentStraProfit);




            cycleChangePercent=cycleChangePercent+totalUp/number;


            oneDayProfitDTO onedayprofitvo=new oneDayProfitDTO();
            onedayprofitvo.setDate(currentDate);
            onedayprofitvo.setStraProfit(currentStraProfit-1.0);
            onedayprofitvo.setStandardProfit(currentStandardProfit-1.0);

            oneDayProfitList.add(onedayprofitvo);
        }

        //统计环节
        //分别计算 年化收益率 基准年化收益率 阿尔法 贝塔 夏普比率 收益波动率 信息比率 最大回撤 换手率
        //        最终的基准收益率currentStandardProfit;    每一天的基准收益率列表standardProfitList;
        //        最终的策略收益率currentStraProfit;        每一天的策略收益率列表straProfitList;
        //        每天的累计收益率列表 currentStraProfitList
        int totalCycles=plusCycles+minusCycles;
        double plusTimes=plusCycles;
        double totalTimes=totalCycles;
        winRate=plusTimes/totalTimes;

        double straYearlyBenifit;
        double standardYearlyBenifit;
        double alpha;
        double beta;
        double sharpRate;
        double profitWaveRate;
        double infoRate;
        double maxBack;
        double turnOverRate;

        straYearlyBenifit=((currentStraProfit-1.0)/straProfitList.size())*250;
        standardYearlyBenifit=((currentStandardProfit-1.0)/standardProfitList.size())*250;
        beta=StockCalculator.getCovariance(straProfitList,standardProfitList)/StockCalculator.getVariance(standardProfitList);
        alpha=straYearlyBenifit- StockCalculator.getOneYearRate()-beta*(standardYearlyBenifit-StockCalculator.getOneYearRate());
        profitWaveRate=Math.sqrt(StockCalculator.getVariance(straProfitList))*Math.sqrt(straProfitList.size());
        sharpRate=(straYearlyBenifit-StockCalculator.getOneYearRate())/profitWaveRate;

        ArrayList<Double> minusList=new ArrayList<Double>();
        for(int count=0;count<straProfitList.size();count++){
            minusList.add(straProfitList.get(count)-standardProfitList.get(count));
        }
        infoRate=(currentStraProfit-currentStandardProfit)/(Math.sqrt(StockCalculator.getVariance(minusList)));

        ArrayList<Double> allBackList=new ArrayList<Double>();
        double maxProfit=currentStraProfitList.get(0);
        for(int count=1;count<currentStraProfitList.size();count++){
            if(currentStraProfitList.get(count)>maxProfit){
                maxProfit=currentStraProfitList.get(count);
            }
            allBackList.add((maxProfit-currentStraProfitList.get(count))/(1+maxProfit));
        }

        maxBack=Collections.max(allBackList);





        straresultvo.setYearProfit(straYearlyBenifit);
        straresultvo.setStandardProfit(standardYearlyBenifit);
        straresultvo.setAlpha(alpha);
        straresultvo.setBeta(beta);
        straresultvo.setSharpRate(sharpRate);
        straresultvo.setProfitWaveRate(profitWaveRate);
        straresultvo.setInfoPercent(infoRate);
        straresultvo.setMaxBack(maxBack);
        straresultvo.setDaysProfitList(oneDayProfitList);

//        System.out.println(straresultvo.getYearProfit()+" "+straresultvo.getStandardProfit()+" "+straresultvo.getAlpha()+" "+straresultvo.getBeta()+" "+straresultvo.getSharpRate()+" "+straresultvo.getProfitWaveRate()+" "+straresultvo.getInfoPercent()+" "+straresultvo.getMaxBack());

        indexprofitvo.setPlusCycles(plusCycles);
        indexprofitvo.setMinusCycles(minusCycles);
        indexprofitvo.setWinRate(winRate);
        indexprofitvo.setCycleChangeMap(cycleChangeMap);
        straresultvo.setIndexprofitvo(indexprofitvo);

        straresultvo.setCurrentStraProfit(oneDayProfitList.get(oneDayProfitList.size()-1).getStraProfit());
        straresultvo.setCurrentStandardProfit(oneDayProfitList.get(oneDayProfitList.size()-1).getStandardProfit());

//        System.out.println(plusCycles+" "+minusCycles+" "+winRate+" "+" "+cycleChangeMap.get(0.00)+" "+cycleChangeMap.get(0.01)+" "+cycleChangeMap.get(0.02)+" "+cycleChangeMap.get(0.03));

//        Iterator iter=cycleChangeMap.entrySet().iterator();
//        while(iter.hasNext()){
//            Map.Entry entry=(Map.Entry) iter.next();
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }

        return straresultvo;
    }

    @Override
    public ArrayList<oneExtraProfitDTO> getTwoExtraProfit(int averageDays, int stockNumbers, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        ArrayList<oneExtraProfitDTO> extraProfitList=new ArrayList<oneExtraProfitDTO>();

        for(int count=6;count<=50;count++){
            int holdDays=count;

            strategyResultDTO straresultvo=getStraTwoResult(averageDays,holdDays,stockNumbers,sDate,lDate,stockType,codeList,blockCode);
            double straProfit=straresultvo.getCurrentStraProfit();
            double standardProfit=straresultvo.getStandardProfit();
            double excessProfit=straProfit-standardProfit;

            double winRate=straresultvo.getIndexprofitvo().getWinRate();

            oneExtraProfitDTO oneextraprofitvo=new oneExtraProfitDTO();
            oneextraprofitvo.setCycle(count);
            oneextraprofitvo.setExtraProfit(excessProfit);
            oneextraprofitvo.setWinRate(winRate);

            extraProfitList.add(oneextraprofitvo);

        }




        return extraProfitList;
    }

    /**
     * 该方法通过两天的股票的HashMap来得到
     * @param n 前n名
     * @param lastMap 之前某日的全部股票信息
     * @param nowMap 当日的全部股票信息
     * @param validCodeList 当日有效的全部股票编号
     * @return
     */
    public ArrayList<waveDTO> getTopNCodesByHashmap(int n,HashMap<String,DayKLine> lastMap,HashMap<String,DayKLine> nowMap,ArrayList<String> validCodeList){
        ArrayList<waveDTO> resultCodes=new ArrayList<waveDTO>();
        ArrayList<waveDTO> waveList=new ArrayList<waveDTO>();

        for(int count=0;count<validCodeList.size();count++){
            String code=validCodeList.get(count);
            if(nowMap.containsKey(code)&&lastMap.containsKey(code)){
                waveDTO wavevo=new waveDTO();
                wavevo.setStockCode(code);
                wavevo.setChangePercent(StockCalculator.getIncrease(lastMap.get(code).getClosePrice(),nowMap.get(code).getClosePrice()));
                waveList.add(wavevo);
            }
        }


        //下面是把wavevo按照涨跌幅的大小来进行排序
        Collections.sort(waveList, COMPARATOR);

        if(n>waveList.size()){
            resultCodes=waveList;
        }else{
            for(int count=0;count<n;count++){
                resultCodes.add(waveList.get(count));
            }
        }


        return resultCodes;
    }

    /**
     * 需要修改 减少两个参数
     * 该方法通过两天的股票的HashMap来得到
     * @param n 前n名
     * @param deviationList 偏离度列表 包含股票编号和偏离度
     * @return
     */
    public ArrayList<waveDTO> getTopNCodesByDeviationList(int n, ArrayList<waveDTO> deviationList){
        ArrayList<waveDTO> resultCodes=new ArrayList<waveDTO>();
        ArrayList<waveDTO> waveList=new ArrayList<waveDTO>();

        for(int count=0;count<deviationList.size();count++){
            waveList.add(deviationList.get(count));
        }


        //下面是把wavevo按照涨跌幅的大小来进行排序
        Collections.sort(waveList, COMPARATOR);

        if(n>waveList.size()){
            resultCodes=waveList;
        }else{
            for(int count=0;count<n;count++){
                resultCodes.add(waveList.get(count));
            }
        }


        return resultCodes;
    }

    //编写Comparator,根据WaveVO的changePercent对waveVO进行排序
    private static final Comparator<waveDTO> COMPARATOR = new Comparator<waveDTO>() {
        public int compare(waveDTO o1, waveDTO o2) {
            return o1.compareTo(o2);//运用User类的compareTo方法比较两个对象
        }
    };
}
