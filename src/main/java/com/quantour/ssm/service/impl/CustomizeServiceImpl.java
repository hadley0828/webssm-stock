package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.RateMapper;
import com.quantour.ssm.dao.StrategyMapper;
import com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO;
import com.quantour.ssm.dto.customizeStrategy.ScreeningConditionDTO;
import com.quantour.ssm.dto.customizeStrategy.StockPondDTO;
import com.quantour.ssm.dto.customizeStrategy.TradeModelDTO;
import com.quantour.ssm.dto.indexProfitDTO;
import com.quantour.ssm.dto.oneDayProfitDTO;
import com.quantour.ssm.dto.strategyResultDTO;
import com.quantour.ssm.dto.waveDTO;
import com.quantour.ssm.model.CustomizeStrategy;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.ScreenCondition;
import com.quantour.ssm.model.StockBasicInfo;
import com.quantour.ssm.service.CustomizeService;
import com.quantour.ssm.util.CodeIndustryMap;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.NumberConvert;
import com.quantour.ssm.util.StockCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangzy on 2017/5/25.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomizeServiceImpl implements CustomizeService{

    @Resource
    private DayKLineMapper dayKLineMapper;
    @Resource
    private StrategyMapper strategyMapper;

    @Override
    public ArrayList<String> getAllIndustryBlock() {
        return (ArrayList<String>) dayKLineMapper.getAllIndustryBlock();
    }

    @Override
    public ArrayList<String> getAllConceptBlock() {
        return (ArrayList<String>) dayKLineMapper.getAllConceptBlock();
    }

    @Override
    public ArrayList<String> getAllAreaBlock() {
        return (ArrayList<String>) dayKLineMapper.getAllAreaBlock();
    }

    @Override
    public strategyResultDTO getCustomizeStrategyResult(String userId,String sDate, String lDate, String blockCode, StockPondDTO stockPondDTO, ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList, TradeModelDTO tradeModelDTO) {
        strategyResultDTO strategyResultdto=new strategyResultDTO();
        strategyResultdto.setStraId("未知");
        ArrayList<oneDayProfitDTO> oneDayProfitList=new ArrayList<oneDayProfitDTO>();

        //C图的数据
        indexProfitDTO indexprofitdto=new indexProfitDTO();
        int plusCycles=0;
        int minusCycles=0;
        double winRate=0.0;
        HashMap<Double,Integer> cycleChangeMap=new HashMap<Double, Integer>();
        //Double表示涨跌幅 Integer表示数量

        //当前的本金率 用来计算累计收益率
        double currentStraCapital=1.0;
        double currentStandardCapital=1.0;

        //下面是股票池 根据StockPondDTO来获得
        ArrayList<String> stockCodeList=new ArrayList<String>();

        ArrayList<String> initialCodeList=new ArrayList<String>();
        if(stockPondDTO.getStockPondChosen().equals("全部股票")){
            ArrayList<StockBasicInfo> stockBasicInfoArrayList= (ArrayList<StockBasicInfo>) dayKLineMapper.getAllStockInfos();
            for(int count=0;count<stockBasicInfoArrayList.size();count++){
                initialCodeList.add(stockBasicInfoArrayList.get(count).getCode());
            }


        }else if(stockPondDTO.getStockPondChosen().equals("自选股票池")){
            //initialCodeList=getOneUserOptionalStocks(String id)
            initialCodeList=dayKLineMapper.getUserAllStock(userId);

        }

        //下面获取行业和股票 股票和行业的对应关系
        //      股票编号 行业名称
        HashMap<String,String> codeToIndustryMap=dayKLineMapper.getAllIndustryAndCode(new CodeIndustryMap("code","industry"));
        //      行业名称 股票编号的set
        HashMap<String,HashSet<String>> industryToCodeMap=new HashMap<String, HashSet<String>>();

        for (Map.Entry<String, String> entry : codeToIndustryMap.entrySet()) {
            String oneCode=entry.getKey();
            String oneIndustry=entry.getValue();

            if(industryToCodeMap.containsKey(oneIndustry)){
                industryToCodeMap.get(oneIndustry).add(oneCode);
            }else {
                HashSet<String> newSet=new HashSet<String>();
                industryToCodeMap.put(oneIndustry,newSet);
                industryToCodeMap.get(oneIndustry).add(oneCode);
            }
        }

        //完成和行业相关的两个map的处理






        ArrayList<HashSet<String>> stockChooseList=new ArrayList<HashSet<String>>();



        if(stockPondDTO.getIndexIngredient().equals("全选")){

        }else if(stockPondDTO.getIndexIngredient().equals("sh000016")){
            HashSet<String> set=new HashSet<String>();
            ArrayList<String> indexList= (ArrayList<String>) dayKLineMapper.getAllCodeBySz50Block();
            for(int count=0;count<indexList.size();count++){
                set.add(indexList.get(count));
            }
            stockChooseList.add(set);


        }else if(stockPondDTO.getIndexIngredient().equals("sh000300")){
            HashSet<String> set=new HashSet<String>();
            ArrayList<String> indexList= (ArrayList<String>) dayKLineMapper.getAllCodeByHs300Block();
            for(int count=0;count<indexList.size();count++){
                set.add(indexList.get(count));
            }
            stockChooseList.add(set);


        }else if(stockPondDTO.getIndexIngredient().equals("sh000905")){
            HashSet<String> set=new HashSet<String>();
            ArrayList<String> indexList= (ArrayList<String>) dayKLineMapper.getAllCodeByZz500Block();
            for(int count=0;count<indexList.size();count++){
                set.add(indexList.get(count));
            }
            stockChooseList.add(set);

        }

        if(stockPondDTO.getBlock().equals("全选")){

        }else if(stockPondDTO.getBlock().equals("主板")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.substring(0,2).equals("60")||oneCode.substring(0,3).equals("000")){
                    set.add(oneCode);
                }


            }

            stockChooseList.add(set);

        }else if(stockPondDTO.getBlock().equals("创业板")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.substring(0,3).equals("300")){
                    set.add(oneCode);
                }

            }

            stockChooseList.add(set);

        }else if(stockPondDTO.getBlock().equals("中小板")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.substring(0,3).equals("002")){
                    set.add(oneCode);
                }
            }

            stockChooseList.add(set);

        }


        if(stockPondDTO.getIndustry().equals("全选")){

        }else{
            HashSet<String> set=new HashSet<String>();
            ArrayList<String> allStockInIndustry= (ArrayList<String>) dayKLineMapper.getIndustryBlockStockCodes(stockPondDTO.getIndustry());
            for(int count=0;count<allStockInIndustry.size();count++){
                set.add(allStockInIndustry.get(count));
            }

            stockChooseList.add(set);


        }

        if(stockPondDTO.getConcept().equals("全选")){

        }else{
            HashSet<String> set=new HashSet<String>();
            ArrayList<String> allStockInConcept= (ArrayList<String>) dayKLineMapper.getConceptBlockStockCodes(stockPondDTO.getConcept());
            for(int count=0;count<allStockInConcept.size();count++){
                set.add(allStockInConcept.get(count));
            }
            stockChooseList.add(set);


        }

        if(stockPondDTO.getSTStock().equals("包含ST")){

        }else if(stockPondDTO.getSTStock().equals("排除ST")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.substring(0,2).equals("ST")||oneCode.substring(0,2).equals("*S")){

                }else {
                    set.add(oneCode);
                }
            }

            stockChooseList.add(set);

        }else if(stockPondDTO.getSTStock().equals("仅有ST")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.substring(0,2).equals("ST")||oneCode.substring(0,2).equals("*S")){
                    set.add(oneCode);
                }
            }

            stockChooseList.add(set);
        }

        if(stockPondDTO.getExchange().equals("全选")){

        }else if(stockPondDTO.getExchange().equals("上海")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.charAt(0)=='6'){
                    set.add(oneCode);
                }
            }

            stockChooseList.add(set);

        }else if(stockPondDTO.getExchange().equals("深圳")){
            HashSet<String> set=new HashSet<String>();
            for(int count=0;count<initialCodeList.size();count++){
                String oneCode=initialCodeList.get(count);
                if(oneCode.charAt(0)=='3'||oneCode.charAt(0)=='0'){
                    set.add(oneCode);
                }
            }

            stockChooseList.add(set);

        }

        if(stockPondDTO.getRegion().equals("全选")){

        }else{
            HashSet<String> set=new HashSet<String>();
            ArrayList<String> allStockInArea= (ArrayList<String>) dayKLineMapper.getAreaBlockStockCodes(stockPondDTO.getRegion());
            for(int count=0;count<allStockInArea.size();count++){
                set.add(allStockInArea.get(count));
            }

            stockChooseList.add(set);
        }

        //这时StockChooseList中存储的是各种股票池的条件

        //下面这段筛选股票可能会出错
        if(stockChooseList.size()==0){
            stockCodeList=initialCodeList;
        }else{
            for(int count=0;count<initialCodeList.size();count++){
                String initialCode=initialCodeList.get(count);

                int numberOfConditionAchieved=0;


                for(int index=0;index<stockChooseList.size();index++){
                    HashSet<String> oneSet=stockChooseList.get(index);
                    if(oneSet.contains(initialCode)){
                        numberOfConditionAchieved++;
                    }
                }

                if(numberOfConditionAchieved==stockChooseList.size()){
                    stockCodeList.add(initialCode);
                }

            }
        }

        HashSet<String> stockCodeSet=new HashSet<String>();
        for(int count=0;count<stockCodeList.size();count++){
            stockCodeSet.add(stockCodeList.get(count));
        }

        //获得一段时间内的所有股票的信息
        //allStockMap

        //获得这段时间的基准板块的全部信息
        HashMap<String,DayKLine> blockMap=new HashMap<String, DayKLine>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayKLineMapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realSDate=DateConvert.getRealStartDate(sDate,allDateList);
        String realLDate=DateConvert.getRealEndDate(lDate,allDateList);

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("block", blockCode);
        map.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realSDate,20)));
        map.put("end",Date.valueOf(realLDate));

        ArrayList<DayKLine> blockList= (ArrayList<DayKLine>) dayKLineMapper.getTimesBlockInfo(map);


        HashMap<String,Date> timeMap = new HashMap<String, Date>();
        timeMap.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realSDate,20)));
        timeMap.put("end",Date.valueOf(realLDate));
        ArrayList<DayKLine> allStockInfoList= (ArrayList<DayKLine>) dayKLineMapper.getStocksByTimes(timeMap);

        //      日期            股票编号 单支股票的信息
        HashMap<String,HashMap<String,DayKLine>> allStockMap=new HashMap<String, HashMap<String, DayKLine>>();

        for(int count=allDateList.indexOf(DateConvert.getLastNDate(allDateList,realSDate,20));count<=allDateList.indexOf(realLDate);count++){
            HashMap<String,DayKLine> oneDayMap=new HashMap<String, DayKLine>();
            allStockMap.put(allDateList.get(count),oneDayMap);
        }

        for(int count=0;count<allStockInfoList.size();count++){
            if(stockCodeSet.contains(allStockInfoList.get(count).getStockCode())){
                String oneStockDate=DateConvert.dateToString(allStockInfoList.get(count).getStockDate());
                allStockMap.get(oneStockDate).put(allStockInfoList.get(count).getStockCode(),allStockInfoList.get(count));
            }
        }

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

            ArrayList<DayKLine> beforeFormdaysStockList=new ArrayList<DayKLine>(); //涨跌幅规定日数之前的有信息的股票列表

            HashMap<String,DayKLine> currentStockMap=allStockMap.get(currentDate);

            HashMap<String,DayKLine> yesterdayStockMap=allStockMap.get(DateConvert.getLastNDate(allDateList,currentDate,1));

            for(int index=0;index<stockCodeList.size();index++){
                String stockCode=stockCodeList.get(index);
                if(currentStockMap.containsKey(stockCode)){
                    validCodeList.add(stockCode);
                }
            }







            if(currentDate.equals(changeDate)){

                //用来存储所有满足条件的股票编号 之后还要进行处理存储到holdStockCodeList
                ArrayList<String> alternativeStockList=new ArrayList<String>();

                //当天全部的股票池中存在的股票
                ArrayList<String> currentAllExistStocks=new ArrayList<String>();
                //遍历当天股票Map
                for (Map.Entry<String, DayKLine> entry : currentStockMap.entrySet()) {
                    DayKLine dayKLine=entry.getValue();
                    currentAllExistStocks.add(dayKLine.getStockCode());
                }
                //currentAllExistStocks处理完成


                holdStockCodeList.clear();
                //进行股票池的重新选择

                ArrayList<HashSet<String>> allConditionStockList=new ArrayList<HashSet<String>>();


                for(int index=0;index<screeningConditionDTOArrayList.size();index++){
                    ScreeningConditionDTO screeningConditionDTO=screeningConditionDTOArrayList.get(index);
                    //根据这一条筛选条件获得在validStockCodeList中符合标准的一个set
                    HashSet<String> set=getAccordingCodeSet(currentDate,allDateList,allStockMap,currentAllExistStocks,screeningConditionDTO,industryToCodeMap);
                    allConditionStockList.add(set);

                }




                //循环获取validStockCodeList中的全部股票 看是否满足条件
                for(int index=0;index<validCodeList.size();index++){
                    String stockCode=validCodeList.get(index);

                    int conditionAchievedNumber=0;

                    for(int i=0;i<allConditionStockList.size();i++){
                        HashSet<String> set=allConditionStockList.get(i);

                        if(set.contains(stockCode)){
                            conditionAchievedNumber++;

                        }
                    }

                    if(conditionAchievedNumber==allConditionStockList.size()){
                        alternativeStockList.add(stockCode);
                    }

                }


                if(alternativeStockList.size()<tradeModelDTO.getMaxHoldStockNumber()){
                    holdStockCodeList=alternativeStockList;
                }else{
                    for(int index=0;index<tradeModelDTO.getMaxHoldStockNumber();index++){

                        holdStockCodeList.add(alternativeStockList.get(index));
                    }
                }

                number=holdStockCodeList.size();


                if(allDateList.indexOf(realLDate)-allDateList.indexOf(currentDate)>tradeModelDTO.getTransferCycle()-1){
                    changeDate=DateConvert.getLastNDate(allDateList,currentDate,-tradeModelDTO.getTransferCycle());
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
            DayKLine nowStockPo=blockMap.get(currentDate);
            DayKLine yesterdayBlockPo=blockMap.get(DateConvert.getLastNDate(allDateList,currentDate,1));

            standardUp=StockCalculator.getIncrease(yesterdayBlockPo.getClosePrice(),nowStockPo.getClosePrice());


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
            onedayprofitvo.setStraProfit(NumberConvert.saveNDouble(currentStraProfit-1.0,5));
            onedayprofitvo.setStandardProfit(NumberConvert.saveNDouble(currentStandardProfit-1.0,5));

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

        strategyResultdto.setYearProfit(NumberConvert.doubleToPercentageString(straYearlyBenifit));
        strategyResultdto.setStandardProfit(NumberConvert.doubleToPercentageString(standardYearlyBenifit));
        strategyResultdto.setAlpha(NumberConvert.saveNDouble(alpha,3));
        strategyResultdto.setBeta(NumberConvert.saveNDouble(beta,3));
        strategyResultdto.setSharpRate(NumberConvert.saveNDouble(sharpRate,3));
        strategyResultdto.setProfitWaveRate(NumberConvert.saveNDouble(profitWaveRate,3));
        strategyResultdto.setInfoPercent(NumberConvert.saveNDouble(infoRate,3));
        strategyResultdto.setMaxBack(NumberConvert.saveNDouble(maxBack,3));
        strategyResultdto.setDaysProfitList(oneDayProfitList);

        indexprofitdto.setPlusCycles(plusCycles);
        indexprofitdto.setMinusCycles(minusCycles);
        indexprofitdto.setWinRate(NumberConvert.doubleToPercentageString(winRate));
        indexprofitdto.setCycleChangeMap(cycleChangeMap);
        strategyResultdto.setIndexprofitvo(indexprofitdto);

        strategyResultdto.setCurrentStraProfit(oneDayProfitList.get(oneDayProfitList.size()-1).getStraProfit());
        strategyResultdto.setCurrentStandardProfit(oneDayProfitList.get(oneDayProfitList.size()-1).getStandardProfit());

        return strategyResultdto;
    }

    /**
     * 获得一个条件对应的股票编号的set  !!!可能需要传入一个全部行业和股票编号对应的map!!!
     * @param currentDate
     * @param allDateList
     * @param allStockMap
     * @param currentAllExistStocks
     * @param screeningConditionDTO
     *@param industryToCodeMap @return
     */
    private HashSet<String> getAccordingCodeSet(String currentDate, ArrayList<String> allDateList, HashMap<String, HashMap<String, DayKLine>> allStockMap, ArrayList<String> currentAllExistStocks, ScreeningConditionDTO screeningConditionDTO,  HashMap<String, HashSet<String>> industryToCodeMap) {
        HashSet<String> resultSet=new HashSet<String>();
        String conditionName=screeningConditionDTO.getConditionName();

        if(conditionName.equals("开盘价")){
            //      股票编号 开盘价
            HashMap<String,Double> allOpenPriceMap=new HashMap<String, Double>();
            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);

            for(int count=0;count<currentAllExistStocks.size();count++){
                allOpenPriceMap.put(currentAllExistStocks.get(count),nowMap.get(currentAllExistStocks.get(count)).getOpenPrice());
            }

            resultSet=getResultSet(allOpenPriceMap,screeningConditionDTO,industryToCodeMap);


        }else if(conditionName.equals("收盘价")){
            //      股票编号 收盘价
            HashMap<String,Double> allClosePriceMap=new HashMap<String, Double>();
            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);

            for(int count=0;count<currentAllExistStocks.size();count++){
                allClosePriceMap.put(currentAllExistStocks.get(count),nowMap.get(currentAllExistStocks.get(count)).getClosePrice());
            }

            resultSet=getResultSet(allClosePriceMap,screeningConditionDTO,industryToCodeMap);


        }else if(conditionName.equals("最高价")){
            //      股票编号 最高价
            HashMap<String,Double> allHighPriceMap=new HashMap<String, Double>();
            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);

            for(int count=0;count<currentAllExistStocks.size();count++){
                allHighPriceMap.put(currentAllExistStocks.get(count),nowMap.get(currentAllExistStocks.get(count)).getHighPrice());
            }

            resultSet=getResultSet(allHighPriceMap,screeningConditionDTO,industryToCodeMap);


        }else if(conditionName.equals("最低价")){
            //      股票编号 最低价
            HashMap<String,Double> allLowPriceMap=new HashMap<String, Double>();
            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);

            for(int count=0;count<currentAllExistStocks.size();count++){
                allLowPriceMap.put(currentAllExistStocks.get(count),nowMap.get(currentAllExistStocks.get(count)).getLowPrice());
            }

            resultSet=getResultSet(allLowPriceMap,screeningConditionDTO,industryToCodeMap);

        }else if(conditionName.equals("前日收盘价")){
            //要是前一天的信息不存在的话是不是会有问题？ 小方法

            HashMap<String,Double> allLastPriceMap=new HashMap<String, Double>();
            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);
            HashMap<String,DayKLine> lastMap=allStockMap.get(DateConvert.getLastNDate(allDateList,currentDate,1));

            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                if(nowMap.containsKey(stockCode)&&lastMap.containsKey(stockCode)){
                    allLastPriceMap.put(stockCode,lastMap.get(stockCode).getClosePrice());
                }
            }

            resultSet=getResultSet(allLastPriceMap,screeningConditionDTO,industryToCodeMap);



        }else if(conditionName.equals("当日成交量")){
            HashMap<String,Double> allVolumeMap=new HashMap<String, Double>();
            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);

            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                allVolumeMap.put(stockCode,nowMap.get(stockCode).getVolume());
            }

            resultSet=getResultSet(allVolumeMap,screeningConditionDTO,industryToCodeMap);




        }else if(conditionName.equals("5日平均成交量")){
            HashMap<String,Double> allVolumeMap=new HashMap<String, Double>();

            ArrayList<HashMap<String,DayKLine>> fiveDayKineList=new ArrayList<HashMap<String, DayKLine>>();
            //循环取得5日的Map
            for(int count=0;count<5;count++){
                String date=DateConvert.getLastNDate(allDateList,currentDate,count);
                HashMap<String,DayKLine> oneDayMap=allStockMap.get(date);
                fiveDayKineList.add(oneDayMap);
            }


            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                double totalVolume=0.0;
                int volumeNumber=0;

                for(int index=0;index<5;index++){
                    if(fiveDayKineList.get(index).containsKey(stockCode)){
                        totalVolume=totalVolume+fiveDayKineList.get(index).get(stockCode).getVolume();
                        volumeNumber++;
                    }
                }

                if(volumeNumber==0){
                    allVolumeMap.put(stockCode,0.0);
                }else{
                    allVolumeMap.put(stockCode,totalVolume/volumeNumber);
                }


            }
            resultSet=getResultSet(allVolumeMap,screeningConditionDTO,industryToCodeMap);

        }else if(conditionName.equals("20日平均成交量")){
            HashMap<String,Double> allVolumeMap=new HashMap<String, Double>();

            ArrayList<HashMap<String,DayKLine>> twentyDayKlineList=new ArrayList<HashMap<String, DayKLine>>();
            for(int count=0;count<20;count++){
                String date=DateConvert.getLastNDate(allDateList,currentDate,count);
                HashMap<String,DayKLine> oneDayMap=allStockMap.get(date);
                twentyDayKlineList.add(oneDayMap);
            }

            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                double totalVolume=0.0;
                int volumeNumber=0;

                for(int index=0;index<20;index++){
                    if(twentyDayKlineList.get(index).containsKey(stockCode)){
                        totalVolume=totalVolume+twentyDayKlineList.get(index).get(stockCode).getVolume();
                        volumeNumber++;
                    }
                }

                if(volumeNumber==0){
                    allVolumeMap.put(stockCode,0.0);
                }else{
                    allVolumeMap.put(stockCode,totalVolume/volumeNumber);
                }


            }

            resultSet=getResultSet(allVolumeMap,screeningConditionDTO,industryToCodeMap);

        }else if(conditionName.equals("1日涨幅")){
            HashMap<String,Double> allChangePercentMap=new HashMap<String, Double>();

            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);
            HashMap<String,DayKLine> lastMap=allStockMap.get(DateConvert.getLastNDate(allDateList,currentDate,1));

            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                if(nowMap.containsKey(stockCode)&&lastMap.containsKey(stockCode)){
                    double changePercent=StockCalculator.getIncrease(lastMap.get(stockCode).getClosePrice(),nowMap.get(stockCode).getClosePrice());
                    allChangePercentMap.put(stockCode,changePercent);
                }

            }

            resultSet=getResultSet(allChangePercentMap,screeningConditionDTO,industryToCodeMap);

        }else if(conditionName.equals("5日涨幅")){
            HashMap<String,Double> allChangePercentMap=new HashMap<String, Double>();

            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);
            HashMap<String,DayKLine> lastMap=allStockMap.get(DateConvert.getLastNDate(allDateList,currentDate,5));

            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                if(nowMap.containsKey(stockCode)&&lastMap.containsKey(stockCode)){
                    double changePercent=StockCalculator.getIncrease(lastMap.get(stockCode).getClosePrice(),nowMap.get(stockCode).getClosePrice());
                    allChangePercentMap.put(stockCode,changePercent);
                }

            }

            resultSet=getResultSet(allChangePercentMap,screeningConditionDTO,industryToCodeMap);


        }else if(conditionName.equals("20日涨幅")){
            HashMap<String,Double> allChangePercentMap=new HashMap<String, Double>();

            HashMap<String,DayKLine> nowMap=allStockMap.get(currentDate);
            HashMap<String,DayKLine> lastMap=allStockMap.get(DateConvert.getLastNDate(allDateList,currentDate,20));

            for(int count=0;count<currentAllExistStocks.size();count++){
                String stockCode=currentAllExistStocks.get(count);

                if(nowMap.containsKey(stockCode)&&lastMap.containsKey(stockCode)){
                    double changePercent=StockCalculator.getIncrease(lastMap.get(stockCode).getClosePrice(),nowMap.get(stockCode).getClosePrice());
                    allChangePercentMap.put(stockCode,changePercent);
                }

            }

            resultSet=getResultSet(allChangePercentMap,screeningConditionDTO,industryToCodeMap);


        }else if(conditionName.equals("上市天数")){
            //不好实现
        }else if(conditionName.equals("交易天数")){
            //不好实现
        }


        return resultSet;

    }

    /**
     * 根据算出来的需要比较的值Map和条件得出符合条件的股票编号的list
     * @param allValueMap
     * @param screeningConditionDTO
     *@param industryToCodeMap @return
     */
    private HashSet<String> getResultSet(HashMap<String, Double> allValueMap, ScreeningConditionDTO screeningConditionDTO,  HashMap<String, HashSet<String>> industryToCodeMap) {
        //在调用的时候需要判断这个resultset是不是空的
        HashSet<String> resultSet=new HashSet<String>();

        double firstValue;
        double secondValue;

        if(screeningConditionDTO.getCompareSymbol().equals("大于")){
            firstValue=screeningConditionDTO.getFirstValue();

            for (Map.Entry<String, Double> entry : allValueMap.entrySet()) {
                if(entry.getValue()>firstValue){
                    resultSet.add(entry.getKey());
                }
            }



        }else if(screeningConditionDTO.getCompareSymbol().equals("小于")){

            firstValue=screeningConditionDTO.getFirstValue();
            for (Map.Entry<String, Double> entry : allValueMap.entrySet()) {
                if(entry.getValue()<firstValue){
                    resultSet.add(entry.getKey());
                }
            }



        }else if(screeningConditionDTO.getCompareSymbol().equals("等于")){

            firstValue=screeningConditionDTO.getFirstValue();
            for (Map.Entry<String, Double> entry : allValueMap.entrySet()) {
                if(entry.getValue()==firstValue){
                    resultSet.add(entry.getKey());
                }
            }

        }else if(screeningConditionDTO.getCompareSymbol().equals("区间")){

            firstValue=screeningConditionDTO.getFirstValue();
            secondValue=screeningConditionDTO.getSecondValue();

            //调整使得firstValue<secondValue
            if(firstValue>secondValue){
                double temp=firstValue;
                firstValue=secondValue;
                secondValue=temp;
            }

            for (Map.Entry<String, Double> entry : allValueMap.entrySet()) {
                if(firstValue<=entry.getValue()&&entry.getValue()<=secondValue){
                    resultSet.add(entry.getKey());
                }
            }

        }else if(screeningConditionDTO.getCompareSymbol().equals("排名最大")){
            //firstValue是double类型的前N名
            firstValue=screeningConditionDTO.getFirstValue();

            //realNumber是向上取整的整数
            int realNumber= NumberConvert.doubleToBiggerInt(firstValue);

            //每个行业中的前几名
            if(screeningConditionDTO.getScope().equals("行业内")){
                //遍历全部的行业
                for (Map.Entry<String, HashSet<String>> entry : industryToCodeMap.entrySet()) {
//                    String industryName=entry.getKey();
                    HashSet<String> oneIndustryAllStockSet=entry.getValue();

                    ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                    for(String str:oneIndustryAllStockSet){
                        if(allValueMap.containsKey(str)){
                            waveDTO wavedto=new waveDTO();
                            wavedto.setStockCode(str);
                            wavedto.setChangePercent(allValueMap.get(str));
                            waveDTOArrayList.add(wavedto);
                        }else{
                            continue;
                        }

                    }

                    //对这个waveDTO进行排名 获得需要的String的list 然后把他们添加到resultSet中
                    ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                    targetNList=getTopNCodesByList(realNumber,waveDTOArrayList);

                    //如果有一个list为null是不是会出问题！
                    if(targetNList.size()!=0){
                        for(int count=0;count<targetNList.size();count++){
                            resultSet.add(targetNList.get(count).getStockCode());
                        }
                    }


                }

            }else{
                //当天全部有信息的股票
                ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();
                //遍历allValueMap
                for (Map.Entry<String, Double> entry : allValueMap.entrySet()) {
                    waveDTO wavedto=new waveDTO();
                    wavedto.setStockCode(entry.getKey());
                    wavedto.setChangePercent(entry.getValue());

                    waveDTOArrayList.add(wavedto);
                }

                ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                targetNList=getTopNCodesByList(realNumber,waveDTOArrayList);

                if(targetNList.size()!=0){
                    for(int count=0;count<targetNList.size();count++){
                        resultSet.add(targetNList.get(count).getStockCode());
                    }
                }

            }




        }else if(screeningConditionDTO.getCompareSymbol().equals("排名最小")){


            firstValue=screeningConditionDTO.getFirstValue();

            int realNumber=NumberConvert.doubleToBiggerInt(firstValue);

            //每个行业中的前几名
            if(screeningConditionDTO.getScope().equals("行业内")){
                //遍历全部的行业
                for (Map.Entry<String, HashSet<String>> entry : industryToCodeMap.entrySet()) {
//                    String industryName=entry.getKey();
                    HashSet<String> oneIndustryAllStockSet=entry.getValue();

                    ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                    for(String str:oneIndustryAllStockSet){
                        if(allValueMap.containsKey(str)){
                            waveDTO wavedto=new waveDTO();
                            wavedto.setStockCode(str);
                            wavedto.setChangePercent(allValueMap.get(str));
                            waveDTOArrayList.add(wavedto);
                        }else{
                            continue;
                        }

                    }

                    //对这个waveDTO进行排名 获得需要的String的list 然后把他们添加到resultSet中
                    ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                    targetNList=getLastNCodesByList(realNumber,waveDTOArrayList);

                    //如果有一个list为null是不是会出问题！


                    if(targetNList.size()!=0){
                        for(int count=0;count<targetNList.size();count++){
                            resultSet.add(targetNList.get(count).getStockCode());
                        }
                    }


                }

            }else{
                //当天全部有信息的股票
                ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();
                //遍历allValueMap
                for (Map.Entry<String, Double> entry : allValueMap.entrySet()) {
                    waveDTO wavedto=new waveDTO();
                    wavedto.setStockCode(entry.getKey());
                    wavedto.setChangePercent(entry.getValue());

                    waveDTOArrayList.add(wavedto);
                }

                ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                targetNList=getLastNCodesByList(realNumber,waveDTOArrayList);

                if(targetNList.size()!=0){
                    for(int count=0;count<targetNList.size();count++){
                        resultSet.add(targetNList.get(count).getStockCode());
                    }
                }

            }




        }else if(screeningConditionDTO.getCompareSymbol().equals("排名区间")){

            firstValue=screeningConditionDTO.getFirstValue();
            secondValue=screeningConditionDTO.getSecondValue();

            int realSmallNumber=NumberConvert.doubleToSmallerInt(firstValue);
            int realBigNumber=NumberConvert.doubleToBiggerInt(secondValue);


            if(screeningConditionDTO.getScope().equals("行业内")){

                for(Map.Entry<String,HashSet<String>> entry:industryToCodeMap.entrySet()){
                    HashSet<String> oneIndustryAllStockSet=entry.getValue();

                    ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                    for(String str:oneIndustryAllStockSet){
                        if(allValueMap.containsKey(str)){
                            waveDTO wavedto=new waveDTO();
                            wavedto.setStockCode(str);
                            wavedto.setChangePercent(allValueMap.get(str));
                            waveDTOArrayList.add(wavedto);


                        }else{
                            continue;
                        }
                    }

                    ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                    targetNList=getBetweenCodesByList(realSmallNumber,realBigNumber,waveDTOArrayList);

                    if(targetNList.size()!=0){
                        for(int count=0;count<targetNList.size();count++){
                            resultSet.add(targetNList.get(count).getStockCode());
                        }
                    }


                }




            }else{

                ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();
                for(Map.Entry<String,Double> entry:allValueMap.entrySet()){
                    waveDTO wavedto=new waveDTO();
                    wavedto.setStockCode(entry.getKey());
                    wavedto.setChangePercent(entry.getValue());

                    waveDTOArrayList.add(wavedto);


                }

                ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                targetNList=getBetweenCodesByList(realSmallNumber,realBigNumber,waveDTOArrayList);

                if(targetNList.size()!=0){
                    for(int count=0;count<targetNList.size();count++){
                        resultSet.add(targetNList.get(count).getStockCode());
                    }
                }



            }




        }else if(screeningConditionDTO.getCompareSymbol().equals("排名%最大")){
            //这里的firstValue是90.1代表是90.1%也就是0.901
            firstValue=screeningConditionDTO.getFirstValue();

            //这里的realNumber的取值在行业内的和全部的时候是不一样的

            if(screeningConditionDTO.getScope().equals("行业内")){
                for(Map.Entry<String,HashSet<String>> entry:industryToCodeMap.entrySet()){
                    HashSet<String> oneIndustryAllStockSet=entry.getValue();

                    int realNumber=NumberConvert.doubleToBiggerInt(oneIndustryAllStockSet.size()*firstValue/100.0);

                    ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                    for(String str:oneIndustryAllStockSet){
                        if(allValueMap.containsKey(str)){
                            waveDTO wavedto=new waveDTO();
                            wavedto.setStockCode(str);
                            wavedto.setChangePercent(allValueMap.get(str));
                            waveDTOArrayList.add(wavedto);

                        }else {
                            continue;
                        }
                    }

                    ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                    targetNList=getTopNCodesByList(realNumber,waveDTOArrayList);

                    if(targetNList.size()!=0){
                        for(int count=0;count<targetNList.size();count++){
                            resultSet.add(targetNList.get(count).getStockCode());
                        }
                    }




                }
            }else{


                int realNumber=NumberConvert.doubleToBiggerInt(allValueMap.size()*firstValue/100.0);

                ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                for(Map.Entry<String,Double> entry:allValueMap.entrySet()){
                    waveDTO wavedto=new waveDTO();
                    wavedto.setStockCode(entry.getKey());
                    wavedto.setChangePercent(entry.getValue());
                    waveDTOArrayList.add(wavedto);
                }

                ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                targetNList=getTopNCodesByList(realNumber,waveDTOArrayList);

                if(targetNList.size()!=0){
                    for(int count=0;count<targetNList.size();count++){
                        resultSet.add(targetNList.get(count).getStockCode());
                    }
                }


            }


        }else if(screeningConditionDTO.getCompareSymbol().equals("排名%最小")){

            //这里的firstValue是90.1代表是90.1%也就是0.901
            firstValue=screeningConditionDTO.getFirstValue();

            //这里的realNumber的取值在行业内的和全部的时候是不一样的

            if(screeningConditionDTO.getScope().equals("行业内")){
                for(Map.Entry<String,HashSet<String>> entry:industryToCodeMap.entrySet()){
                    HashSet<String> oneIndustryAllStockSet=entry.getValue();

                    int realNumber=NumberConvert.doubleToBiggerInt(oneIndustryAllStockSet.size()*firstValue/100.0);

                    ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                    for(String str:oneIndustryAllStockSet){
                        if(allValueMap.containsKey(str)){
                            waveDTO wavedto=new waveDTO();
                            wavedto.setStockCode(str);
                            wavedto.setChangePercent(allValueMap.get(str));
                            waveDTOArrayList.add(wavedto);

                        }else {
                            continue;
                        }
                    }

                    ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                    targetNList=getLastNCodesByList(realNumber,waveDTOArrayList);

                    if(targetNList.size()!=0){
                        for(int count=0;count<targetNList.size();count++){
                            resultSet.add(targetNList.get(count).getStockCode());
                        }
                    }




                }
            }else{


                int realNumber=NumberConvert.doubleToBiggerInt(allValueMap.size()*firstValue/100.0);

                ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                for(Map.Entry<String,Double> entry:allValueMap.entrySet()){
                    waveDTO wavedto=new waveDTO();
                    wavedto.setStockCode(entry.getKey());
                    wavedto.setChangePercent(entry.getValue());
                    waveDTOArrayList.add(wavedto);
                }

                ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                targetNList=getLastNCodesByList(realNumber,waveDTOArrayList);

                if(targetNList.size()!=0){
                    for(int count=0;count<targetNList.size();count++){
                        resultSet.add(targetNList.get(count).getStockCode());
                    }
                }


            }


        }else if(screeningConditionDTO.getCompareSymbol().equals("排名%区间")){
            firstValue=screeningConditionDTO.getFirstValue();
            secondValue=screeningConditionDTO.getSecondValue();

            if(screeningConditionDTO.getScope().equals("行业内")){
                for(Map.Entry<String,HashSet<String>> entry:industryToCodeMap.entrySet()){
                    HashSet<String> oneIndustryAllStockSet=entry.getValue();

                    int realSmallNumber=NumberConvert.doubleToSmallerInt(oneIndustryAllStockSet.size()*firstValue/100.0);
                    int realBigNumber=NumberConvert.doubleToBiggerInt(oneIndustryAllStockSet.size()*secondValue/100.0);

                    ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();

                    for(String str:oneIndustryAllStockSet){
                        if(allValueMap.containsKey(str)){
                            waveDTO wavedto=new waveDTO();
                            wavedto.setStockCode(str);
                            wavedto.setChangePercent(allValueMap.get(str));
                            waveDTOArrayList.add(wavedto);

                        }else{
                            continue;
                        }

                    }

                    ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                    targetNList=getBetweenCodesByList(realSmallNumber,realBigNumber,waveDTOArrayList);

                    if(targetNList.size()!=0){
                        for(int count=0;count<targetNList.size();count++){
                            resultSet.add(targetNList.get(count).getStockCode());
                        }
                    }


                }




            }else{

                int realSmallNumber=NumberConvert.doubleToSmallerInt(allValueMap.size()*firstValue/100.0);
                int realBigNumber=NumberConvert.doubleToBiggerInt(allValueMap.size()*secondValue/100.0);

                ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();
                for(Map.Entry<String,Double> entry:allValueMap.entrySet()){
                    waveDTO wavedto=new waveDTO();
                    wavedto.setStockCode(entry.getKey());
                    wavedto.setChangePercent(entry.getValue());
                    waveDTOArrayList.add(wavedto);


                }


                ArrayList<waveDTO> targetNList=new ArrayList<waveDTO>();
                targetNList=getBetweenCodesByList(realSmallNumber,realBigNumber,waveDTOArrayList);

                if(targetNList.size()!=0){
                    for(int count=0;count<targetNList.size();count++){
                        resultSet.add(targetNList.get(count).getStockCode());
                    }
                }



            }


        }


        return resultSet;
    }

    @Override
    public String getCurrentTime() {
        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now=new java.util.Date();
        String rq=myFmt1.format(now);

        return rq;
    }

    /**
     * id=userId+" "+dateTime
     * @param strategyId
     * @return
     */
    @Override
    public CustomizeStrategyDTO getOneStrategy(String strategyId) {


        CustomizeStrategy customizeStrategy=strategyMapper.selectCustomizeStrategy(strategyId);
        ArrayList<ScreenCondition> screenConditionArrayList=strategyMapper.selectStrategyAllCondition(strategyId);

        CustomizeStrategyDTO customizeStrategyDTO=new CustomizeStrategyDTO(customizeStrategy,screenConditionArrayList);

        return customizeStrategyDTO;
    }

    //添加数据的时候需要对两个表进行操作
    @Override
    public boolean insertOneStrategy(CustomizeStrategyDTO customizeStrategyDTO) {

        ArrayList<CustomizeStrategy> customizeStrategyArrayList=strategyMapper.getAllCustomizeStrategy();
        HashSet<String> allStrategyIdSet=new HashSet<String>();

        for(int count=0;count<customizeStrategyArrayList.size();count++){
            allStrategyIdSet.add(customizeStrategyArrayList.get(count).getStrategyId());
        }

        if(allStrategyIdSet.contains(customizeStrategyDTO.getStrategyID())){
            return false;
        }else{

            CustomizeStrategy customizeStrategy=new CustomizeStrategy(customizeStrategyDTO);

            strategyMapper.insertCustomizeStrategy(customizeStrategy);


            ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList=customizeStrategyDTO.getScreeningConditionDTOArrayList();

            for(int count=0;count<screeningConditionDTOArrayList.size();count++){
                ScreeningConditionDTO screeningConditionDTO=screeningConditionDTOArrayList.get(count);

                ScreenCondition screenCondition=new ScreenCondition(screeningConditionDTO);

                strategyMapper.insertStrategyrAllCondition(screenCondition);


            }

            return true;
        }


    }

    @Override
    public boolean deleteOneStrategy(String strategyId) {
        ArrayList<CustomizeStrategy> customizeStrategyArrayList=strategyMapper.getAllCustomizeStrategy();

        HashSet<String> allStrategySet=new HashSet<String>();
        for(int count=0;count<customizeStrategyArrayList.size();count++){
            allStrategySet.add(customizeStrategyArrayList.get(count).getStrategyId());
        }

        if(allStrategySet.contains(strategyId)){
            strategyMapper.deleteCustomizeStrategy(strategyId);
            strategyMapper.deleteStrategyAllCondition(strategyId);

            return true;
        }else{
            return false;
        }


    }

    @Override
    public boolean deleteUserAllStrategy(String userId) {

        ArrayList<CustomizeStrategy> allStrategyList=strategyMapper.getAllCustomizeStrategy();

        for(int count=0;count<allStrategyList.size();count++){
            String strategyId=allStrategyList.get(count).getStrategyId();

            if(strategyId.split(" ")[0].equals(userId)){
                strategyMapper.deleteStrategyAllCondition(strategyId);
                strategyMapper.deleteCustomizeStrategy(strategyId);



            }



        }
        return true;
    }

    @Override
    public ArrayList<CustomizeStrategyDTO> getAllCustomizeStrategy() {
        ArrayList<CustomizeStrategyDTO> strategyDTOArrayList=new ArrayList<CustomizeStrategyDTO>();

        ArrayList<CustomizeStrategy> strategyArrayList=strategyMapper.getAllCustomizeStrategy();

        ArrayList<ScreenCondition> screenConditionArrayList=strategyMapper.getAllScreenCondition();
        //      策略id  策略对应的筛选条件
        HashMap<String,ArrayList<ScreenCondition>> conditionMap=new HashMap<String, ArrayList<ScreenCondition>>();
        for(int count=0;count<screenConditionArrayList.size();count++){
            String strategyId=screenConditionArrayList.get(count).getStrategyId();

            if(conditionMap.containsKey(strategyId)){
                conditionMap.get(strategyId).add(screenConditionArrayList.get(count));
            }else{
                ArrayList<ScreenCondition> newList=new ArrayList<ScreenCondition>();
                conditionMap.put(strategyId,newList);
                conditionMap.get(strategyId).add(screenConditionArrayList.get(count));

            }
        }


        for(int count=0;count<strategyArrayList.size();count++){
            String strategyId=strategyArrayList.get(count).getStrategyId();

            CustomizeStrategy customizeStrategy=strategyArrayList.get(count);
            ArrayList<ScreenCondition> screenConditions=new ArrayList<ScreenCondition>();

            if(conditionMap.containsKey(strategyId)){
                screenConditions=conditionMap.get(strategyId);
            }

            CustomizeStrategyDTO customizeStrategyDTO=new CustomizeStrategyDTO(customizeStrategy,screenConditions);

            strategyDTOArrayList.add(customizeStrategyDTO);


        }
        return strategyDTOArrayList;
    }

    @Override
    public ArrayList<CustomizeStrategyDTO> getOneUserAllStrategy(String userID) {
        ArrayList<CustomizeStrategyDTO> resultList=new ArrayList<CustomizeStrategyDTO>();

        ArrayList<CustomizeStrategyDTO> allList=getAllCustomizeStrategy();
        for(int count=0;count<allList.size();count++){
            if(allList.get(count).getCreaterID().equals(userID)){
                resultList.add(allList.get(count));
            }
        }

        return resultList;
    }


    /**
     * 该方法通过两天的股票的HashMap来得到
     * @param n 前n名
     * @param lastMap 之前某日的全部股票信息
     * @param nowMap 当日的全部股票信息
     * @param validCodeList 当日有效的全部股票编号
     * @return
     */
    public ArrayList<waveDTO> getTopNCodesByHashmap(int n, HashMap<String,DayKLine> lastMap, HashMap<String,DayKLine> nowMap, ArrayList<String> validCodeList){
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
     * 获取一个list中的前n名
     * @param n
     * @param waveDTOArrayList
     * @return
     */
    private ArrayList<waveDTO> getTopNCodesByList(int n, ArrayList<waveDTO> waveDTOArrayList) {
        ArrayList<waveDTO> resultCodes=new ArrayList<waveDTO>();

        Collections.sort(waveDTOArrayList,COMPARATOR);
        if(n>waveDTOArrayList.size()){
            resultCodes=waveDTOArrayList;
        }else{
            for(int count=0;count<n;count++){
                resultCodes.add(waveDTOArrayList.get(count));
            }
        }

        return resultCodes;
    }

    /**
     * 获取一个list中的后n名
     * @param n
     * @param waveDTOArrayList
     * @return
     */
    private ArrayList<waveDTO> getLastNCodesByList(int n,ArrayList<waveDTO> waveDTOArrayList){
        ArrayList<waveDTO> resultCodes=new ArrayList<waveDTO>();

        Collections.sort(waveDTOArrayList,COMPARATOR1);
        if(n>waveDTOArrayList.size()){
            resultCodes=waveDTOArrayList;
        }else{
            for(int count=0;count<n;count++){
                resultCodes.add(waveDTOArrayList.get(count));
            }
        }

        return resultCodes;
    }

    /**
     * 获取一个list中的smallN-bigN名
     * @param smallN
     * @param bigN
     * @param waveDTOArrayList
     * @return
     */
    private ArrayList<waveDTO> getBetweenCodesByList(int smallN,int bigN,ArrayList<waveDTO> waveDTOArrayList){
        ArrayList<waveDTO> resultCodes=new ArrayList<waveDTO>();

        Collections.sort(waveDTOArrayList,COMPARATOR);

        if(smallN>waveDTOArrayList.size()){
            //如果不符合的话这个的size是0
        }else{
            if(bigN>waveDTOArrayList.size()){
                for(int count=smallN-1;count<waveDTOArrayList.size();count++){
                    resultCodes.add(waveDTOArrayList.get(count));
                }
            }else{

                for(int count=smallN-1;count<=bigN-1;count++){
                    resultCodes.add(waveDTOArrayList.get(count));
                }

            }

        }


        return resultCodes;

    }
    //写一个顺序排列的方法和一个倒序排序的方法

    //编写Comparator,根据WaveVO的changePercent对waveVO进行顺序排序
    private static final Comparator<waveDTO> COMPARATOR = new Comparator<waveDTO>() {
        public int compare(waveDTO o1, waveDTO o2) {
            return o1.compareTo(o2);//运用User类的compareTo方法比较两个对象
        }
    };

    //编写Comparator1,根据WaveVO的changePercent对waveVO进行倒序排序
    private static final Comparator<waveDTO> COMPARATOR1= new Comparator<waveDTO>() {
        public int compare(waveDTO o1, waveDTO o2) {
            return o2.compareTo(o1);//运用User类的compareTo方法比较两个对象
        }
    };

}
