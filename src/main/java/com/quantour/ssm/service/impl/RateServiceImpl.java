package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.RateMapper;
import com.quantour.ssm.dto.stockRate.*;
import com.quantour.ssm.model.*;
import com.quantour.ssm.service.RateService;
import com.quantour.ssm.util.CodeIndustryMap;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.StockCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
        return null;
    }

    @Override
    public void calculateOneDayStockScore(String date) {

    }

    @Override
    public TechnicalDTO getOneStockTechnicalScore(String code, String date) {
        return null;
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
        HashMap<String,Double> oneIndustryMap=allProfessionFlowMap.get(thisStockIndustry);

        for(int count=19;count>=0;count--){
            String currentDate=DateConvert.getLastNDate(allDateList,realDate,count);

            FundFlowMapDTO fundFlowMapDTO=new FundFlowMapDTO();


            double oneDayIndustryTotalFlow=0.0;

            if(count==0){
                //单日的个股资金流向和行业资金流向
                singleOneFlow=singleOneFlow+oneStockSingleMap.get(currentDate);
                industryOneFlow=industryOneFlow+oneIndustryMap.get(currentDate);
            }

            if(0<=count&&count<=4){
                //近五日的个股资金流向和行业资金流向
                singleFiveFlow=singleFiveFlow+oneStockSingleMap.get(currentDate);
                industryFiveFlow=industryFiveFlow+oneIndustryMap.get(currentDate);
            }

            if(0<=count&&count<=9){
                //近十日的个股资金流向和行业资金流向
                singleTenFlow=singleTenFlow+oneStockSingleMap.get(currentDate);
                industryTenFlow=industryTenFlow+oneIndustryMap.get(currentDate);
            }

            //近二十日的个股资金流向和行业资金流向
            singleTwentyFlow=singleTwentyFlow+oneStockSingleMap.get(currentDate);
            industryTwentyFlow=industryTwentyFlow+oneIndustryMap.get(currentDate);

//            System.out.println(oneStockSameKindSet.size());

            for (String str : oneStockSameKindSet) {

                if(allSingleFlowMap.containsKey(str)){
                    if(allSingleFlowMap.get(str).containsKey(currentDate)){
                        oneDayIndustryTotalFlow=oneDayIndustryTotalFlow+allSingleFlowMap.get(str).get(currentDate);
                    }
                }
            }

            double oneDayIndustryAverageFlow=oneDayIndustryTotalFlow/oneStockSameKindSet.size();
            fundFlowMapDTO.setDate(currentDate);
            fundFlowMapDTO.setSingleFlow(oneStockSingleMap.get(currentDate));
            fundFlowMapDTO.setIndustryAverageFlow(oneDayIndustryAverageFlow);

            allFlowMapList.add(fundFlowMapDTO);

        }


        capitalDTO.setCapitalScore(10.0);
        capitalDTO.setPartScore(100);
        capitalDTO.setDefeatPercent(80);

        capitalDTO.setFlowMapList(allFlowMapList);

        capitalDTO.setTodayStockFlow(singleOneFlow);
        capitalDTO.setFiveStockFlow(singleFiveFlow);
        capitalDTO.setTenStockFlow(singleTenFlow);
        capitalDTO.setTwentyStockFlow(singleTwentyFlow);

        capitalDTO.setTodayIndustryFlow(industryOneFlow);
        capitalDTO.setFiveIndustryFlow(industryFiveFlow);
        capitalDTO.setTenIndustryFlow(industryTenFlow);
        capitalDTO.setTwentyIndustryFlow(industryTwentyFlow);


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


        messageDTO.setMessageScore(10.0);
        messageDTO.setPartScore(80);
        messageDTO.setDefeatPercent(90);
        messageDTO.setNumberOfMessage(messageNewsDTOArrayList.size());


        return messageDTO;
    }

    @Override
    public IndustryDTO getOneStockIndustryScore(String code, String date) {
        IndustryDTO industryDTO=new IndustryDTO();

        industryDTO.setIndustryScore(10.0);
        industryDTO.setPartScore(80);
        industryDTO.setDefeatPercent(80);


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
            dateAndChange.setIndustryChangePercent(oneIndustryFlowMap.get(currentDate).getChange_percent());

            resultList.add(dateAndChange);

            double changePercent=oneIndustryFlowMap.get(currentDate).getChange_percent();
            tenDaysIndustryChange=tenDaysIndustryChange*(1+changePercent);



        }

        tenDaysIndustryChange=tenDaysIndustryChange-1.0;

        double beforePrice=blockInfoMap.get(DateConvert.getLastNDate(allDateList,realDate,10));
        double nowPrice=blockInfoMap.get(realDate);


        double tenDaysMarketChange=StockCalculator.getIncrease(beforePrice,nowPrice);

        industryDTO.setTenDaysIndustryChange(tenDaysIndustryChange);
        industryDTO.setTenDaysMarketChange(tenDaysMarketChange);
        industryDTO.setChangeList(resultList);


        return industryDTO;
    }

    @Override
    public BasicDTO getOneStockBasicScore(String code, String date) {
        return null;
    }
}
