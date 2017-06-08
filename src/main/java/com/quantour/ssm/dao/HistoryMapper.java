package com.quantour.ssm.dao;

import com.quantour.ssm.model.StockRecord;
import com.quantour.ssm.model.StrategyRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/8.
 * 用于处理和历史记录相关的数据库功能 包括个股的历史记录和策略结果的历史记录
 */
@Repository
public interface HistoryMapper {

    /**
     * 根据用户的id获得这个用户的全部个股浏览记录
     * @param userid
     * @return
     */
    public ArrayList<StockRecord> getUserAllStockRecord(String userid);

    /**
     * 根据用户的id和记录的时间或者一条浏览记录
     * @param userid
     * @param date_time
     * @return
     */
    public StockRecord selectOneStockRecord(String userid,String date_time);

    /**
     * 根据一个新的stockRecord添加一条浏览记录
     * @param stockRecord
     * @return
     */
    public int insertOneStockRecord(StockRecord stockRecord);


    /**
     * 删除一条浏览记录
     * @param userid
     * @param date_time
     * @return
     */
    public int deleteOneStockRecord(String userid,String date_time);

    public int deleteAllStockRecord(String userid);


    /**
     * 获得一个用户的全部策略生成记录
     * @param userid
     * @return
     */
    public ArrayList<StrategyRecord> getUserAllStrategyRecord(String userid);

    /**
     * 根据用户id和生成时间获得一条策略生成记录
     * @param userid
     * @param result_time
     * @return
     */
    public StrategyRecord selectOneStrategyRecord(String userid,String result_time);

    /**
     * 添加一条策略生成记录
     * @param strategyRecord
     * @return
     */
    public int insertOneStrategyRecord(StrategyRecord strategyRecord);


    /**
     * 删除一条策略生成记录
     * @param userid
     * @param result_time
     * @return
     */
    public int deleteOneStrategyRecord(String userid,String result_time);



    public int deleteAllStrategyRecord(String userid);
}
