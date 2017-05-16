package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dto.*;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.DayKLineKey;
import com.quantour.ssm.model.StockBasicInfo;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.StockCalculator;
import com.quantour.ssm.util.StockChangeHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

    //关于取前一天的DayKline可能会出bug  不能取不存在的日期
    @Override
    public stockDTO getStockInfo(String code, String date) {
        DayKLineKey dayKLineKey=new DayKLineKey();
        dayKLineKey.setStockCode(code);
        dayKLineKey.setStockDate(Date.valueOf(date));


        DayKLine dayKLine=dayklinemapper.getOneDayKLine(dayKLineKey);

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayklinemapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();
        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }
        String lastDate=DateConvert.getLastNDate(allDateList,date,1);


        dayKLineKey.setStockCode(code);
        dayKLineKey.setStockDate(Date.valueOf(lastDate));
        DayKLine lastDayKLine=dayklinemapper.getOneDayKLine(dayKLineKey);


        StockBasicInfo stockBasicInfo=dayklinemapper.getOneStockInfo(code);


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


        return stockdto;
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
    public marketDTO getMarketInfo(String date) {
        marketDTO marketdto=new marketDTO();

        String name="上圳";
        String Date=date;
        long Volume=0;

        int limitup=0; //这里的涨停没有考虑ST股票
        int limitdown=0;
        int upfive=0;
        int downfive=0;
        int upnum=0;		//开盘-收盘小于-5%*上一个交易日收盘价的股票个数;
        int downnum=0;	//开盘-收盘大于5%*上一个交易日收盘价的股票个数;



        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(date));
        HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<dayKLineArrayList.size();count++){
            nowStockMap.put(dayKLineArrayList.get(count).getStockCode(),dayKLineArrayList.get(count));
        }
        ArrayList<DayKLine> lastDayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getYesterdayDayKLines(DateConvert.stringToDate(date));
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
        ArrayList<DayKLine> firstDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getTimesDayKLines(map);


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

        map=new HashMap<String, Object>();
        map.put("code",secondCode);
        map.put("start",Date.valueOf(realSDate));
        map.put("end",Date.valueOf(realLDate));
        ArrayList<DayKLine> secondDayKLineList= (ArrayList<DayKLine>) dayklinemapper.getTimesDayKLines(map);

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

        return false;
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


    @Override
    public ArrayList<String> getAllCodeAndName() {
        return null;
    }

    @Override
    public ArrayList<String> getAllPlateName() {
        return null;
    }

    @Override
    public ArrayList<String> getPlateAllStockCode(String plateName) {
        return null;
    }

    @Override
    public ArrayList<String> getOneStockAllPlate(String stockCode) {
        return null;
    }

    @Override
    public ArrayList<waveDTO> getTopNCodesByDays(int n, String date, int changeDays) {
        return null;
    }

    @Override
    public ArrayList<limitUpAndDownNumsDTO> getLimitUpAndDownNumber(String date) {
        return null;
    }

    @Override
    public boolean isMarketDateValid(String date) {
        return false;
    }

    @Override
    public boolean isBlockDateValid(String date, String blockCode) {
        return false;
    }

    @Override
    public strategyResultDTO getStraOneResult(int formDays, int holdDays, String sDate, String lDate, int type, ArrayList<String> codeList, String blockCode) {
        return null;
    }

    @Override
    public ArrayList<oneExtraProfitDTO> getOneExtraProfit(int daysType, int days, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        return null;
    }

    @Override
    public strategyResultDTO getStraTwoResult(int averageDays, int holdDays, int stockNumbers, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        return null;
    }

    @Override
    public ArrayList<oneExtraProfitDTO> getTwoExtraProfit(int averageDays, int stockNumbers, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        return null;
    }
}
