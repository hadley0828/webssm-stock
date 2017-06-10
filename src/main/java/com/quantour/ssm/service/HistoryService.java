package com.quantour.ssm.service;

import com.quantour.ssm.dto.UserHistory.StockRecordDTO;
import com.quantour.ssm.dto.UserHistory.StrategyResultRecordDTO;
import com.quantour.ssm.model.StockRecord;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/25.
 * 全部测试
 */
public interface HistoryService {

    /**
     * 根据用户id获得该用户全部的个股浏览记录
     * @param userId
     * @return
     */
    public ArrayList<StockRecordDTO> getUserAllStockRecord(String userId,String date);

    /**
     * 在数据库中创建一条新的个股浏览记录
     * @return
     */
    public boolean createNewStockRecord(StockRecordDTO stockRecordDTO);

    /**
     * 删除一条历史浏览记录
     * @param userId
     * @param dateTime
     * @return
     */
    public boolean deleteOneStockRecord(String userId,String dateTime);

    /**
     * 删除一个用户的全部历史浏览记录
     * @param userId
     * @return
     */
    public boolean deleteUserAllStockRecord(String userId);

    /**
     * 获取用户全部的策略结果生成记录
     * @param userid
     * @return
     */
    public ArrayList<StrategyResultRecordDTO> getUserAllStrategyRecord(String userid);

    /**
     * 在数据库中创建一条新的策略结果生成记录
     * @param strategyResultRecordDTO
     * @return
     */
    public boolean createNewStrategyRecord(StrategyResultRecordDTO strategyResultRecordDTO);

    /**
     * 删除一条策略结果生成记录
     * @param userId
     * @param resultTime
     * @return
     */
    public boolean deleteOneStrategyRecord(String userId,String resultTime);

    /**
     * 删除一个用户的所有策略结果生成记录
     * @param userId
     * @return
     */
    public boolean deleteUserAllStrategyRecord(String userId);







}
