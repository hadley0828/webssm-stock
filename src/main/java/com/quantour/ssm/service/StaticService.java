package com.quantour.ssm.service;

import com.quantour.ssm.dto.oneExtraProfitDTO;
import com.quantour.ssm.dto.strategyResultDTO;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/17.
 */
public interface StaticService {

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
    public strategyResultDTO getStraOneResult(int formDays, int holdDays, String sDate, String lDate, int type, ArrayList<String> codeList, String blockCode);


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
    public ArrayList<oneExtraProfitDTO> getOneExtraProfit(int daysType, int days, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode);

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
