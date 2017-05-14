package com.quantour.ssm.dao;

import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.DayKLineKey;
import com.quantour.ssm.model.StockBasicInfo;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Repository
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
     * 用hashmap封装参数 key值分别为 String code, String start, String end
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
    public List<DayKLine> getOneDayDayKLines(Date date);


    /**
     * 根据日期获得该日期前一个交易日的所有股票信息
     * @param date
     * @return
     */
    public List<DayKLine> getYesterdayDayKLines(Date date);

    /**
     * 根据股票代码获得该股票所有交易日的信息
     * @param code
     * @return
     */
    public List<DayKLine> getAllDayKLinesByCode(String code);

    /**
     * 获取所有股票基本信息
     * @return
     */
    public List<StockBasicInfo> getAllStockInfos();


    /**
     *
     * 获得数据区中从开始日期到结束日期的所有交易日
     * @return
     */
    public List<Date> getMarketDates();




    //下面来自于StaticDataService
    /**
     * 未完成
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
     * 用hashmap封装参数 key值分别为 String block, String startDate, String endDate
     * 获得一段时间内的某板块信息
     * @param map
     * @return
     */
    public List<DayKLine> getTimesBlockInfo(HashMap map);

    /**
     * new interface
     * 获得一个概念板块所有股票编号
     * @param blockName
     * @return
     */
    public List<String> getConceptBlockStockCodes(String blockName);

    /**
     * new interface
     * 获得一个行业板块所有股票编号
     * @param blockName
     * @return
     */
    public List<String> getIndustryBlockStockCodes(String blockName);

    /**
     * new interface
     * 获得一个地域板块所有股票编号
     * @param blockName
     * @return
     */
    public List<String> getAreaBlockStockCodes(String blockName);

    /**
     * new interface
     * 获得所有概念板块
     * @return
     */
    public List<String> getAllConceptBlock();

    /**
     * new interface
     * 获得所有行业板块
     * @return
     */
    public List<String> getAllIndustryBlock();

    /**
     * new interface
     * 获得所有地域板块
     * @return
     */
    public List<String> getAllAreaBlock();


    /**
     * 获得一个基准板块的所有交易日
     * @param block
     * @return
     */
    public List<Date> getBlockAllDate(String block);

    /**
     * 未完成
     * 获得一个股票的所属板块
     * @param code
     * @return
     */
    public List<String> getBlockByStock(String code);




}