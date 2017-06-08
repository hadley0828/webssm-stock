package com.quantour.ssm.service;

import com.quantour.ssm.dto.stockRate.GeneralScoreDTO;

/**
 * 该接口用来处理和股票评级相关的方法
 * Created by zhangzy on 2017/6/3.
 */
public interface RateService {

    /**
     * 根据股票编号获得该股票当天的评级信息
     * @param code
     * @return
     */
    public GeneralScoreDTO getOneStockGeneralScore(String code);

    //从数据库中获取当天的信息

    public void calculateOneDayStockScore(String date);


}
