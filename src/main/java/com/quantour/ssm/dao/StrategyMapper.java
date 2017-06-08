package com.quantour.ssm.dao;

import com.quantour.ssm.model.CustomizeStrategy;
import com.quantour.ssm.model.ScreenCondition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/8.
 * 实现和自定义策略相关的数据层功能
 */
@Repository
public interface StrategyMapper {

    /**
     * 获取数据库中全部的CustomizeStrategy
     * @return
     */
    public ArrayList<CustomizeStrategy> getAllCustomizeStrategy();

    /**
     * 根据策略编号获取一个customizeStrategy
     * @param strategyId
     * @return
     */
    public CustomizeStrategy selectCustomizeStrategy(String strategyId);

    /**
     * 添加一条自定义策略
     * @param customizeStrategy
     * @return
     */
    public int insertCustomizeStrategy(CustomizeStrategy customizeStrategy);

    /**
     * 删除一条自定义策略
     * @param strategyId
     * @return
     */
    public int deleteCustomizeStrategy(String strategyId);

    /**
     * 获取数据库全部的ScreenCondition 把每条条件取出来 在数据层做处理
     * @return
     */
    public ArrayList<ScreenCondition> getAllScreenCondition();

    /**
     * 根据策略编号获取这个策略对应的筛选条件
     * @param strategyId
     * @return
     */
    public ArrayList<ScreenCondition> selectStrategyAllCondition(String strategyId);

    /**
     * 添加一个策略全部的筛选条件
     * @param screenConditionArrayList
     * @return
     */
    public int insertStrategyrAllCondition(ArrayList<ScreenCondition> screenConditionArrayList);

    /**
     * 删除一个策略对应的全部的筛选条件
     * @param strategyId
     * @return
     */
    public int deleteStrategyAllCondition(String strategyId);




}
