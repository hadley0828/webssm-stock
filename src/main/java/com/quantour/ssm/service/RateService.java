package com.quantour.ssm.service;

import com.quantour.ssm.dto.stockRate.*;

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
    public GeneralScoreDTO getOneStockGeneralScore(String code,String date);

    //从数据库中获取当天的信息

    public void calculateOneDayStockScore(String date);

    /**
     * 根据股票编号和日期获得当天的技术面分数
     * @param code
     * @param date
     * @return
     */
    public TechnicalDTO getOneStockTechnicalScore(String code,String date);

    /**
     * 根据股票编号和日期获得当天的资金面分数
     * @param code
     * @param date
     * @return
     */
    public CapitalDTO getOneStockCapitalScore(String code,String date);

    /**
     * 根据股票编号和日期获得当天的消息面分数
     * @param code
     * @param date
     * @return
     */
    public MessageDTO getOneStockMessageScore(String code,String date);

    /**
     * 根据股票编号和日期获得当天的行业面分数
     * @param code
     * @param date
     * @return
     */
    public IndustryDTO getOneStockIndustryScore(String code,String date);

    /**
     * 根据股票编号和日期获得当天的基本面分数
     * @param code
     * @param date
     * @return
     */
    public BasicDTO getOneStockBasicScore(String code,String date);

}
