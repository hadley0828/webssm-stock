package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.StockMapper;
import com.quantour.ssm.dto.*;
import com.quantour.ssm.model.*;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.StockCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

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

        ArrayList<DayKLine> dayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getOneDayDayKLines(DateConvert.stringToDate(date));
        HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
        for(int count=0;count<dayKLineArrayList.size();count++){
            nowStockMap.put(dayKLineArrayList.get(count).getStockCode(),dayKLineArrayList.get(count));
        }
        ArrayList<DayKLine> lastDayKLineArrayList= (ArrayList<DayKLine>) dayklinemapper.getYesterdayDayKLines(DateConvert.stringToDate(date));



        return marketdto;
    }

    @Override
    public ArrayList<averageDTO> getAverageLine(String code, String sDate, String lDate, int days) {
        return null;
    }

    @Override
    public ArrayList<compareDTO> getCompareInfo(String firstCode, String secondCode, String sDate, String lDate) {
        return null;
    }

    @Override
    public boolean isStockValid(String input) {
        return false;
    }

    @Override
    public boolean isDateValid(String input, String date) {
        return false;
    }

    @Override
    public boolean isDateValid(String date) {
        return false;
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
