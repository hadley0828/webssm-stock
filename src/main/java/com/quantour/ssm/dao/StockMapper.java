package com.quantour.ssm.dao;

import com.quantour.ssm.model.*;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface StockMapper {
    int deleteByPrimaryKey(StockKey key);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(StockKey key);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    /**
     * 获得某一支股票的所有日期
     * @param code
     * @return
     */
    public List<String> getAllDateByCode(String code);

    /**
     * 根据code 和 date 查询某一支股票信息
     * 用Stockkey封装参数(复合主键)
     * @param stockKey
     * @return
     */
    public Stock getOneStock(StockKey stockKey);

    /**
     * 获得一段时间内的股票信息
     * 用hashmap封装参数 key值分别为 String code, String startDate, String endDate
     * @param map
     * @return
     */
    public List<Stock> getTimesStocks(HashMap map);



    /**
     * 根据日期获得该日期所有股票信息
     * 注意日期的数据类型是 Java.sql.Date
     * @param date
     * @return
     */
    public List<Stock> getOneDayStocks(Date date);


    /**
     * 根据股票编码和日期获得该股票前一天信息
     * 用Stockkey封装参数(复合主键)
     * @param stockKey
     * @return
     */
    public Stock getYesterdayStock(StockKey stockKey);

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