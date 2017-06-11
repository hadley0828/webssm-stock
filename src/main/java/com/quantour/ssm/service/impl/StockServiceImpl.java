package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dto.*;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.DayKLineKey;
import com.quantour.ssm.model.StockBasicInfo;
import com.quantour.ssm.model.StockNews;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.StockCalculator;
import com.quantour.ssm.util.StockChangeHelper;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

/**
 * Created by loohaze on 2017/5/11.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

    @Resource
    private DayKLineMapper dayklinemapper;


    //数据层传来的格式为dayKline
    //传入的日期格式为2007-01-01

    //可以取不存在的日期
    @Override
    public stockDTO getStockInfo(String code, String date) {

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realDate=DateConvert.getRealEndDate(date,allDateList);


        DayKLineKey dayKLineKey=new DayKLineKey();
        dayKLineKey.setStockCode(code);
        dayKLineKey.setStockDate(Date.valueOf(realDate));


        DayKLine dayKLine=dayklinemapper.getOneDayKLine(dayKLineKey);


        String lastDate=DateConvert.getLastNDate(allDateList,realDate,1);


        dayKLineKey.setStockCode(code);
        dayKLineKey.setStockDate(Date.valueOf(lastDate));
        DayKLine lastDayKLine=dayklinemapper.getOneDayKLine(dayKLineKey);


        StockBasicInfo stockBasicInfo=dayklinemapper.getOneStockInfo(code);

        System.out.println(dayKLine);

        stockDTO stockdto=new stockDTO();
        stockdto.setId(stockBasicInfo.getCode());
        stockdto.setName(stockBasicInfo.getName());
        if(stockdto.getId().charAt(0)=='0'){
            stockdto.setMarket("深圳");
        }else if(stockdto.getId().charAt(0)=='3'){
            stockdto.setMarket("创业板");
        }else if(stockdto.getId().charAt(0)=='6'){
            stockdto.setMarket("上海");
        }
        stockdto.setOpenPrice(dayKLine.getOpenPrice());
        stockdto.setClosePrice(dayKLine.getClosePrice());
        stockdto.setHighPrice(dayKLine.getHighPrice());
        stockdto.setLowPrice(dayKLine.getLowPrice());

        stockdto.setUplift(StockCalculator.getUplift(lastDayKLine.getClosePrice(),dayKLine.getClosePrice()));
        stockdto.setAdjClose(dayKLine.getClosePrice());
        stockdto.setVolume(Math.round(dayKLine.getVolume()));
        stockdto.setLogYield(StockCalculator.getLogYield(lastDayKLine.getClosePrice(),dayKLine.getClosePrice()));
        stockdto.setStockIndustry(stockBasicInfo.getIndustry());
        stockdto.setStockArea(stockBasicInfo.getArea());

        ArrayList<NewsDTO> newsDTOArrayList=new ArrayList<NewsDTO>();
        ArrayList<StockNews> stockNewsArrayList=dayklinemapper.getOneStockAllNews(code);



        if(stockNewsArrayList.size()!=0){
            for(int count=0;count<stockNewsArrayList.size();count++){
                StockNews stockNews=stockNewsArrayList.get(count);

                NewsDTO newsDTO=new NewsDTO();
                newsDTO.setCode(stockNews.getStockCode());
                newsDTO.setDate(DateConvert.dateToString(stockNews.getDate()));
                newsDTO.setType(stockNews.getType());
                newsDTO.setTitle(stockNews.getTitle());
                newsDTO.setUrl(stockNews.getUrl());

                newsDTOArrayList.add(newsDTO);
            }
        }

        stockdto.setNewsDTOArrayList(newsDTOArrayList);

        return stockdto;
    }

    @Override
    public ArrayList<stockDTO> getSeveralStockInfo(ArrayList<String> codeList, String date) {

        ArrayList<stockDTO> stockDTOArrayList=new ArrayList<stockDTO>();



        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        String realDate=DateConvert.getRealEndDate(date,allDateList);


        ArrayList<DayKLine> nowStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(realDate));
        ArrayList<DayKLine> lastStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(DateConvert.getLastNDate(allDateList,realDate,1)));

        HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
        HashMap<String,DayKLine> lastStockMap=new HashMap<String, DayKLine>();

        for(int count=0;count<nowStockList.size();count++){
            nowStockMap.put(nowStockList.get(count).getStockCode(),nowStockList.get(count));
        }

        for(int count=0;count<lastStockList.size();count++){
            lastStockMap.put(lastStockList.get(count).getStockCode(),lastStockList.get(count));
        }

        ArrayList<StockBasicInfo> stockBasicInfoArrayList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
        HashMap<String,StockBasicInfo> stockBasicInfoHashMap=new HashMap<String, StockBasicInfo>();

        //TODO 在这里能取到更多的数据

        for(int count=0;count<stockBasicInfoArrayList.size();count++){
            stockBasicInfoHashMap.put(stockBasicInfoArrayList.get(count).getCode(),stockBasicInfoArrayList.get(count));
        }

        for(int count=0;count<codeList.size();count++){
            String stockCode=codeList.get(count);

            if(!nowStockMap.containsKey(stockCode)){

                    stockDTO stockdto=new stockDTO();
                    stockdto.setId(stockCode);
                    stockdto.setName(stockBasicInfoHashMap.get(stockCode).getName());
                    if(stockdto.getId().charAt(0)=='0'){
                        stockdto.setMarket("深圳");
                    }else if(stockdto.getId().charAt(0)=='3'){
                        stockdto.setMarket("创业板");
                    }else if(stockdto.getId().charAt(0)=='6'){
                        stockdto.setMarket("上海");
                    }
                    stockdto.setOpenPrice(0.0);
                    stockdto.setClosePrice(0.0);
                    stockdto.setHighPrice(0.0);
                    stockdto.setLowPrice(0.0);

                    stockdto.setUplift(0.0);
                    stockdto.setAdjClose(0.0);
                    stockdto.setVolume(0);
                    stockdto.setLogYield(0.0);

                    stockDTOArrayList.add(stockdto);
            }else{

                DayKLine dayKLine=new DayKLine();
                DayKLine lastDayKLine=new DayKLine();

                if(lastStockMap.containsKey(stockCode)){
                    dayKLine=nowStockMap.get(stockCode);
                    lastDayKLine=lastStockMap.get(stockCode);

                }else{
                    dayKLine=nowStockMap.get(stockCode);

                    DayKLineKey dayKLineKey = new DayKLineKey();
                    dayKLineKey.setStockCode(stockCode);
                    dayKLineKey.setStockDate(Date.valueOf(realDate));
                    lastDayKLine=dayklinemapper.getYesterdayDayKLine(dayKLineKey);

                }


                stockDTO stockdto=new stockDTO();
                stockdto.setId(stockCode);
                stockdto.setName(stockBasicInfoHashMap.get(stockCode).getName());
                if(stockdto.getId().charAt(0)=='0'){
                    stockdto.setMarket("深圳");
                }else if(stockdto.getId().charAt(0)=='3'){
                    stockdto.setMarket("创业板");
                }else if(stockdto.getId().charAt(0)=='6'){
                    stockdto.setMarket("上海");
                }
                stockdto.setOpenPrice(dayKLine.getOpenPrice());
                stockdto.setClosePrice(dayKLine.getClosePrice());
                stockdto.setHighPrice(dayKLine.getHighPrice());
                stockdto.setLowPrice(dayKLine.getLowPrice());

                stockdto.setUplift(StockCalculator.getUplift(lastDayKLine.getClosePrice(),dayKLine.getClosePrice()));
                stockdto.setAdjClose(dayKLine.getClosePrice());
                stockdto.setVolume(Math.round(dayKLine.getVolume()));
                stockdto.setLogYield(StockCalculator.getLogYield(lastDayKLine.getClosePrice(),dayKLine.getClosePrice()));

                stockDTOArrayList.add(stockdto);


            }
        }

        return stockDTOArrayList;
    }

    //需要实现开始日期和结束日期不存在的情况
    //要是整个区间都不在范围之内的话另外做判断！
    @Override
    public ArrayList<klineDTO> getKline(String code, String sDate, String lDate) {
        ArrayList<klineDTO> klineDTOArrayList=new ArrayList<klineDTO>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
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
        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getTimesDayKLines(map);

        StockBasicInfo stockBasicInfo=dayklinemapper.getOneStockInfo(code);

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

    @Override
    public ArrayList<klineDTO> getBlockKline(String code, String sDate, String lDate) {
        ArrayList<klineDTO> klineDTOArrayList=new ArrayList<klineDTO>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        //allDateList是全部日期按照顺序排列
        String realSDate=DateConvert.getRealStartDate(sDate,allDateList);
        String realLDate=DateConvert.getRealEndDate(lDate,allDateList);

        HashMap<String,Object> map = new HashMap<String, Object>();

        map.put("block", code);
        map.put("start",Date.valueOf(realSDate));
        map.put("end",Date.valueOf(realLDate));

        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getTimesBlockInfo(map);

        String name="未知";
        if(code.equals("sh000001")){
            name="上证指数";
        }else if(code.equals("sh000016")){
            name="上证50";
        }else if(code.equals("sh000300")){
            name="沪深300";
        }else if(code.equals("sh000905")){
            name="中证500";
        }else if(code.equals("sz399001")){
            name="深证成指";
        }else if(code.equals("sz399005")){
            name="中小板指";
        }else if(code.equals("sz399006")){
            name="创业板指";
        }

        for(int count=0;count<dayKLineArrayList.size();count++){
            klineDTO klineDTO=new klineDTO();
            DayKLine dayKLine=dayKLineArrayList.get(count);
            klineDTO.setId(code);
            klineDTO.setOpenPrice(dayKLine.getOpenPrice());
            klineDTO.setClosePrice(dayKLine.getClosePrice());
            klineDTO.setHighPrice(dayKLine.getHighPrice());
            klineDTO.setLowPrice(dayKLine.getLowPrice());
            klineDTO.setDate(DateConvert.dateToString(dayKLine.getStockDate()));
            klineDTO.setName(name);
            klineDTOArrayList.add(klineDTO);

        }
        return klineDTOArrayList;
    }

    @Override
    public marketDTO getMarketInfo(String date) {
        marketDTO marketdto=new marketDTO();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        String realDate=DateConvert.getRealEndDate(date,allDateList);

        String name="上圳";
        String Date=realDate;
        long Volume=0;

        int limitup=0; //这里的涨停没有考虑ST股票
        int limitdown=0;
        int upfive=0;
        int downfive=0;
        int upnum=0;		//开盘-收盘小于-5%*上一个交易日收盘价的股票个数;
        int downnum=0;	//开盘-收盘大于5%*上一个交易日收盘价的股票个数;

        int riseStockNumber=0;
        int declineStockNumber=0;
        ArrayList<Integer> changePercentNumberList=new ArrayList<Integer>();
        for(int count=0;count<10;count++){
            int i=0;
            changePercentNumberList.add(i);
        }


        String lastDate=DateConvert.getLastNDate(allDateList,realDate,1);



        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(realDate));
        HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<dayKLineArrayList.size();count++){
            nowStockMap.put(dayKLineArrayList.get(count).getStockCode(),dayKLineArrayList.get(count));
        }

        ArrayList<DayKLine> lastDayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(lastDate));
        HashMap<String,DayKLine> yesterdayStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<lastDayKLineArrayList.size();count++){
            yesterdayStockMap.put(lastDayKLineArrayList.get(count).getStockCode(),lastDayKLineArrayList.get(count));
        }

        for(int count=0;count<dayKLineArrayList.size();count++){
            String stockCode=dayKLineArrayList.get(count).getStockCode();
            DayKLine currentDayKLine=nowStockMap.get(stockCode);
            if(nowStockMap.containsKey(currentDayKLine.getStockCode())&&yesterdayStockMap.containsKey(currentDayKLine.getStockCode())){
                DayKLine yesterdayKLine=yesterdayStockMap.get(stockCode);

                Volume=Volume+Math.round(currentDayKLine.getVolume());

                double changePercent=StockCalculator.getIncrease(yesterdayKLine.getClosePrice(),currentDayKLine.getClosePrice());

                if(changePercent<=-0.08){
                    changePercentNumberList.set(0,changePercentNumberList.get(0)+1);
                    declineStockNumber++;

                }else if(-0.08<changePercent&&changePercent<=-0.06){
                    changePercentNumberList.set(1,changePercentNumberList.get(1)+1);
                    declineStockNumber++;


                }else if(-0.06<changePercent&&changePercent<=-0.04){
                    changePercentNumberList.set(2,changePercentNumberList.get(2)+1);
                    declineStockNumber++;

                }else if(-0.04<changePercent&&changePercent<=-0.02){
                    changePercentNumberList.set(3,changePercentNumberList.get(3)+1);
                    declineStockNumber++;

                }else if(-0.02<changePercent&&changePercent<=0.0){
                    changePercentNumberList.set(4,changePercentNumberList.get(4)+1);
                    declineStockNumber++;

                }else if(0.0<changePercent&&changePercent<=0.02){
                    changePercentNumberList.set(5,changePercentNumberList.get(5)+1);
                    riseStockNumber++;

                }else if(0.02<changePercent&&changePercent<=0.04){
                    changePercentNumberList.set(6,changePercentNumberList.get(6)+1);
                    riseStockNumber++;

                }else if(0.04<changePercent&&changePercent<=0.06){
                    changePercentNumberList.set(7,changePercentNumberList.get(7)+1);
                    riseStockNumber++;

                }else if(0.06<changePercent&&changePercent<=0.08){
                    changePercentNumberList.set(8,changePercentNumberList.get(8)+1);
                    riseStockNumber++;

                }else if(changePercent>0.08){
                    changePercentNumberList.set(9,changePercentNumberList.get(9)+1);
                    riseStockNumber++;
                }

                if(StockChangeHelper.isLimitUp(yesterdayKLine.getClosePrice(),currentDayKLine.getClosePrice())){
                    limitup++;
                }
                if(StockChangeHelper.isLimitDown(yesterdayKLine.getClosePrice(),currentDayKLine.getClosePrice())){
                    limitdown++;
                }
                if(StockChangeHelper.isUp(yesterdayKLine.getClosePrice(),currentDayKLine.getClosePrice(),0.05)){
                    upfive++;
                }
                if(StockChangeHelper.isdown(yesterdayKLine.getClosePrice(),currentDayKLine.getClosePrice(),0.05)){
                    downfive++;
                }
                if(StockChangeHelper.isCUp(yesterdayKLine.getClosePrice(),currentDayKLine.getOpenPrice(),currentDayKLine.getClosePrice(),0.05)){
                    upnum++;
                }
                if(StockChangeHelper.isCDown(yesterdayKLine.getClosePrice(),currentDayKLine.getOpenPrice(),currentDayKLine.getClosePrice(),0.05)){
                    downnum++;
                }

            }
        }

        marketdto.setName(name);
        marketdto.setVolume(Volume);
        marketdto.setDate(Date);
        marketdto.setLimitup(limitup);
        marketdto.setLimitdown(limitdown);
        marketdto.setUpfive(upfive);
        marketdto.setDownfive(downfive);
        marketdto.setUpnum(upnum);
        marketdto.setDownnum(downnum);

        marketdto.setRiseStockNumber(riseStockNumber);
        marketdto.setDeclineStockNumber(declineStockNumber);
        marketdto.setChangePercentNumberList(changePercentNumberList);

        return marketdto;
    }
    //not test
    @Override
    public ArrayList<compareDTO> getCompareInfo(String firstCode, String secondCode, String sDate, String lDate) {

        ArrayList<compareDTO> compareDTOArrayList=new ArrayList<compareDTO>();

        compareDTO firstdto=new compareDTO();

        ArrayList<String> firstDateList= (ArrayList<String>) dayklinemapper.getAllDateByCode(firstCode);
        String realSDate=DateConvert.getRealStartDate(sDate,firstDateList);
        String realLDate=DateConvert.getRealEndDate(lDate,firstDateList);

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("code",firstCode);
        map.put("start",Date.valueOf(realSDate));
        map.put("end",Date.valueOf(realLDate));

//        System.out.println(firstCode+" "+realSDate+" "+realLDate);

        ArrayList<DayKLine> firstDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getTimesDayKLines(map);

//        System.out.println(firstDayKLineList.get(0).getLowPrice());
//        System.out.println(firstDayKLineList.get(0).getStockCode());

        ArrayList<String> dateList=new ArrayList<String>();
        double lowestPrice=firstDayKLineList.get(0).getLowPrice();
        double highestPrice=firstDayKLineList.get(0).getHighPrice();
        double upOrDown=0.0;
        double startPrice=firstDayKLineList.get(0).getOpenPrice();
        double endPrice=firstDayKLineList.get(firstDayKLineList.size()-1).getClosePrice();

        ArrayList<Double> closePriceList=new ArrayList<Double>();
        ArrayList<Double> logYieldList=new ArrayList<Double>();
        double logVariance;
        for(int count=0;count<firstDayKLineList.size();count++){
            DayKLine currentDayKLine=firstDayKLineList.get(count);
            dateList.add(DateConvert.dateToString(currentDayKLine.getStockDate()));

            if(currentDayKLine.getLowPrice()<lowestPrice){
                lowestPrice=currentDayKLine.getLowPrice();
            }

            if(currentDayKLine.getHighPrice()>highestPrice){
                highestPrice=currentDayKLine.getHighPrice();
            }
            closePriceList.add(currentDayKLine.getClosePrice());

            if(count!=0){
                logYieldList.add(StockCalculator.getLogYield(firstDayKLineList.get(count-1).getClosePrice(),currentDayKLine.getClosePrice()));
            }
        }

        upOrDown=StockCalculator.getUplift(startPrice,endPrice);
        logVariance=StockCalculator.getLogVariance(closePriceList);

        firstdto.setbDate(realSDate);
        firstdto.setlDate(realLDate);
        firstdto.setLowestPrice(lowestPrice);
        firstdto.setHighestPrice(highestPrice);
        firstdto.setUpOrDown(upOrDown);
        firstdto.setClosePriceList(closePriceList);
        firstdto.setLogYieldList(logYieldList);
        firstdto.setLogVariance(logVariance);
        firstdto.setName(dayklinemapper.getOneStockInfo(firstCode).getName());
        firstdto.setDateList(dateList);

        compareDTOArrayList.add(firstdto);

        compareDTO seconddto=new compareDTO();

        ArrayList<String> secondDateList= (ArrayList<String>) dayklinemapper.getAllDateByCode(secondCode);
        realSDate=DateConvert.getRealStartDate(sDate,secondDateList);
        realLDate=DateConvert.getRealEndDate(lDate,secondDateList);

        HashMap<String,Object> newMap=new HashMap<String, Object>();
        newMap.put("code",secondCode);
        newMap.put("start",Date.valueOf(realSDate));
        newMap.put("end",Date.valueOf(realLDate));
        ArrayList<DayKLine> secondDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getTimesDayKLines(newMap);

        dateList=new ArrayList<String>();
        lowestPrice=secondDayKLineList.get(0).getLowPrice();
        highestPrice=secondDayKLineList.get(0).getHighPrice();
        startPrice=secondDayKLineList.get(0).getOpenPrice();
        endPrice=secondDayKLineList.get(secondDayKLineList.size()-1).getClosePrice();

        closePriceList=new ArrayList<Double>();
        logYieldList=new ArrayList<Double>();

        for(int count=0;count<secondDayKLineList.size();count++){
            DayKLine currentDayKLine=secondDayKLineList.get(count);
            dateList.add(DateConvert.dateToString(currentDayKLine.getStockDate()));

            if(currentDayKLine.getLowPrice()<lowestPrice){
                lowestPrice=currentDayKLine.getLowPrice();
            }
            if(currentDayKLine.getHighPrice()>highestPrice){
                highestPrice=currentDayKLine.getHighPrice();
            }
            closePriceList.add(currentDayKLine.getClosePrice());

            if(count!=0){
                logYieldList.add(StockCalculator.getLogYield(secondDayKLineList.get(count-1).getClosePrice(),currentDayKLine.getClosePrice()));
            }
        }

        upOrDown=StockCalculator.getUplift(startPrice,endPrice);
        logVariance=StockCalculator.getLogVariance(closePriceList);

        seconddto.setbDate(realSDate);
        seconddto.setlDate(realLDate);
        seconddto.setLowestPrice(lowestPrice);
        seconddto.setHighestPrice(highestPrice);
        seconddto.setUpOrDown(upOrDown);
        seconddto.setClosePriceList(closePriceList);
        seconddto.setLogYieldList(logYieldList);
        seconddto.setLogVariance(logVariance);
        seconddto.setName(dayklinemapper.getOneStockInfo(secondCode).getName());
        seconddto.setDateList(dateList);

        compareDTOArrayList.add(seconddto);


        return compareDTOArrayList;
    }

    //半角全角的问题！
    @Override
    public boolean isStockValid(String input) {
        boolean result=false;

        ArrayList<StockBasicInfo> stockInfoList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
        HashSet<String> allCodeList=new HashSet<String>();
        HashSet<String> allNameList=new HashSet<String>();
        for(int count=0;count<stockInfoList.size();count++){
            StockBasicInfo stockBasicInfo=stockInfoList.get(count);
            allCodeList.add(stockBasicInfo.getCode());
            allNameList.add(stockBasicInfo.getName());
        }
        if(allCodeList.contains(input)||allNameList.contains(input)){
            result=true;
        }
        return result;
    }

    @Override
    public boolean isDateValid(String input, String date) {
        String code=nameToCode(input);
        ArrayList<String> allStockDateList= (ArrayList<String>) dayklinemapper.getAllDateByCode(code);


        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        String realDate=DateConvert.getRealEndDate(date,allDateList);


        HashSet<String> stockAllDateMap=new HashSet<String>();
        for(int count=0;count<allStockDateList.size();count++){
            stockAllDateMap.add(allStockDateList.get(count));
        }

        if(stockAllDateMap.contains(realDate)){
            return true;
        }else{
            return false;
        }


    }

    @Override
    public boolean isDateValid(String date) {
        boolean result=false;

        ArrayList<Date> allDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        for(int count=0;count<allDateList.size();count++){
            String currentDate=DateConvert.dateToString(allDateList.get(count));
            if(currentDate.equals(date)){
                result=true;
                break;
            }
        }
        return result;
    }
    //感觉需要存储在数据库中！
    public String nameToCode(String input){
        if(input.charAt(0)<=57&&input.charAt(0)>=48){
            return input;
        }else{
            ArrayList<StockBasicInfo> stockInfoList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
            HashMap<String,String> nameAndCodeMap=new HashMap<String, String>();
            for(int count=0;count<stockInfoList.size();count++){
                nameAndCodeMap.put(stockInfoList.get(count).getName(),stockInfoList.get(count).getCode());
            }

            if(nameAndCodeMap.containsKey(input)){
                return nameAndCodeMap.get(input);
            }else{
                return "Not exists the Stock Name";
            }
        }
    }

    //String=code+"\t"+name;
    @Override
    public ArrayList<String> getAllCodeAndName() {
        ArrayList<StockBasicInfo> stockBasicInfoArrayList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
        ArrayList<String> allCodeAndNameList=new ArrayList<String>();
        for(int count=0;count<stockBasicInfoArrayList.size();count++){
            String str=stockBasicInfoArrayList.get(count).getCode()+"\t"+stockBasicInfoArrayList.get(count).getName();
            allCodeAndNameList.add(str);
        }

        return allCodeAndNameList;
    }

    @Override
    public ArrayList<String> getAllPlateName() {
        ArrayList<String> allPlateNameList=new ArrayList<String>();
        ArrayList<String> conceptList= (ArrayList<String>) dayklinemapper.getAllConceptBlock();
        for(int count=0;count<conceptList.size();count++){
            allPlateNameList.add(conceptList.get(count));
        }
        ArrayList<String> areaList= (ArrayList<String>) dayklinemapper.getAllAreaBlock();
        for(int count=0;count<areaList.size();count++){
            allPlateNameList.add(areaList.get(count));
        }
        ArrayList<String> industryList= (ArrayList<String>) dayklinemapper.getAllIndustryBlock();
        for(int count=0;count<industryList.size();count++){
            allPlateNameList.add(industryList.get(count));
        }
        return allPlateNameList;
    }

    @Override
    public ArrayList<String> getPlateAllStockCode(String plateName) {

        ArrayList<String> allConceptList= (ArrayList<String>) dayklinemapper.getAllConceptBlock();
        HashSet<String> allConceptSet=new HashSet<String>();
        for(int count=0;count<allConceptList.size();count++){
            allConceptSet.add(allConceptList.get(count));
        }

        if(allConceptSet.contains(plateName)){
            return (ArrayList<String>) dayklinemapper.getConceptBlockStockCodes(plateName);
        }

        ArrayList<String> allAreaList= (ArrayList<String>) dayklinemapper.getAllAreaBlock();
        HashSet<String> allAreaSet=new HashSet<String>();
        for(int count=0;count<allAreaList.size();count++){
            allAreaSet.add(allAreaList.get(count));
        }

        if(allAreaSet.contains(plateName)){
            return (ArrayList<String>) dayklinemapper.getAreaBlockStockCodes(plateName);
        }

        ArrayList<String> allIndustryList= (ArrayList<String>) dayklinemapper.getAllIndustryBlock();
        HashSet<String> allIndustrySet=new HashSet<String>();
        for(int count=0;count<allIndustryList.size();count++){
            allIndustrySet.add(allIndustryList.get(count));
        }

        if(allIndustrySet.contains(plateName)){
            return (ArrayList<String>) dayklinemapper.getIndustryBlockStockCodes(plateName);
        }


        return null;
    }

    @Override
    public ArrayList<String> getOneStockAllPlate(String stockCode) {
        ArrayList<String> allPlateList=new ArrayList<String>();

        ArrayList<String> conceptsList= (ArrayList<String>) dayklinemapper.getConceptByStock(stockCode);
        for(int count=0;count<conceptsList.size();count++){
            allPlateList.add(conceptsList.get(count));
        }

        ArrayList<String> areasList= (ArrayList<String>) dayklinemapper.getAreaByStock(stockCode);
        for(int count=0;count<areasList.size();count++){
            allPlateList.add(areasList.get(count));
        }

        ArrayList<String> industrysList= (ArrayList<String>) dayklinemapper.getIndustryByStock(stockCode);
        for(int count=0;count<industrysList.size();count++){
            allPlateList.add(industrysList.get(count));
        }

        return allPlateList;
    }

    /**
     * 需要保证当前的日期有效
     * 该方法用来获得某一天全部股票中涨幅前n名的股票的List
     * 用于生成排行榜
     * @param n 获得前n名
     * @param date 当天日期
     * @param changeDays 涨跌幅的天数
     * @return
     */
    @Override
    public ArrayList<waveDTO> getTopNCodesByDays(int n, String date, int changeDays) {
        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realDate=DateConvert.getRealEndDate(date,allDateList);


        String lastDate=DateConvert.getLastNDate(allDateList,realDate,changeDays);
        //date是当前日期 lastDate是上一个作比较的日期

        ArrayList<waveDTO> waveDTOArrayList=new ArrayList<waveDTO>();
        ArrayList<waveDTO> resultList=new ArrayList<waveDTO>();


        ArrayList<DayKLine> nowDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(realDate));
        HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<nowDayKLineList.size();count++){
            nowStockMap.put(nowDayKLineList.get(count).getStockCode(),nowDayKLineList.get(count));
        }

        ArrayList<DayKLine> lastDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(lastDate));
        HashMap<String,DayKLine> lastStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<lastDayKLineList.size();count++){
            lastStockMap.put(lastDayKLineList.get(count).getStockCode(),lastDayKLineList.get(count));
        }

        for(int count=0;count<nowDayKLineList.size();count++){
            String currentCode=nowDayKLineList.get(count).getStockCode();

            if(nowStockMap.containsKey(currentCode)&&lastStockMap.containsKey(currentCode)){
                DayKLine nowDayKline=nowStockMap.get(currentCode);
                DayKLine lastDayKline=lastStockMap.get(currentCode);

                waveDTO wavedto=new waveDTO();
                wavedto.setStockCode(currentCode);
                wavedto.setChangePercent(StockCalculator.getIncrease(lastDayKline.getClosePrice(),nowDayKline.getClosePrice()));

                waveDTOArrayList.add(wavedto);
            }
        }

        Collections.sort(waveDTOArrayList,COMPARATOR);

        if(n>waveDTOArrayList.size()){
            resultList=waveDTOArrayList;
        }else{
            for(int count=0;count<n;count++){
                resultList.add(waveDTOArrayList.get(count));
            }
        }



        return resultList;
    }

    @Override
    public ArrayList<RankDTO> getTopNStockByDays(int n, String date, int changeDays) {
        ArrayList<RankDTO> resultList=new ArrayList<RankDTO>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String realDate=DateConvert.getRealEndDate(date,allDateList);


        ArrayList<waveDTO> waveDTOArrayList=getTopNCodesByDays(n,date,changeDays);

        ArrayList<StockBasicInfo> allStockInfoList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
        HashMap<String,String> codeToNameMap=new HashMap<String, String>();
        for(int count=0;count<allStockInfoList.size();count++){
            codeToNameMap.put(allStockInfoList.get(count).getCode(),allStockInfoList.get(count).getName());
        }

        ArrayList<DayKLine> oneDayStockList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(realDate));
        HashMap<String,Double> codeToPriceMap=new HashMap<String, Double>();
        for(int count=0;count<oneDayStockList.size();count++){
            codeToPriceMap.put(oneDayStockList.get(count).getStockCode(),oneDayStockList.get(count).getClosePrice());
        }


        if(waveDTOArrayList.size()!=0){
            for(int index=0;index<waveDTOArrayList.size();index++){
                RankDTO rankDTO=new RankDTO();
                String code=waveDTOArrayList.get(index).getStockCode();
                rankDTO.setStockCode(code);
                rankDTO.setChangePercent(waveDTOArrayList.get(index).getChangePercent());
                rankDTO.setStockName(codeToNameMap.get(code));
                rankDTO.setNewestPrice(codeToPriceMap.get(code));

                resultList.add(rankDTO);
            }

        }
        return resultList;
    }

    //编写Comparator,根据WaveVO的changePercent对waveDTO进行排序
    private static final Comparator<waveDTO> COMPARATOR = new Comparator<waveDTO>() {
        public int compare(waveDTO o1, waveDTO o2) {
            return o1.compareTo(o2);//运用User类的compareTo方法比较两个对象
        }
    };

    /**
     * 当前的日期必须有效
     * 该方法用来获得某一个日期从前30个交易日为止的每天的涨停数和跌停数
     * @param date
     * @return
     */
    @Override
    public ArrayList<limitUpAndDownNumsDTO> getLimitUpAndDownNumber(String date) {
        ArrayList<limitUpAndDownNumsDTO> resultList=new ArrayList<limitUpAndDownNumsDTO>();

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        String realDate=DateConvert.getRealEndDate(date,allDateList);

        HashMap<String,Date> timeMap = new HashMap<String, Date>();
        timeMap.put("start",Date.valueOf(DateConvert.getLastNDate(allDateList,realDate,40)));
        timeMap.put("end",Date.valueOf(realDate));
        ArrayList<DayKLine> allStockInfoList= (ArrayList<DayKLine>) dayklinemapper.getStocksByTimes(timeMap);

        //      日期            股票编号 单支股票的信息
        HashMap<String,HashMap<String,DayKLine>> allStockMap=new HashMap<String, HashMap<String, DayKLine>>();

        for(int count=allDateList.indexOf(DateConvert.getLastNDate(allDateList,realDate,40));count<=allDateList.indexOf(realDate);count++){
            HashMap<String,DayKLine> oneDayMap=new HashMap<String, DayKLine>();
            allStockMap.put(allDateList.get(count),oneDayMap);
        }

        for(int count=0;count<allStockInfoList.size();count++){

            String oneStockDate=DateConvert.dateToString(allStockInfoList.get(count).getStockDate());
            allStockMap.get(oneStockDate).put(allStockInfoList.get(count).getStockCode(),allStockInfoList.get(count));

        }


        for(int count=30;count>=1;count--){
            String lastDate=DateConvert.getLastNDate(allDateList,realDate,count+1);
            String currentDate=DateConvert.getLastNDate(allDateList,realDate,count);

            int limitUpNumber=0;
            int limitDownNumber=0;

//            ArrayList<DayKLine> currentDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(currentDate));
//            HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
//            for(int index=0;index<currentDayKLineList.size();index++){
//                nowStockMap.put(currentDayKLineList.get(index).getStockCode(),currentDayKLineList.get(index));
//            }
            HashMap<String,DayKLine> nowStockMap=allStockMap.get(currentDate);

//            ArrayList<DayKLine> lastDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(lastDate));
//            HashMap<String,DayKLine> lastStockMap=new HashMap<String, DayKLine>();
//            for(int index=0;index<lastDayKLineList.size();index++){
//                lastStockMap.put(lastDayKLineList.get(index).getStockCode(),lastDayKLineList.get(index));
//            }
            HashMap<String,DayKLine> lastStockMap=allStockMap.get(lastDate);


            for (Map.Entry<String, DayKLine> entry : nowStockMap.entrySet()) {

                String currentCode=entry.getKey();
                if(lastStockMap.containsKey(currentCode)){
                    double lastPrice=lastStockMap.get(currentCode).getClosePrice();
                    double nowPrice=nowStockMap.get(currentCode).getClosePrice();

                    if(StockChangeHelper.isLimitUp(lastPrice,nowPrice)){
                        limitUpNumber++;
                    }

                    if(StockChangeHelper.isLimitDown(lastPrice,nowPrice)){
                        limitDownNumber++;
                    }
                }


            }


//            for(int index=0;index<currentDayKLineList.size();index++){
//                String currentCode=currentDayKLineList.get(index).getStockCode();
//                if(nowStockMap.containsKey(currentCode)&&lastStockMap.containsKey(currentCode)){
//                    double lastPrice=lastStockMap.get(currentCode).getClosePrice();
//                    double nowPrice=nowStockMap.get(currentCode).getClosePrice();
//
//                    if(StockChangeHelper.isLimitUp(lastPrice,nowPrice)){
//                        limitUpNumber++;
//                    }
//
//                    if(StockChangeHelper.isLimitDown(lastPrice,nowPrice)){
//                        limitDownNumber++;
//                    }
//                }
//            }

            limitUpAndDownNumsDTO limitUpAndDownNumsdto=new limitUpAndDownNumsDTO();
            limitUpAndDownNumsdto.setDate(currentDate);
            limitUpAndDownNumsdto.setUpNumber(limitUpNumber);
            limitUpAndDownNumsdto.setDownNumber(limitDownNumber);

            resultList.add(limitUpAndDownNumsdto);

        }
        return resultList;
    }

    /**
     * 传入的日期格式为2007-01-02
     * @param date
     * @return
     */
    @Override
    public boolean isMarketDateValid(String date) {
        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        HashSet<String> allDateSet=new HashSet<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateSet.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        if(allDateSet.contains(date)){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param date
     * @param blockCode 格式为"sh000300"
     * @return
     */
    @Override
    public boolean isBlockDateValid(String date, String blockCode) {
        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getBlockAllDate(blockCode);

        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        String realDate=DateConvert.getRealEndDate(date,allDateList);




        HashSet<String> allDateSet=new HashSet<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateSet.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        if(allDateSet.contains(realDate)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<String> getUserAllOptionalStock(String userId) {
        return dayklinemapper.getUserAllStock(userId);
    }

    @Override
    public boolean addOneNewOptionalStock(String userId, String stockCode) {

        ArrayList<String> userAllStockList=dayklinemapper.getUserAllStock(userId);

        if(userAllStockList.contains(stockCode)){
            return false;
        }else{

            dayklinemapper.insertOneOptionalStock(userId,stockCode);
            return true;
        }

    }

    @Override
    public boolean deleteOneOptionalStock(String userId, String stockCode) {
        ArrayList<String> userAllStockList=dayklinemapper.getUserAllStock(userId);

        if(userAllStockList.contains(stockCode)){
            dayklinemapper.deleteOneOptionalStock(userId,stockCode);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteUserAllOptionalStock(String userId) {

        ArrayList<String> userAllStockList = dayklinemapper.getUserAllStock(userId);

        if (userAllStockList.size() == 0) {
            return false;
        } else {
            for (int count = 0; count < userAllStockList.size(); count++) {
                String stockCode = userAllStockList.get(count);
                dayklinemapper.deleteOneOptionalStock(userId, stockCode);


            }

            return true;

        }
    }

    @Override
    public String getStockCodeByName(String stockName) {
        ArrayList<StockBasicInfo> stockBasicInfoArrayList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();

        for(int count=0;count<stockBasicInfoArrayList.size();count++){
            if(stockBasicInfoArrayList.get(count).getName().equals(stockName)){
                return stockBasicInfoArrayList.get(count).getCode();
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> getStockCodesByNames(ArrayList<String> stockNameList) {
        ArrayList<String> codeList=new ArrayList<String>();

        ArrayList<StockBasicInfo> stockBasicInfoArrayList= (ArrayList<StockBasicInfo>) dayklinemapper.getAllStockInfos();
        HashMap<String,String> nameToCodeMap=new HashMap<String, String>();

        for(int count=0;count<stockBasicInfoArrayList.size();count++){
            nameToCodeMap.put(stockBasicInfoArrayList.get(count).getName(),stockBasicInfoArrayList.get(count).getCode());
        }




        if(stockNameList.size()!=0){
            for(int count=0;count<stockNameList.size();count++){
                String stockName=stockNameList.get(count);

                if(nameToCodeMap.containsKey(stockName)){
                    codeList.add(nameToCodeMap.get(stockName));
                }

            }




        }
        return codeList;
    }


}
