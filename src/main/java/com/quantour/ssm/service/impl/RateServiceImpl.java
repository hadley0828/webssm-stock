package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.RateMapper;
import com.quantour.ssm.dto.klineDTO;
import com.quantour.ssm.dto.stockRate.*;
import com.quantour.ssm.model.*;
import com.quantour.ssm.service.RateService;
import com.quantour.ssm.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

/**
 * Created by zhangzy on 2017/6/3.
 * 评级功能
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RateServiceImpl implements RateService{

    @Resource
    private RateMapper rateMapper;
    @Resource
    private DayKLineMapper dayKLineMapper;

    @Override
    public GeneralScoreDTO getOneStockGeneralScore(String code,String date) {
        GeneralScoreDTO generalScoreDTO=new GeneralScoreDTO();


        ArrayList<StockBasicInfo> allStockInfoList= (ArrayList<StockBasicInfo>) dayKLineMapper.getAllStockInfos();
        HashMap<String,String> codeToNameMap=new HashMap<String, String>();
        for(int count=0;count<allStockInfoList.size();count++){
            codeToNameMap.put(allStockInfoList.get(count).getCode(),allStockInfoList.get(count).getName());
        }


        TechnicalDTO technicalDTO=getOneStockTechnicalScore(code,date);
        CapitalDTO capitalDTO=getOneStockCapitalScore(code,date);
        MessageDTO messageDTO=getOneStockMessageScore(code,date);
        IndustryDTO industryDTO=getOneStockIndustryScore(code,date);
        BasicDTO basicDTO=getOneStockBasicScore(code,date);

        generalScoreDTO.setStockCode(code);
        generalScoreDTO.setStockName(codeToNameMap.get(code));
        generalScoreDTO.setTotalScore(NumberConvert.saveNDouble(0.3*technicalDTO.getTechnicalScore()+0.3*capitalDTO.getCapitalScore()+0.1*messageDTO.getMessageScore()+0.15*industryDTO.getIndustryScore()+0.15*basicDTO.getBasicScore(),2));

        double totalScore=generalScoreDTO.getTotalScore();
        if(0<=totalScore&&totalScore<2){
            generalScoreDTO.setSuggestion("卖出");
        }else if(2<=totalScore&&totalScore<4){
            generalScoreDTO.setSuggestion("减持");
        }else if(4<=totalScore&&totalScore<6){
            generalScoreDTO.setSuggestion("中性");
        }else if(6<=totalScore&&totalScore<8){
            generalScoreDTO.setSuggestion("增持");
        }else if(8<=totalScore&&totalScore<=10){
            generalScoreDTO.setSuggestion("买入");
        }

        generalScoreDTO.setTechnicalScore(technicalDTO.getTechnicalScore());
        generalScoreDTO.setCapitalScore(capitalDTO.getCapitalScore());
        generalScoreDTO.setMessageScore(messageDTO.getMessageScore());
        generalScoreDTO.setIndustryScore(industryDTO.getIndustryScore());
        generalScoreDTO.setBasicScore(basicDTO.getBasicScore());

        generalScoreDTO.setTechnicalDTO(technicalDTO);
        generalScoreDTO.setCapitalDTO(capitalDTO);
        generalScoreDTO.setMessageDTO(messageDTO);
        generalScoreDTO.setIndustryDTO(industryDTO);
        generalScoreDTO.setBasicDTO(basicDTO);



        return generalScoreDTO;
    }

    @Override
    public void calculateOneDayStockScore(String date) {

    }

    @Override
    public TechnicalDTO getOneStockTechnicalScore(String code, String date) {
        TechnicalDTO technicalDTO=new TechnicalDTO();

        //获取有意义的日期
        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayKLineMapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realDate=DateConvert.getRealEndDate(date,allDateList);


        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("code",code);
        map.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realDate,10)));
        map.put("end",Date.valueOf(realDate));
        ArrayList<DayKLine> timesStockList= (ArrayList<DayKLine>) dayKLineMapper.getTimesDayKLines(map);


        HashMap<String,DayKLine> timeStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<timesStockList.size();count++){
            timeStockMap.put(DateConvert.dateToString(timesStockList.get(count).getStockDate()),timesStockList.get(count));
        }



        String blockCode="sh000001";
        if(code.charAt(0)=='3'||code.charAt(0)=='0'){
            blockCode="sz399001";
        }

        HashMap<String,Object> newMap = new HashMap<String, Object>();

        newMap.put("block", blockCode);
        newMap.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realDate,10)));
        newMap.put("end",Date.valueOf(realDate));

        ArrayList<DayKLine> timesBlockList= (ArrayList<DayKLine>) dayKLineMapper.getTimesBlockInfo(newMap);

        HashMap<String,DayKLine> timeBlockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<timesBlockList.size();count++){
            timeBlockMap.put(DateConvert.dateToString(timesBlockList.get(count).getStockDate()),timesBlockList.get(count));
        }

        ArrayList<Technical_mapDTO> technicalMapDTOArrayList=new ArrayList<Technical_mapDTO>();


        double oneDayVolume=0.0;
        double fiveDayVolume=0.0;
        double tenDayVolume=0.0;


        //timeStockMap timeBlockMap
        for(int count=9;count>=0;count--){
            String currentDate=DateConvert.getLastNDate(allDateList,realDate,count);
            String lastDate=DateConvert.getLastNDate(allDateList,realDate,count+1);

            double blockChangePercent=0.0;
            double stockChangePercent=0.0;

            double oneVolume=0.0;
            if(timeStockMap.containsKey(currentDate)){
                oneVolume=timeStockMap.get(currentDate).getVolume();
            }

            if(count==0){
                oneDayVolume=oneDayVolume+oneVolume;
            }

            if(0<=count&&count<=4){
                fiveDayVolume=fiveDayVolume+oneVolume;
            }

            tenDayVolume=tenDayVolume+oneVolume;



            if(timeBlockMap.containsKey(currentDate)&&timeBlockMap.containsKey(lastDate)){
                double lastPrice=timeBlockMap.get(lastDate).getClosePrice();
                double nowPrice=timeBlockMap.get(currentDate).getClosePrice();

                blockChangePercent=StockCalculator.getIncrease(lastPrice,nowPrice);


            }

            if(timeStockMap.containsKey(currentDate)&&timeStockMap.containsKey(lastDate)){
                double lastPrice=timeStockMap.get(lastDate).getClosePrice();
                double nowPrice=timeStockMap.get(currentDate).getClosePrice();

                stockChangePercent=StockCalculator.getIncrease(lastPrice,nowPrice);
            }


            Technical_mapDTO technicalMapDTO=new Technical_mapDTO();
            technicalMapDTO.setDate(currentDate);
            technicalMapDTO.setBlockChangePercent(NumberConvert.saveNDouble(blockChangePercent,5));
            technicalMapDTO.setStockChangePercent(NumberConvert.saveNDouble(stockChangePercent,5));

            technicalMapDTOArrayList.add(technicalMapDTO);

        }




        double averageDayChangePercent=0.0;     //平均每天的涨跌幅
        double averageMinusChangePercent=0.0;   //平均每天和大盘差值的涨跌幅
        double totalOne=0.0;
        double totalTwo=0.0;
        //指标一
        double nowChangePercent=technicalMapDTOArrayList.get(technicalMapDTOArrayList.size()-1).getStockChangePercent();

        for(int count=0;count<technicalMapDTOArrayList.size();count++){
            double stockChangePercent=technicalMapDTOArrayList.get(count).getStockChangePercent();
            double blockChangePercent=technicalMapDTOArrayList.get(count).getBlockChangePercent();

            totalOne=totalOne+stockChangePercent;
            totalTwo=totalTwo+stockChangePercent-blockChangePercent;

        }
        //指标二
        averageDayChangePercent=totalOne/10.0;

        //指标三
        averageMinusChangePercent=totalTwo/10.0;


        //指标四 指标五 指标六
        double one=nowChangePercent;
        double two=averageDayChangePercent;
        double three=averageMinusChangePercent;
        double four=oneDayVolume;
        double five=fiveDayVolume/5.0;
        double six=tenDayVolume/10.0;

        double partScore=30*(one+0.1)+10*(two+0.1)+20*(three+0.1)+5*five/four+5*six/four;

        StockScore stockScore=rateMapper.getOneStockScore(code);
        double thisScore=stockScore.getTechnicalScore();

        ArrayList<StockScore> allStockScore= (ArrayList<StockScore>) rateMapper.getAllStockScore();
        ArrayList<Double> oneScoreList=new ArrayList<Double>();

        for(int count=0;count<allStockScore.size();count++){
            oneScoreList.add(allStockScore.get(count).getTechnicalScore());
        }

        //0-10分中给分
        double technicalScore=getOneScore(thisScore,oneScoreList);


        technicalDTO.setTechnicalScore(NumberConvert.saveNDouble(technicalScore,2));
        technicalDTO.setPartScore(partScore);
        technicalDTO.setDefeatPercent(NumberConvert.doubleToBiggerInt(technicalScore*10.0));
        technicalDTO.setKlineDTOArrayList(getKline(code,DateConvert.getLastNDate(allDateList,realDate,200),realDate));
        technicalDTO.setTechnicalMapDTOArrayList(JsonConvert.markerlineConvert(technicalMapDTOArrayList));
        technicalDTO.setOneDayVolume(oneDayVolume);
        technicalDTO.setFiveDayVolume(fiveDayVolume/5.0);
        technicalDTO.setTenDayVolume(tenDayVolume/10.0);



        return technicalDTO;
    }

    private double getOneScore(double thisScore, ArrayList<Double> oneScoreList) {
        int totalNumber=oneScoreList.size();
        int defeatNumber=0;

        for(int count=0;count<oneScoreList.size();count++){
            if(thisScore>=oneScoreList.get(count)){
                defeatNumber++;
            }
        }


        return 10.0*defeatNumber/totalNumber;
    }

    @Override
    public CapitalDTO getOneStockCapitalScore(String code, String date) {
        CapitalDTO capitalDTO=new CapitalDTO();


        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayKLineMapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realDate=DateConvert.getRealEndDate(date,allDateList);



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

        //当前股票所属的行业名称
        String thisStockIndustry=codeToIndustryMap.get(code);


        ArrayList<ProfessionFundFlows> professionFundFlowsArrayList= (ArrayList<ProfessionFundFlows>) rateMapper.getAllProfessionFundFlows();
        //      行业名称         日期  行业资金流向
        HashMap<String,HashMap<String,Double>> allProfessionFlowMap=new HashMap<String, HashMap<String, Double>>();

        for(int count=0;count<professionFundFlowsArrayList.size();count++){
            String industryName=professionFundFlowsArrayList.get(count).getIndustry();
            String oneDate=DateConvert.dateToString(professionFundFlowsArrayList.get(count).getDate());
            double oneFlow=Double.valueOf(professionFundFlowsArrayList.get(count).getInflow());

            if(!allProfessionFlowMap.containsKey(industryName)){
                HashMap<String,Double> newMap=new HashMap<String, Double>();
                allProfessionFlowMap.put(industryName,newMap);
                allProfessionFlowMap.get(industryName).put(oneDate,oneFlow);
            }else{
                allProfessionFlowMap.get(industryName).put(oneDate,oneFlow);
            }

        }

        //这个股票同行业的全部股票编号
        HashSet<String> oneStockSameKindSet=industryToCodeMap.get(thisStockIndustry);


        ArrayList<SingleStockFundFlows> allSingleFlowList= (ArrayList<SingleStockFundFlows>) rateMapper.getAllSingleStockFundFlows();
        //      股票编号        日期    个股资金流向
        HashMap<String,HashMap<String,Double>> allSingleFlowMap=new HashMap<String, HashMap<String, Double>>();

        for(int count=0;count<allSingleFlowList.size();count++){

            String stockCode=allSingleFlowList.get(count).getCode();
            String oneDate=DateConvert.dateToString(allSingleFlowList.get(count).getDate());
            double oneFlow=Double.valueOf(allSingleFlowList.get(count).getInflow());

            if(!allSingleFlowMap.containsKey(stockCode)){

                HashMap<String,Double> newMap=new HashMap<String, Double>();
                allSingleFlowMap.put(stockCode,newMap);
                allSingleFlowMap.get(stockCode).put(oneDate,oneFlow);

            }else{

                allSingleFlowMap.get(stockCode).put(oneDate,oneFlow);
            }

        }

        double singleOneFlow=0.0;
        double singleFiveFlow=0.0;
        double singleTenFlow=0.0;
        double singleTwentyFlow=0.0;

        double industryOneFlow=0.0;
        double industryFiveFlow=0.0;
        double industryTenFlow=0.0;
        double industryTwentyFlow=0.0;

        ArrayList<FundFlowMapDTO> allFlowMapList=new ArrayList<FundFlowMapDTO>();


        HashMap<String,Double> oneStockSingleMap=allSingleFlowMap.get(code);
        if(oneStockSingleMap==null){
            oneStockSingleMap=new HashMap<String, Double>();
        }
        HashMap<String,Double> oneIndustryMap=allProfessionFlowMap.get(thisStockIndustry);
        if(oneIndustryMap==null){
            oneIndustryMap=new HashMap<String, Double>();
        }


        for(int count=19;count>=0;count--){
            String currentDate=DateConvert.getLastNDate(allDateList,realDate,count);

            FundFlowMapDTO fundFlowMapDTO=new FundFlowMapDTO();


            double oneDayIndustryTotalFlow=0.0;

//            System.out.println(oneStockSingleMap);
//            System.out.println(oneStockSingleMap.size());
//            System.out.println(oneIndustryMap);
//            System.out.println(oneIndustryMap.size());

            if(oneStockSingleMap.containsKey(currentDate)){
                if(count==0){
                    //单日的个股资金流向和行业资金流向
                    singleOneFlow=singleOneFlow+oneStockSingleMap.get(currentDate);
                }

                if(0<=count&&count<=4){
                    //近五日的个股资金流向和行业资金流向
                    singleFiveFlow=singleFiveFlow+oneStockSingleMap.get(currentDate);
                }

                if(0<=count&&count<=9){
                    //近十日的个股资金流向和行业资金流向
                    singleTenFlow=singleTenFlow+oneStockSingleMap.get(currentDate);
                }

                //近二十日的个股资金流向和行业资金流向
                singleTwentyFlow=singleTwentyFlow+oneStockSingleMap.get(currentDate);
            }

            if(oneIndustryMap.containsKey(currentDate)){
                if(count==0){
                    //单日的个股资金流向和行业资金流向
                    industryOneFlow=industryOneFlow+oneIndustryMap.get(currentDate);
                }

                if(0<=count&&count<=4){
                    //近五日的个股资金流向和行业资金流向
                    industryFiveFlow=industryFiveFlow+oneIndustryMap.get(currentDate);
                }

                if(0<=count&&count<=9){
                    //近十日的个股资金流向和行业资金流向
                    industryTenFlow=industryTenFlow+oneIndustryMap.get(currentDate);
                }

                //近二十日的个股资金流向和行业资金流向
                industryTwentyFlow=industryTwentyFlow+oneIndustryMap.get(currentDate);



            }




//            System.out.println(oneStockSameKindSet.size());

//            System.out.println(oneStockSameKindSet);

            if(oneStockSameKindSet==null){
                oneDayIndustryTotalFlow=oneDayIndustryTotalFlow+0.0;
            }else{
                for (String str : oneStockSameKindSet) {

                    if(allSingleFlowMap.containsKey(str)){
                        if(allSingleFlowMap.get(str).containsKey(currentDate)){
                            oneDayIndustryTotalFlow=oneDayIndustryTotalFlow+allSingleFlowMap.get(str).get(currentDate);
                        }
                    }
                }
            }

            double oneDayIndustryAverageFlow=0.0;

            if(oneStockSameKindSet==null){
                oneDayIndustryAverageFlow=0.0;
            }else{
                oneDayIndustryAverageFlow=oneDayIndustryTotalFlow/oneStockSameKindSet.size();
            }


            fundFlowMapDTO.setDate(currentDate);

            if(oneStockSingleMap.containsKey(currentDate)){
                fundFlowMapDTO.setSingleFlow(NumberConvert.saveNDouble(oneStockSingleMap.get(currentDate)/10000.0,1));
            }else{
                fundFlowMapDTO.setSingleFlow(0.0);
            }
            fundFlowMapDTO.setIndustryAverageFlow(NumberConvert.saveNDouble(oneDayIndustryAverageFlow/10000.0,1));

            allFlowMapList.add(fundFlowMapDTO);

        }




        StockScore stockScore=rateMapper.getOneStockScore(code);
        double thisScore=stockScore.getCapitalScore();

        ArrayList<StockScore> allStockScore= (ArrayList<StockScore>) rateMapper.getAllStockScore();
        ArrayList<Double> oneScoreList=new ArrayList<Double>();

        for(int count=0;count<allStockScore.size();count++){
            oneScoreList.add(allStockScore.get(count).getCapitalScore());
        }

        //0-10分中给分
        double CapitalScore=getOneScore(thisScore,oneScoreList);






        capitalDTO.setCapitalScore(NumberConvert.saveNDouble(CapitalScore,2));

        capitalDTO.setDefeatPercent(NumberConvert.doubleToBiggerInt(CapitalScore*10.0));

        capitalDTO.setFlowMapList(JsonConvert.capitalLineConvert(allFlowMapList));

        capitalDTO.setTodayStockFlow(NumberConvert.saveNDouble(singleOneFlow/10000.0,1));
        capitalDTO.setFiveStockFlow(NumberConvert.saveNDouble(singleFiveFlow/10000.0,1));
        capitalDTO.setTenStockFlow(NumberConvert.saveNDouble(singleTenFlow/10000.0,1));
        capitalDTO.setTwentyStockFlow(NumberConvert.saveNDouble(singleTwentyFlow/10000.0,1));

        capitalDTO.setTodayIndustryFlow(NumberConvert.saveNDouble(industryOneFlow/10000.0,1));
        capitalDTO.setFiveIndustryFlow(NumberConvert.saveNDouble(industryFiveFlow/10000.0,1));
        capitalDTO.setTenIndustryFlow(NumberConvert.saveNDouble(industryTenFlow/10000.0,1));
        capitalDTO.setTwentyIndustryFlow(NumberConvert.saveNDouble(industryTwentyFlow/10000.0,1));


        InstitutionTrade institutionTrade=rateMapper.getOneInstitutionTrade(code);
        if(institutionTrade!=null){
            capitalDTO.setStockCode(institutionTrade.getStockCode());
            capitalDTO.setStockName(institutionTrade.getStockName());
            capitalDTO.setbAmount(institutionTrade.getBamount());
            capitalDTO.setbCount(institutionTrade.getBcount());
            capitalDTO.setsAmount(institutionTrade.getSamount());
            capitalDTO.setsCount(institutionTrade.getScount());
            capitalDTO.setNet(institutionTrade.getNet());
        }


        //下面是指标
        double one=singleOneFlow/(singleTwentyFlow/20.0);
        double two=industryOneFlow/(singleTwentyFlow/20.0);
        double three=-10;
        if(institutionTrade!=null){
            three=institutionTrade.getNet()/singleOneFlow;
        }
        double partScore=one*0.2+two*0.1+0.00005*three;

        capitalDTO.setPartScore(partScore);

        return capitalDTO;
    }

    @Override
    public MessageDTO getOneStockMessageScore(String code, String date) {
        MessageDTO messageDTO=new MessageDTO();



        AllocationPlan allocationPlan=dayKLineMapper.getOneAllocationPlan(code);

        Message_allocationDTO messageAllocationDTO=new Message_allocationDTO();
        if(allocationPlan!=null){
            messageAllocationDTO.setCode(allocationPlan.getCode());
            messageAllocationDTO.setName(allocationPlan.getName());
            messageAllocationDTO.setYear(allocationPlan.getYear());
            messageAllocationDTO.setReport_date(DateConvert.dateToString(allocationPlan.getReport_name()));
            messageAllocationDTO.setDivi(Double.valueOf(allocationPlan.getDivi()));
            messageAllocationDTO.setShares(Integer.valueOf(allocationPlan.getShares()));
        }

        messageDTO.setMessage_allocationDTO(messageAllocationDTO);




        AchievementForecast achievementForecast=dayKLineMapper.getOneStockForecast(code);

        Message_forecastDTO messageForecastDTO=new Message_forecastDTO();
        if(achievementForecast!=null){
            messageForecastDTO.setCode(achievementForecast.getCode());
            messageForecastDTO.setName(achievementForecast.getName());
            messageForecastDTO.setType(achievementForecast.getType());
            messageForecastDTO.setReport_date(DateConvert.dateToString(achievementForecast.getReport_date()));
            messageForecastDTO.setPre_eps(Double.valueOf(achievementForecast.getPre_eps()));
            messageForecastDTO.setOut_range(achievementForecast.getRange());
        }

        messageDTO.setMessage_forecastDTO(messageForecastDTO);


        ArrayList<Message_NewsDTO> messageNewsDTOArrayList=new ArrayList<Message_NewsDTO>();

        ArrayList<StockNews> stockNewsArrayList=dayKLineMapper.getOneStockAllNews(code);
        if(stockNewsArrayList.size()!=0){
            for(int count=0;count<stockNewsArrayList.size();count++){
                StockNews stockNews=stockNewsArrayList.get(count);
                Message_NewsDTO messageNewsDTO=new Message_NewsDTO();
                messageNewsDTO.setCode(stockNews.getStockCode());
                messageNewsDTO.setDate(DateConvert.dateToString(stockNews.getDate()));
                messageNewsDTO.setType(stockNews.getType());
                messageNewsDTO.setTitle(stockNews.getTitle());
                messageNewsDTO.setUrl(stockNews.getUrl());

                messageNewsDTOArrayList.add(messageNewsDTO);

            }
        }
        messageDTO.setMessageNewsDTOArrayList(messageNewsDTOArrayList);

        //指标
        double one=messageNewsDTOArrayList.size()+0.0;
        double two=0.0;
        if(achievementForecast!=null){
            String type=achievementForecast.getType();
            if(type.equals("减亏")){
                two=-2.0;
            }else if(type.equals("预亏")||type.equals("预降")||type.equals("预降")){
                two=-1.0;
            }else if(type.equals("预升")||type.equals("预增")||type.equals("预盈")){
                two=1.0;
            }
        }

        double partScore=1.0*one+10.0*two;

        StockScore stockScore=rateMapper.getOneStockScore(code);
        double thisScore=stockScore.getMessageScore();

        ArrayList<StockScore> allStockScore= (ArrayList<StockScore>) rateMapper.getAllStockScore();
        ArrayList<Double> oneScoreList=new ArrayList<Double>();

        for(int count=0;count<allStockScore.size();count++){
            oneScoreList.add(allStockScore.get(count).getMessageScore());
        }

        //0-10分中给分
        double technicalScore=getOneScore(thisScore,oneScoreList);


        messageDTO.setMessageScore(NumberConvert.saveNDouble(technicalScore,2));
        messageDTO.setPartScore(partScore);
        messageDTO.setDefeatPercent(NumberConvert.doubleToBiggerInt(technicalScore*10.0));
        messageDTO.setNumberOfMessage(messageNewsDTOArrayList.size());


        return messageDTO;
    }

    @Override
    public IndustryDTO getOneStockIndustryScore(String code, String date) {
        IndustryDTO industryDTO=new IndustryDTO();




        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayKLineMapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realDate=DateConvert.getRealEndDate(date,allDateList);


        //      股票编号 行业名称
        HashMap<String,String> codeToIndustryMap=dayKLineMapper.getAllIndustryAndCode(new CodeIndustryMap("code","industry"));

        String belongIndustry=codeToIndustryMap.get(code);

        ArrayList<ProfessionFundFlows> professionFundFlowsArrayList= (ArrayList<ProfessionFundFlows>) rateMapper.getAllProfessionFundFlows();
        //      日期    涨跌幅   最后取结果需要用到
        HashMap<String,ProfessionFundFlows> oneIndustryFlowMap=new HashMap<String, ProfessionFundFlows>();

        for(int count=0;count<professionFundFlowsArrayList.size();count++){

            if(professionFundFlowsArrayList.get(count).getIndustry().equals(belongIndustry)){
                String oneDate=DateConvert.dateToString(professionFundFlowsArrayList.get(count).getDate());

                oneIndustryFlowMap.put(oneDate,professionFundFlowsArrayList.get(count));
            }

        }

        if(oneIndustryFlowMap==null){
            oneIndustryFlowMap=new HashMap<String, ProfessionFundFlows>();
        }

        String lastDate=DateConvert.getLastNDate(allDateList,realDate,10);

        HashMap<String,Object> map = new HashMap<String, Object>();

        map.put("block", "sh000001");
        map.put("start",Date.valueOf(lastDate));
        map.put("end",Date.valueOf(realDate));

        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayKLineMapper.getTimesBlockInfo(map);

        //      日期    收盘价
        HashMap<String,Double> blockInfoMap=new HashMap<String, Double>();
        for(int count=0;count<dayKLineArrayList.size();count++){
            String oneDate=DateConvert.dateToString(dayKLineArrayList.get(count).getStockDate());
            double price=dayKLineArrayList.get(count).getClosePrice();

            blockInfoMap.put(oneDate,price);
        }

        ArrayList<DateAndChange> resultList=new ArrayList<DateAndChange>();

        double tenDaysIndustryChange=1.0;

        for(int count=9;count>=0;count--){
            String currentDate=DateConvert.getLastNDate(allDateList,realDate,count);
            String yesterdayDate=DateConvert.getLastNDate(allDateList,realDate,count+1);

            double nowPrice=blockInfoMap.get(currentDate);
            double lastPrice=blockInfoMap.get(yesterdayDate);

            DateAndChange dateAndChange=new DateAndChange();

            dateAndChange.setDate(currentDate);
            dateAndChange.setBlockChangePercent(StockCalculator.getIncrease(lastPrice,nowPrice));

            if(oneIndustryFlowMap.containsKey(currentDate)){
                dateAndChange.setIndustryChangePercent(oneIndustryFlowMap.get(currentDate).getChange_percent());
            }else{
                dateAndChange.setIndustryChangePercent(0.0);
            }


            resultList.add(dateAndChange);

            double changePercent=0.0;
            if(oneIndustryFlowMap.containsKey(currentDate)){
                changePercent=oneIndustryFlowMap.get(currentDate).getChange_percent();
            }

            tenDaysIndustryChange=tenDaysIndustryChange*(1+changePercent);



        }

        tenDaysIndustryChange=tenDaysIndustryChange-1.0;

        double beforePrice=blockInfoMap.get(DateConvert.getLastNDate(allDateList,realDate,10));
        double nowPrice=blockInfoMap.get(realDate);


        double tenDaysMarketChange=StockCalculator.getIncrease(beforePrice,nowPrice);

        industryDTO.setTenDaysIndustryChange(tenDaysIndustryChange);
        industryDTO.setTenDaysMarketChange(tenDaysMarketChange);
        industryDTO.setChangeList(JsonConvert.ChangeListConvert(resultList));

        //指标
        double one=tenDaysIndustryChange;
        double two=tenDaysIndustryChange-tenDaysMarketChange;

        double partScore=two*200.0+100.0*one;

        StockScore stockScore=rateMapper.getOneStockScore(code);
        double thisScore=stockScore.getIndustryScore();

        ArrayList<StockScore> allStockScore= (ArrayList<StockScore>) rateMapper.getAllStockScore();
        ArrayList<Double> oneScoreList=new ArrayList<Double>();

        for(int count=0;count<allStockScore.size();count++){
            oneScoreList.add(allStockScore.get(count).getIndustryScore());
        }

        //0-10分中给分
        double technicalScore=getOneScore(thisScore,oneScoreList);


        industryDTO.setIndustryScore(NumberConvert.saveNDouble(technicalScore,2));
        industryDTO.setPartScore(partScore);
        industryDTO.setDefeatPercent(NumberConvert.doubleToBiggerInt(technicalScore*10.0));


        return industryDTO;
    }

    @Override
    public BasicDTO getOneStockBasicScore(String code, String date) {
        BasicDTO basicDTO=new BasicDTO();


        CashFlow cashFlow=rateMapper.getOneCashFlow(code);
        Basic_cashFlowDTO basicCashFlowDTO=new Basic_cashFlowDTO();

//        System.out.println(cashFlow.getCode());

        basicCashFlowDTO.setCode(code);
        if(cashFlow!=null){
            basicCashFlowDTO.setName(cashFlow.getName());
            if(cashFlow.getCf_sales().equals("nan")){
                basicCashFlowDTO.setCfSales("--");
            }else{
                basicCashFlowDTO.setCfSales(cashFlow.getCf_sales());
            }

            if(cashFlow.getRateofreturn().equals("nan")){
                basicCashFlowDTO.setRateOfReturn("--");
            }else{
                basicCashFlowDTO.setRateOfReturn(cashFlow.getCashflowratio());
            }

            if(cashFlow.getCf_nm().equals("nan")){
                basicCashFlowDTO.setCfNm("--");
            }else{
                basicCashFlowDTO.setCfNm(cashFlow.getCf_nm());
            }

            if(cashFlow.getCf_liabilities().equals("nan")){
                basicCashFlowDTO.setCfLiAbilities("--");
            }else{
                basicCashFlowDTO.setCfLiAbilities(cashFlow.getCf_liabilities());
            }

            if(cashFlow.getCashflowratio().equals("nan")){
                basicCashFlowDTO.setCashFlowRatio("--");
            }else{
                basicCashFlowDTO.setCashFlowRatio(cashFlow.getCashflowratio());
            }
        }


        basicDTO.setBasicCashFlowDTO(basicCashFlowDTO);


        EarningAbility earningAbility=rateMapper.getOneEarningAbility(code);
        Basic_earningDTO basicEarningDTO=new Basic_earningDTO();


//        System.out.println(earningAbility.getCode());



        basicEarningDTO.setCode(code);

        if(earningAbility!=null){
            basicEarningDTO.setName(earningAbility.getName());
            if(earningAbility.getArturnover().equals("nan")){
                basicEarningDTO.setArTurnOver("--");
            }else{
                basicEarningDTO.setArTurnOver(earningAbility.getArturnover());
            }

            if(earningAbility.getArturndays().equals("nan")){
                basicEarningDTO.setArTurnDays("--");
            }else{
                basicEarningDTO.setArTurnDays(earningAbility.getArturndays());
            }

            if(earningAbility.getInventory_turnover().equals("nan")){
                basicEarningDTO.setInventoryTurnOver("--");
            }else{
                basicEarningDTO.setInventoryTurnOver(earningAbility.getInventory_turnover());
            }

            if(earningAbility.getInventory_days().equals("nan")){
                basicEarningDTO.setInventoryDays("--");
            }else{
                basicEarningDTO.setInventoryDays(earningAbility.getInventory_days());
            }

            if(earningAbility.getCurrentasset_turnover().equals("nan")){
                basicEarningDTO.setCurrentAssetTurnOver("--");
            }else{
                basicEarningDTO.setCurrentAssetTurnOver(earningAbility.getCurrentasset_turnover());
            }

            if(earningAbility.getCurrentasset_days().equals("nan")){
                basicEarningDTO.setCurrentAssetDays("--");
            }else{
                basicEarningDTO.setCurrentAssetDays(earningAbility.getCurrentasset_days());
            }
        }



        basicDTO.setBasicEarningDTO(basicEarningDTO);

        GrowAbility growAbility=rateMapper.getOneGrowAbility(code);
        Basic_growDTO basicGrowDTO=new Basic_growDTO();


//        System.out.println(growAbility.getCode());


        basicGrowDTO.setCode(code);

        if(growAbility!=null){
            basicGrowDTO.setName(growAbility.getName());
            if(growAbility.getMbrg().equals("nan")){
                basicGrowDTO.setMbrg("--");
            }else{
                basicGrowDTO.setMbrg(growAbility.getMbrg());
            }

            if(growAbility.getNprg().equals("nan")){
                basicGrowDTO.setNprg("--");
            }else{
                basicGrowDTO.setNprg(growAbility.getNprg());
            }

            if(growAbility.getNav().equals("nan")){
                basicGrowDTO.setNav("--");
            }else{
                basicGrowDTO.setNav(growAbility.getNav());
            }

            if(growAbility.getTarg().equals("nan")){
                basicGrowDTO.setTarg("--");
            }else{
                basicGrowDTO.setTarg(growAbility.getTarg());
            }

            if(growAbility.getEpsg().equals("nan")){
                basicGrowDTO.setEpsg("--");
            }else{
                basicGrowDTO.setEpsg(growAbility.getEpsg());
            }

            if(growAbility.getSeg().equals("nan")){
                basicGrowDTO.setSeg("--");
            }else{
                basicGrowDTO.setSeg(growAbility.getSeg());
            }
        }

        basicDTO.setBasicGrowDTO(basicGrowDTO);

        PaymentAbility paymentAbility=rateMapper.getOnePaymentAbility(code);
        Basic_paymentDTO basicPaymentDTO=new Basic_paymentDTO();


//        System.out.println(paymentAbility.getCode());

        basicPaymentDTO.setCode(code);
        if(paymentAbility!=null){
            basicPaymentDTO.setName(paymentAbility.getName());
            if(paymentAbility.getCurrentratio().equals("nan")){
                basicPaymentDTO.setCurrentRatio("--");
            }else{
                basicPaymentDTO.setCurrentRatio(paymentAbility.getCurrentratio());
            }
            if(paymentAbility.getQuickratio().equals("nan")){
                basicPaymentDTO.setQuickRatio("--");
            }else{
                basicPaymentDTO.setQuickRatio(paymentAbility.getQuickratio());
            }
            if(paymentAbility.getCashratio().equals("nan")){
                basicPaymentDTO.setCashRatio("--");
            }else{
                basicPaymentDTO.setCashRatio(paymentAbility.getCashratio());
            }
            if(paymentAbility.getIcratio().equals("nan")){
                basicPaymentDTO.setIcRatio("--");
            }else{
                basicPaymentDTO.setIcRatio(paymentAbility.getIcratio());
            }

            if(paymentAbility.getSheqratio().equals("nan")){
                basicPaymentDTO.setSheqRatio("--");
            }else{
                basicPaymentDTO.setSheqRatio(paymentAbility.getSheqratio());
            }

            if(paymentAbility.getAdratio().equals("nan")){
                basicPaymentDTO.setAdRatio("--");
            }else{
                basicPaymentDTO.setAdRatio(paymentAbility.getAdratio());
            }

        }
        basicDTO.setBasicPaymentDTO(basicPaymentDTO);

        ProfitAbility profitAbility=rateMapper.getOneProfitAbility(code);
        Basic_profitDTO basicProfitDTO=new Basic_profitDTO();


//        System.out.println(profitAbility.getCode());

        basicProfitDTO.setCode(code);

        if(profitAbility!=null){
            basicProfitDTO.setName(profitAbility.getName());
            if(profitAbility.getRoe().equals("nan")){
                basicProfitDTO.setRoe("--");
            }else{
                basicProfitDTO.setRoe(profitAbility.getRoe());
            }
            if(profitAbility.getNet_profit_ratio().equals("nan")){
                basicProfitDTO.setNetProfitRatio("--");
            }else{
                basicProfitDTO.setNetProfitRatio(profitAbility.getNet_profit_ratio());
            }
            if(profitAbility.getGross_profit_rate().equals("nan")){
                basicProfitDTO.setGrossProfitRate("--");
            }else{
                basicProfitDTO.setGrossProfitRate(profitAbility.getGross_profit_rate());
            }
            if(profitAbility.getNet_profits().equals("nan")){
                basicProfitDTO.setNetProfits("--");
            }else{
                basicProfitDTO.setNetProfits(profitAbility.getNet_profits());
            }
            if(profitAbility.getEsp().equals("nan")){
                basicProfitDTO.setEsp("--");
            }else{
                basicProfitDTO.setEsp(profitAbility.getEsp());
            }
            if(profitAbility.getBusiness_income().equals("nan")){
                basicProfitDTO.setBussinessIncome("--");
            }else{
                basicProfitDTO.setBussinessIncome(profitAbility.getBusiness_income());
            }
            if(profitAbility.getBips().equals("nan")){
                basicProfitDTO.setBips("--");
            }else{
                basicProfitDTO.setBips(profitAbility.getBips());
            }
        }

        basicDTO.setBasicProfitDTO(basicProfitDTO);

//        System.out.println(basicDTO);

        //指标
        double one=0.0;
        double two=0.0;
        double three=0.0;
        double four=0.0;
        double five=0.0;

        if(cashFlow!=null){
            if(!cashFlow.getCashflowratio().equals("nan")){
                one=Double.valueOf(cashFlow.getCashflowratio());
            }
        }

        if(earningAbility!=null){
            if(!earningAbility.getCurrentasset_turnover().equals("nan")){
                two=Double.valueOf(earningAbility.getCurrentasset_turnover());
            }
        }

        if(growAbility!=null){
            if(!growAbility.getSeg().equals("nan")){
                three=Double.valueOf(growAbility.getSeg());
            }
        }

        if(paymentAbility!=null){
            if(!paymentAbility.getAdratio().equals("--")){
                four=Double.valueOf(paymentAbility.getAdratio());
            }
        }

        if(profitAbility!=null){
            if(!profitAbility.getEsp().equals("nan")){
                five=Double.valueOf(profitAbility.getBips());
            }
        }

        double partScore=one*0.1+two*10+three*0.1+four*0.1+five*10;

        StockScore stockScore=rateMapper.getOneStockScore(code);
        double thisScore=stockScore.getBasicScore();

        ArrayList<StockScore> allStockScore= (ArrayList<StockScore>) rateMapper.getAllStockScore();
        ArrayList<Double> oneScoreList=new ArrayList<Double>();

        for(int count=0;count<allStockScore.size();count++){
            oneScoreList.add(allStockScore.get(count).getBasicScore());
        }

        //0-10分中给分
        double technicalScore=getOneScore(thisScore,oneScoreList);


        basicDTO.setBasicScore(NumberConvert.saveNDouble(technicalScore,2));
        basicDTO.setPartScore(partScore);
        basicDTO.setDefeatPercent(NumberConvert.doubleToBiggerInt(technicalScore*10.0));


        return basicDTO;
    }

    @Override
    public boolean addAllScoreDate() {
        List<StockBasicInfo> allStockInfoList=dayKLineMapper.getAllStockInfos();
        ArrayList<String> codeList=new ArrayList<String>();

        for(int count=0;count<allStockInfoList.size();count++){
            codeList.add(allStockInfoList.get(count).getCode());
        }

        for(int count=0;count<codeList.size();count++){


            String stockCode=codeList.get(count);

            TechnicalDTO technicalDTO=getOneStockTechnicalScore(stockCode,"2017-06-02");
            CapitalDTO capitalDTO=getOneStockCapitalScore(stockCode,"2017-06-02");
            MessageDTO messageDTO=getOneStockMessageScore(stockCode,"2017-06-02");
            IndustryDTO industryDTO=getOneStockIndustryScore(stockCode,"2017-06-02");
            BasicDTO basicDTO=getOneStockBasicScore(stockCode,"2017-06-02");

            if(technicalDTO!=null&&capitalDTO!=null&&messageDTO!=null&&industryDTO!=null&&basicDTO!=null){
                StockScore stockScore=new StockScore();
                stockScore.setCode(stockCode);

                if(String.valueOf(technicalDTO.getPartScore()).equals("NaN")||String.valueOf(technicalDTO.getPartScore()).equals("Infinity")||String.valueOf(technicalDTO.getPartScore()).equals("-Infinity")){
                    stockScore.setTechnicalScore(0.0);
                }else{
                    stockScore.setTechnicalScore(technicalDTO.getPartScore());
                }

                if(String.valueOf(capitalDTO.getPartScore()).equals("NaN")||String.valueOf(capitalDTO.getPartScore()).equals("Infinity")||String.valueOf(capitalDTO.getPartScore()).equals("-Infinity")){
                    stockScore.setCapitalScore(0.0);
                }else{
                    stockScore.setCapitalScore(capitalDTO.getPartScore());
                }

                if(String.valueOf(messageDTO.getPartScore()).equals("NaN")||String.valueOf(messageDTO.getPartScore()).equals("Infinity")||String.valueOf(messageDTO.getPartScore()).equals("-Infinity")){
                    stockScore.setMessageScore(0.0);
                }else{
                    stockScore.setMessageScore(messageDTO.getPartScore());
                }

                if(String.valueOf(industryDTO.getPartScore()).equals("NaN")||String.valueOf(industryDTO.getPartScore()).equals("Infinity")||String.valueOf(industryDTO.getPartScore()).equals("-Infinity")){
                    stockScore.setIndustryScore(0.0);
                }else{
                    stockScore.setIndustryScore(industryDTO.getPartScore());
                }

                if(String.valueOf(basicDTO.getPartScore()).equals("NaN")||String.valueOf(basicDTO.getPartScore()).equals("Infinity")||String.valueOf(basicDTO.getPartScore()).equals("-Infinity")){
                    stockScore.setBasicScore(0.0);
                }else{
                    stockScore.setBasicScore(basicDTO.getPartScore());
                }




//                System.out.println(technicalDTO.getPartScore()+" "+capitalDTO.getPartScore()+" "+messageDTO.getPartScore()+" "+industryDTO.getPartScore()+" "+basicDTO.getPartScore());
//
//                System.out.println(String.valueOf(technicalDTO.getPartScore()));

                rateMapper.insertStockScore(stockScore);

//                System.out.println(stockCode);
            }


        }
        return true;


    }


    public ArrayList<klineDTO> getKline(String code, String sDate, String lDate) {
        ArrayList<klineDTO> klineDTOArrayList=new ArrayList<klineDTO>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayKLineMapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        //allDateList是全部日期按照顺序排列
        String realSDate=DateConvert.getRealStartDate(sDate,allDateList);
        String realLDate=DateConvert.getRealEndDate(lDate,allDateList);

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("code",code);
        map.put("start",Date.valueOf(realSDate));
        map.put("end",Date.valueOf(realLDate));
        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayKLineMapper.getTimesDayKLines(map);

        StockBasicInfo stockBasicInfo=dayKLineMapper.getOneStockInfo(code);

        for(int count=0;count<dayKLineArrayList.size();count++){
            klineDTO klineDTO=new klineDTO();
            DayKLine dayKLine=dayKLineArrayList.get(count);
            klineDTO.setId(code);
            klineDTO.setOpenPrice(dayKLine.getOpenPrice());
            klineDTO.setClosePrice(dayKLine.getClosePrice());
            klineDTO.setHighPrice(dayKLine.getHighPrice());
            klineDTO.setLowPrice(dayKLine.getLowPrice());
            klineDTO.setDate(DateConvert.dateToString(dayKLine.getStockDate()));
            klineDTO.setName(stockBasicInfo.getName());
            klineDTOArrayList.add(klineDTO);

        }



        return klineDTOArrayList;
    }




}
