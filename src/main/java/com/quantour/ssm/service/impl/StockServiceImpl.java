package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.StockMapper;
import com.quantour.ssm.model.Block;
import com.quantour.ssm.model.Stock;
import com.quantour.ssm.model.StockKey;
import com.quantour.ssm.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by loohaze on 2017/5/11.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;


    public ArrayList<String> getAllDateByCode(String code) {
        return (ArrayList<String>) stockMapper.getAllDateByCode(code);
    }

    public Stock getOneStock(String code, String date) {
        StockKey stockKey = new StockKey();
        stockKey.setStockCode(code);
        stockKey.setStockDate(date);
        return stockMapper.getOneStock(stockKey);
    }

    public ArrayList<Stock> getTimesStocks(String code, String startDate, String endDate) {
        return (ArrayList<Stock>) stockMapper.getTimesStocks(code,startDate,endDate);
    }

    public ArrayList<Stock> getOneDayStocks(String date) {
        return null;
    }

    public Stock getYesterdayStock(String code, String date) {
        return null;
    }

    public ArrayList<Stock> getYesterdayStocks(String date) {
        return null;
    }

    public ArrayList<Stock> getAllStocksByCode(String code) {
        return null;
    }

    public ArrayList<String> getAllStockNames() {
        return null;
    }

    public Stock getLastDaysStockInfo(String code, String date, int days) {
        return null;
    }

    public ArrayList<String> getMarketDates() {
        return null;
    }

    public HashMap<String, ArrayList<Stock>> getStockMap(String sdate, String ldate) {
        return null;
    }

    public ArrayList<String> getAllCodeByPlate(String plate) {
        return null;
    }

    public Block getOneBlockInfo(String block, String date) {
        return null;
    }

    public ArrayList<Block> getTimesBlockInfo(String block, String sDate, String lDate) {
        return null;
    }

    public ArrayList<String> getOnePlateStockcodes(String plateName) {
        return null;
    }

    public ArrayList<String> getAllPlatesNames() {
        return null;
    }

    public ArrayList<String> getBlockAllDate(String blockCode) {
        return null;
    }

    public ArrayList<String> getBlockByStock(String code) {
        return null;
    }
}
