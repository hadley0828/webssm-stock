package com.quantour.ssm.dao;

import com.quantour.ssm.model.StrategyInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/8.
 * 实现和自定义策略相关的数据层功能
 */
@Repository
public interface StrategyMapper {

    /**
     * 获得全部的自定义策略
     * @return
     */
    public ArrayList<StrategyInfo> getAllCustomizeStrategy();

    public ArrayList<StrategyInfo> getUserAllStrategy(String userid);

    //TODO


}
