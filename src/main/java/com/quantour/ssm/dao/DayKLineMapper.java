package com.quantour.ssm.dao;

import com.quantour.ssm.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DayKLineMapper {
    int deleteByPrimaryKey(DayKLineKey key);

    int insert(DayKLine record);

    int insertSelective(DayKLine record);

    DayKLine selectByPrimaryKey(DayKLineKey key);

    int updateByPrimaryKeySelective(DayKLine record);

    int updateByPrimaryKey(DayKLine record);


    /**
     * 获得某一支股票的所有日期
     * @param code
     * @return
     */
    public List<String> getAllDateByCode(String code);

    /**
     * 根据code 和 date 查询某一支股票信息
     * 用Stockkey封装参数(复合主键)
     * @param dayKLineKey
     * @return
     */
    public DayKLine getOneDayKLine(DayKLineKey dayKLineKey);

    /**
     * 获得一段时间内的股票信息
     * 用hashmap封装参数 key值分别为 String code, String startDate, String endDate
     * @param map
     * @return
     */
    public List<DayKLine> getTimesDayKLines(HashMap map);

    /**
     * 根据日期获得该日期所有股票信息
     * 注意日期的数据类型是 Java.sql.Date
     * @param date
     * @return
     */
    public List<DayKLine> getOneDayDayKLine(Date date);


    /**
     * 根据股票编码和日期获得该股票前一天信息
     * 用Stockkey封装参数(复合主键)
     * @param dayKLineKey
     * @return
     */
    public DayKLine getYesterdayDayKLine(DayKLineKey dayKLineKey);

    /**
     * 根据日期获得该日期前一个交易日的所有股票信息
     * @param date
     * @return
     */
    public List<DayKLine> getYesterdayDayKLine(Date date);

    /**
     * 根据股票代码获得该股票所有交易日的信息
     * @param code
     * @return
     */
    public List<DayKLine> getAllDayKLinesByCode(String code);

    /**
     * 获取所有股票名称和编号
     * @return
     */
    public List<String> getAllStockNames();

    /**
     * 获得一个股票向前n个交易日的信息
     * @param dayKLineKey
     * @param days
     * @return
     */
    public DayKLine getLastDaysDayKLineInfo(DayKLineKey dayKLineKey, int days);

    /**
     * 获得数据区中从开始日期到结束日期的所有交易日
     * @return
     */
    public List<String> getMarketDates();

    /**
     * 获得开始和结束日期之间的一个哈希表
     * @param sdate
     * @param ldate
     * @return
     */
    public HashMap<String,ArrayList<DayKLine>> getDayKLineMap(Date sdate, Date ldate);


    //下面来自于StaticDataService
    /**
     * 获得某基准板块所有股票编号
     * @param block
     * @return
     */
    public List<String> getAllCodeByBlock(String block);

    /**
     * 获得某一天的某板块信息
     * @param dayKLineKey
     * @return
     */
    public DayKLine getOneBlockInfo(DayKLineKey dayKLineKey);

    /**
     * 获得一段时间内的某板块信息
     * @param block
     * @param sDate
     * @param lDate
     * @return
     */
    public List<DayKLine> getTimesBlockInfo(String block, Date sDate, Date lDate);

    /**
     * 获得自选板块所有股票编号
     * @param plateName
     * @return
     */
    public List<String> getOnePlateStockcodes(String plateName);

    /**
     * 获得所有自选板块的名称
     * @return
     */
    public List<String> getAllPlatesNames();

    /**
     * 获得一个基准板块的所有交易日
     * @param block
     * @return
     */
    public List<String> getBlockAllDate(String block);

    /**
     * 获得一个股票的所属板块
     * @param code
     * @return
     */
    public List<String> getBlockByStock(String code);




}