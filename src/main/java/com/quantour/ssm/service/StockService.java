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
     * 根据股票编号的列表和日期按照顺序获得这些股票的当天信息
     * @param codeList
     * @param date
     * @return
     */
    public ArrayList<stockDTO> getSeveralStockInfo(ArrayList<String> codeList,String date);

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
     * 该方法用来获得某一天全部股票中涨幅前n名的股票的List
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

    //-------------------------------------------------------------------
    //处理和自选股相关的接口


    /**
     * 获取一个用户的全部自选股编号
     * @param userId
     * @return
     */
    public ArrayList<String> getUserAllOptionalStock(String userId);

    /**
     * 添加一个自选股票
     * @param userId
     * @param stockCode
     * @return
     */
    public boolean addOneNewOptionalStock(String userId,String stockCode);

    /**
     * 删除一个自选股票
     * @param userId
     * @param stockCode
     * @return
     */
    public boolean deleteOneOptionalStock(String userId,String stockCode);

    /**
     * 删除一个用户全部的自选股票
     * @param userId
     * @return
     */
    public boolean deleteUserAllOptionalStock(String userId);


}
