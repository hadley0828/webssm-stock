package com.quantour.ssm.dao;

import com.quantour.ssm.model.*;
import com.quantour.ssm.util.CodeIndustryMap;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface DayKLineMapper    {
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
     *
     * 获得一段时间内的所有股票信息
     * 用hashmap封装参数 key值分别为 String start, String end
     * @param map
     * @return
     */
    public List<DayKLine> getStocksByTimes(HashMap map);

    /**
     * 根据日期获得该日期所有股票信息
     * 注意日期的数据类型是 Java.sql.Date
     * @param date
     * @return
     */
    public List<DayKLine> getOneDayDayKLines(Date date);


    public DayKLine getYesterdayDayKLine(DayKLineKey dayKLineKey);


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
     * 获得单个股票基本信息
     * @param code
     * @return
     */
    public StockBasicInfo getOneStockInfo(String code);
    /**
     *
     * 获得数据区中从开始日期到结束日期的所有交易日
     * @return
     */
    public List<Date> getMarketDates();




    //下面来自于StaticDataService
    /**
     * 获得sme(中小板)板块所有股票编号
     * @return
     */
    public List<String> getAllCodeBySmeBlock();

    /**
     * 获得gem(创业板)板块所有股票编号
     * sme(中小板) gem(创业板) hs300(沪深300) sz50(上证50) zz500(中证500)
     * @return
     */
    public List<String> getAllCodeByGemBlock();

    /**
     * 获得hs300(沪深300)板块所有股票编号
     * sme(中小板) gem(创业板) hs300(沪深300) sz50(上证50) zz500(中证500)
     * @return
     */
    public List<String> getAllCodeByHs300Block();

    /**
     * 获得sz50(上证50)板块所有股票编号
     * sme(中小板) gem(创业板) hs300(沪深300) sz50(上证50) zz500(中证500)
     * @return
     */
    public List<String> getAllCodeBySz50Block();


    /**
     * 获得zz500(中证500)板块所有股票编号
     * sme(中小板) gem(创业板) hs300(沪深300) sz50(上证50) zz500(中证500)
     * @return
     */
    public List<String> getAllCodeByZz500Block();


    /**
     * 获得某一天的某板块信息
     * stock_code: 板块号 { sh000016:上证50  sh000300:沪深300 sh000905:中证500 sz399005:中小板 sz399006:创业板}
     * @param dayKLineKey
     * @return
     */
    public DayKLine getOneBlockInfo(DayKLineKey dayKLineKey);

    /**
     * 用hashmap封装参数 key值分别为 String block, String start, String end
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
     * 获得一个股票的所属行业板块
     * @param code
     * @return
     */
    public List<String> getIndustryByStock(String code);

    /**
     * 获得一个股票的所属地域板块
     * @param code
     * @return
     */
    public List<String> getAreaByStock(String code);

    /**
     * 获得一个股票的所属概念板块
     * @param code
     * @return
     */
    public List<String> getConceptByStock(String code);


    /**
     * 在调用这个方法时，传入的参数为新建的自定义类 CodeIndustryMap
     * 通过dayKLineMapper.getAllIndustryAndCode(new CodeIndustryMap("code","industry"));  调用
     *
     * 获得行业与股票编号的一一对应  key=code  value=industry
     * @return
     */
    public HashMap<String,String> getAllIndustryAndCode(CodeIndustryMap param);


    //下面是和自选股票相关的功能

    /**
     * 获得一个用户全部的自选股票编号
     * @param userid
     * @return
     */
    public ArrayList<String> getUserAllStock(String userid);

    /**
     * 添加一条自选股票
     * @param userid
     * @param stockCode
     * @return
     */
    public int insertOneOptionalStock(String userid,String stockCode);

    /**
     * 删除一条自选股票
     * @param userid
     * @param stockCode
     * @return
     */
    public int deleteOneOptionalStock(String userid,String stockCode);

    /**
     * 获得一个股票的全部新闻
     * @param code
     * @return
     */
    public ArrayList<StockNews> getOneStockAllNews(String code);

    /**
     * 获得全部股票的全部新闻
     * @return
     */
    public ArrayList<StockNews> getAllNews();


    /**
     * 获得一个股票的全部业绩预测
     * @param code
     * @return
     */
    public AchievementForecast getOneStockForecast(String code);

    /**
     * 获得全部的业绩预测
     * @return
     */
    public ArrayList<AchievementForecast> getAllForecast();


    /**
     * 获得一个股票的分配预案
     * @param code
     * @return
     */
    public AllocationPlan getOneAllocationPlan(String code);

    /**
     * 获得全部的分配预案
     * @return
     */
    public ArrayList<AllocationPlan> getAllAllocationPlan();
}