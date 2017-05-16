package com.quantour.ssm.service;

import com.quantour.ssm.dto.*;
import com.quantour.ssm.model.Block;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.Stock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by loohaze on 2017/5/11.
 */

//处理迭代一和迭代二逻辑层的接口
public interface StockService {

    /**
     * 根据股票编号和日期获得当天的股票信息
     * @param code
     * @param date
     * @return
     */
    public stockDTO getStockInfo(String code, String date);

    /**
     * 根据股票编号 开始日期 结束日期获得一段时间内的该股票k线图信息
     * @param code
     * @param sDate
     * @param lDate
     * @return
     */
    public ArrayList<klineDTO> getKline(String code, String sDate, String lDate);

    /**
     * 根据日期获得当天上圳市场的市场信息
     * @param date
     * @return
     */
    public marketDTO getMarketInfo(String date);

    /**
     * 根据两个股票编号和开始日期结束日期来获得这两个股票的比较信息 保存在list的0和1中
     * @param firstCode
     * @param secondCode
     * @param sDate
     * @param lDate
     * @return
     */
    public ArrayList<compareDTO> getCompareInfo(String firstCode, String secondCode, String sDate, String lDate);

    /**
     * 根据股票编号或者股票名称来判断这个股票是否存在
     * @param input
     * @return
     */
    public boolean isStockValid(String input);

    /**
     * 根据输入的股票编号或名称以及日期来判断该日期是否有效
     * @param input
     * @param date
     * @return
     */
    public boolean isDateValid(String input,String date);

    /**
     * 根据当天的日期来判断当天是否是交易日
     * @param date
     * @return
     */
    public boolean isDateValid(String date);

    /**
     * 获得所有的股票编号+两个空格+股票名称
     * @return
     */
    public ArrayList<String> getAllCodeAndName();

    /**
     * 获得所有的版块名称
     * @return
     */
    public ArrayList<String> getAllPlateName();

    /**
     * 获得一个版块中所有的股票编号
     * @param plateName
     * @return
     */
    public ArrayList<String> getPlateAllStockCode(String plateName);

    /**
     * 获得一个股票的所有所属板块
     * @param stockCode
     * @return
     */
    public ArrayList<String> getOneStockAllPlate(String stockCode);

    //-------------------------------------------------------------

    /**
     * 该方法用来获得某一天某个股票池中涨幅前n名的股票的List
     * 用于生成排行榜
     * @param n 获得前n名
     * @param date 当天日期
     * @param changeDays 涨跌幅的天数
     * @return
     */
    public ArrayList<waveDTO> getTopNCodesByDays(int n,String date,int changeDays);

    /**
     * 该方法用来获得某一个日期从前30个交易日为止的每天的涨停数和跌停数
     * @param date
     * @return
     */
    public ArrayList<limitUpAndDownNumsDTO> getLimitUpAndDownNumber(String date);

    //---------------------------------------------------------------------

    /**
     * 输入的日期格式 4/3/14  判断这个日期是否存在
     * @param date
     * @return
     */
    public boolean isMarketDateValid(String date);

    /**
     * 输入的日期格式 4/3/14 判断当前基准板块的当前日期信息是否存在
     * @param date
     * @param blockCode
     * @return
     */
    public boolean isBlockDateValid(String date,String blockCode);

    //---------------------------------------------------------------------
    //动量策略的逻辑层
    /**
     * 根据形成期、持有期、开始时间、结束时间、类型编号、股票编号获得股票池的策略和基准的累计收益率比较图数据;
     * 类型是1 表示计算范围是全部的股票  传入的codeList是空
     * 类型是2 表示计算范围是某个板块内的全部股票 传入的codeList的第一项为板块的编号
     * 类型是3 表示计算范围是自选股票池 传入的codeList就是选择的股票编号
     *
     * @param formDays 形成期
     * @param holdDays 持有期
     * @param sDate 开始时间
     * @param lDate 结束时间
     * @param type 类型
     * @param codeList 股票编号的列表
     * @param blockCode 基准板块的编号
     * @return
     */
    public strategyResultDTO getStraOneResult(int formDays, int holdDays, String sDate, String lDate, int type,ArrayList<String> codeList,String blockCode);


    /**
     * 根据时间类型 区间 开始时间 结束时间 股票池类型 股票编号的列表获得股票池的超额收益率关系图数据
     * @param daysType 时间类型 为1表示传入的是形成期 为2表示传入的是持有期
     * @param days 形成期(持有期)
     * @param sDate 开始时间
     * @param lDate 结束时间
     * @param stockType 股票池类型 为1表示全部股票 2表示某个板块的股票 3表示选定股票池
     * @param codeList 股票编号的列表
     * @return
     */
    public ArrayList<oneExtraProfitDTO> getOneExtraProfit(int daysType,int days,String sDate,String lDate,int stockType,ArrayList<String> codeList,String blockCode);

    //均值回归策略

    /**
     * 根据均线日数 持有期 仓内的股票数量 开始时间 结束时间 股票类型 股票编号的列表来获得选定股票池的累计收益率比较图数据
     * @param averageDays 均线日数 5/10/20
     * @param holdDays 持有期
     * @param stockNumbers 持仓的股票数量
     * @param sDate 开始时间
     * @param lDate 结束时间
     * @param stockType 股票池类型 1-3
     * @param codeList 股票编号的列表
     * @param blockCode 基准板块的编号
     * @return
     */
    public strategyResultDTO getStraTwoResult(int averageDays, int holdDays,int stockNumbers, String sDate, String lDate,int stockType, ArrayList<String> codeList,String blockCode);


    /**
     *根据均线日数 开始时间 结束时间 股票池类型 股票编号的列表获得股票池的超额收益率关系图数据
     * @param averageDays 均线日数 5/10/20
     * @param stockNumbers 仓内的股票数量
     * @param sDate 开始时间
     * @param lDate 结束时间
     * @param stockType 股票池类型 1-3
     * @param codeList 股票编号的列表
     * @return
     */
    public ArrayList<oneExtraProfitDTO> getTwoExtraProfit(int averageDays,int stockNumbers,String sDate,String lDate,int stockType,ArrayList<String> codeList,String blockCode);



}
