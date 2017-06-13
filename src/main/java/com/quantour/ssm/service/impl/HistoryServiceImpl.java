package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.HistoryMapper;
import com.quantour.ssm.dto.UserHistory.StockRecordDTO;
import com.quantour.ssm.dto.UserHistory.StrategyResultRecordDTO;
import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.model.*;
import com.quantour.ssm.service.HistoryService;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.NumberConvert;
import com.quantour.ssm.util.StockCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zhangzy on 2017/5/25.
 * 用来处理和历史记录相关的接口 历史记录包括个股信息和执行策略
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HistoryServiceImpl implements HistoryService{

    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private DayKLineMapper dayKLineMapper;


    //个股浏览记录 已测试
    @Override
    public ArrayList<StockRecordDTO> getUserAllStockRecord(String userId,String date) {
        ArrayList<StockRecord> stockRecordArrayList=historyMapper.getUserAllStockRecord(userId);

        ArrayList<String> codeList=new ArrayList<String>();

        ArrayList<StockRecordDTO> stockRecordDTOArrayList=new ArrayList<StockRecordDTO>();

        for(int count=0;count<stockRecordArrayList.size();count++){
            StockRecordDTO stockRecordDTO=new StockRecordDTO(stockRecordArrayList.get(count));
            stockRecordDTOArrayList.add(stockRecordDTO);

            codeList.add(stockRecordArrayList.get(count).getCode_id());
        }

        //codeList里面是全部的需要获取具体数据的股票编号

        ArrayList<Date> allSqlDateList= (ArrayList<Date>) dayKLineMapper.getMarketDates();
        ArrayList<String> allDateList=new ArrayList<String>();

        for(int count=0;count<allSqlDateList.size();count++){
            allDateList.add(DateConvert.dateToString(allSqlDateList.get(count)));
        }

        String realDate=DateConvert.getRealEndDate(date,allDateList);


        //stockDTOArrayList是当日的这些股票的信息
        ArrayList<stockDTO> stockDTOArrayList=new ArrayList<stockDTO>();


        ArrayList<DayKLine> nowStockList= (ArrayList<DayKLine>) dayKLineMapper.getOneDayDayKLines(DateConvert.stringToDate(realDate));
        ArrayList<DayKLine> lastStockList= (ArrayList<DayKLine>) dayKLineMapper.getOneDayDayKLines(DateConvert.stringToDate(DateConvert.getLastNDate(allDateList,realDate,1)));

        HashMap<String,DayKLine> nowStockMap=new HashMap<String, DayKLine>();
        HashMap<String,DayKLine> lastStockMap=new HashMap<String, DayKLine>();

        for(int count=0;count<nowStockList.size();count++){
            nowStockMap.put(nowStockList.get(count).getStockCode(),nowStockList.get(count));
        }

        for(int count=0;count<lastStockList.size();count++){
            lastStockMap.put(lastStockList.get(count).getStockCode(),lastStockList.get(count));
        }

        ArrayList<StockBasicInfo> stockBasicInfoArrayList= (ArrayList<StockBasicInfo>) dayKLineMapper.getAllStockInfos();
        HashMap<String,StockBasicInfo> stockBasicInfoHashMap=new HashMap<String, StockBasicInfo>();


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

                stockdto.setUplift("0.0%");
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
                    lastDayKLine=dayKLineMapper.getYesterdayDayKLine(dayKLineKey);

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

                stockdto.setUplift(NumberConvert.doubleToPercentageString(StockCalculator.getUplift(lastDayKLine.getClosePrice(),dayKLine.getClosePrice())));
                stockdto.setAdjClose(dayKLine.getClosePrice());
                stockdto.setVolume(Math.round(dayKLine.getVolume()));
                stockdto.setLogYield(StockCalculator.getLogYield(lastDayKLine.getClosePrice(),dayKLine.getClosePrice()));

                stockDTOArrayList.add(stockdto);


            }
        }


        for(int count=0;count<stockRecordDTOArrayList.size();count++){
            stockDTO stockdto=stockDTOArrayList.get(count);

            stockRecordDTOArrayList.get(count).setStock_name(stockdto.getName());
            stockRecordDTOArrayList.get(count).setMarket(stockdto.getMarket());
            stockRecordDTOArrayList.get(count).setOpenPrice(stockdto.getOpenPrice());
            stockRecordDTOArrayList.get(count).setClosePrice(stockdto.getClosePrice());
            stockRecordDTOArrayList.get(count).setHighPrice(stockdto.getHighPrice());
            stockRecordDTOArrayList.get(count).setLowPrice(stockdto.getLowPrice());
            stockRecordDTOArrayList.get(count).setVolume(stockdto.getVolume());
            stockRecordDTOArrayList.get(count).setLogYield(stockdto.getLogYield());




        }
        return stockRecordDTOArrayList;
    }

    @Override
    public boolean createNewStockRecord(StockRecordDTO stockRecordDTO) {

        String userId=stockRecordDTO.getUser_id();
        ArrayList<StockRecordDTO> userAllStock=getUserAllStockRecord(userId,"2017-05-04");

        HashSet<String> userAllStockSet=new HashSet<String>();
        for(int count=0;count<userAllStock.size();count++){
            userAllStockSet.add(userAllStock.get(count).getCode_id());
        }

        if(userAllStockSet.contains(stockRecordDTO.getCode_id())){
            return false;
        }else {
            StockRecord stockRecord=new StockRecord();
            stockRecord.setUser_id(stockRecordDTO.getUser_id());
            stockRecord.setCode_id(stockRecordDTO.getCode_id());
            stockRecord.setDate_time(stockRecordDTO.getDate_time());

            historyMapper.insertOneStockRecord(stockRecord);

            return true;
        }


    }

    @Override
    public boolean deleteOneStockRecord(String userId, String dateTime) {
        historyMapper.deleteOneStockRecord(userId,dateTime);

        return true;
    }

    @Override
    public boolean deleteUserAllStockRecord(String userId) {

        historyMapper.deleteAllStockRecord(userId);

        return true;
    }

    @Override
    public ArrayList<StrategyResultRecordDTO> getUserAllStrategyRecord(String userid) {
        ArrayList<StrategyRecord> strategyRecordArrayList=historyMapper.getUserAllStrategyRecord(userid);

        ArrayList<StrategyResultRecordDTO> strategyResultRecordDTOArrayList=new ArrayList<StrategyResultRecordDTO>();

        for(int count=0;count<strategyRecordArrayList.size();count++){
            StrategyResultRecordDTO strategyResultRecordDTO=new StrategyResultRecordDTO(strategyRecordArrayList.get(count));
            strategyResultRecordDTOArrayList.add(strategyResultRecordDTO);
        }

        return strategyResultRecordDTOArrayList;
    }

    @Override
    public boolean createNewStrategyRecord(StrategyResultRecordDTO strategyResultRecordDTO) {
        StrategyRecord strategyRecord=new StrategyRecord(strategyResultRecordDTO);

        historyMapper.insertOneStrategyRecord(strategyRecord);

        return true;
    }

    @Override
    public boolean deleteOneStrategyRecord(String userId, String resultTime) {
        historyMapper.deleteOneStrategyRecord(userId,resultTime);

        return true;
    }

    @Override
    public boolean deleteUserAllStrategyRecord(String userId) {

        historyMapper.deleteAllStrategyRecord(userId);

        return true;
    }
}
