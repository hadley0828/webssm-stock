package com.quantour.ssm.service;

import com.quantour.ssm.model.Block;
import com.quantour.ssm.model.Stock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by loohaze on 2017/5/11.
 */
public interface StockService {

    //下面来自于StockDataService
    /**
     * 根据股票编码获得该股票的所有日期
     * @param code
     * @return
     */
    public ArrayList<String> getAllDateByCode(String code);

    /**
     * 根据股票编码和日期获得该股票当天信息
     * @param code
     * @param date
     * @return
     */
    public Stock getOneStock(String code, String date);

    /**
     * 根据股票编码和起始、结束日期获得该股票此时间段所有信息
     * @param code
     * @param startDate
     * @param endDate
     * @return
     */
    public ArrayList<Stock> getTimesStocks(String code, String startDate, String endDate);

    /**
     * 根据日期获得该日期所有股票信息
     * @param date
     * @return
     */
    public ArrayList<Stock> getOneDayStocks(String date);

    /**
     * 根据股票编码和日期获得该股票前一天信息
     * @param code
     * @param date
     * @return
     */
    public Stock getYesterdayStock(String code, String date);

    /**
     * 根据日期获得该日期前一个交易日的所有股票信息
     * @param date
     * @return
     */
    public ArrayList<Stock> getYesterdayStocks(String date);

    /**
     * 根据股票代码获得该股票所有交易日的信息
     * @param code
     * @return
     */
    public ArrayList<Stock> getAllStocksByCode(String code);

    /**
     * 获取所有股票名称和编号
     * @return
     */
    public ArrayList<String> getAllStockNames();

    /**
     * 获得一个股票向前n个交易日的信息
     * @param code
     * @param date
     * @param days
     * @return
     */
    public Stock getLastDaysStockInfo(String code, String date, int days);

    /**
     * 获得数据区中从开始日期到结束日期的所有交易日
     * @return
     */
    public ArrayList<String> getMarketDates();

    /**
     * 获得开始和结束日期之间的一个哈希表
     * @param sdate
     * @param ldate
     * @return
     */
    public HashMap<String,ArrayList<Stock>> getStockMap(String sdate, String ldate);

    //下面来自于StaticDataService
    /**
     * 获得某基准板块所有股票编号
     * @param plate
     * @return
     */
    public ArrayList<String> getAllCodeByPlate(String plate);

    /**
     * 获得某一天的某板块信息
     * @param block
     * @param date
     * @return
     */
    public Block getOneBlockInfo(String block, String date);

    /**
     * 获得一段时间内的某板块信息
     * @param block
     * @param sDate
     * @param lDate
     * @return
     */
    public ArrayList<Block> getTimesBlockInfo(String block, String sDate, String lDate);

    /**
     * 获得自选板块所有股票编号
     * @param plateName
     * @return
     */
    public ArrayList<String> getOnePlateStockcodes(String plateName);

    /**
     * 获得所有自选板块的名称
     * @return
     */
    public ArrayList<String> getAllPlatesNames();

    /**
     * 获得一个基准板块的所有交易日
     * @param blockCode
     * @return
     */
    public ArrayList<String> getBlockAllDate(String blockCode);

    /**
     * 获得一个股票的所属板块
     * @param code
     * @return
     */
    public ArrayList<String> getBlockByStock(String code);




}
