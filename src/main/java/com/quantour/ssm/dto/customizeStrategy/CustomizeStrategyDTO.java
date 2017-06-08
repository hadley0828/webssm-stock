package com.quantour.ssm.dto.customizeStrategy;

import com.quantour.ssm.dto.strategyResultDTO;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/6.
 * 用来表示整个自定义策略
 */
public class CustomizeStrategyDTO {
    String strategyID;  //createrID+" "+createTime
    String createrID;   //创建者的用户ID
    String strategyName;    //策略名称
    String strategyExplanation; //策略说明
    String createTime;         //创建的时间
    StockPondDTO stockPondDTO;
    ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList;
    TradeModelDTO tradeModelDTO;

    @Override
    public String toString() {
        return "CustomizeStrategyDTO{" +
                "strategyID='" + strategyID + '\'' +
                ", createrID='" + createrID + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", strategyExplanation='" + strategyExplanation + '\'' +
                ", createTime='" + createTime + '\'' +
                ", stockPondDTO=" + stockPondDTO +
                ", screeningConditionDTOArrayList=" + screeningConditionDTOArrayList +
                ", tradeModelDTO=" + tradeModelDTO +
                ", resultDTO=" + resultDTO +
                '}';
    }

    strategyResultDTO resultDTO; //在保存策略的时候生成一个区间的结果 用于展示自定义策略的时候可以出现示意图

    public CustomizeStrategyDTO(){
        super();
    }



    public String getStrategyID() {
        return strategyID;
    }

    public void setStrategyID(String strategyID) {
        this.strategyID = strategyID;
    }


    public String getCreaterID() {
        return createrID;
    }

    public void setCreaterID(String createrID) {
        this.createrID = createrID;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyExplanation() {
        return strategyExplanation;
    }

    public void setStrategyExplanation(String strategyExplanation) {
        this.strategyExplanation = strategyExplanation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public StockPondDTO getStockPondDTO() {
        return stockPondDTO;
    }

    public void setStockPondDTO(StockPondDTO stockPondDTO) {
        this.stockPondDTO = stockPondDTO;
    }

    public ArrayList<ScreeningConditionDTO> getScreeningConditionDTOArrayList() {
        return screeningConditionDTOArrayList;
    }

    public void setScreeningConditionDTOArrayList(ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList) {
        this.screeningConditionDTOArrayList = screeningConditionDTOArrayList;
    }

    public TradeModelDTO getTradeModelDTO() {
        return tradeModelDTO;
    }

    public void setTradeModelDTO(TradeModelDTO tradeModelDTO) {
        this.tradeModelDTO = tradeModelDTO;
    }

    public strategyResultDTO getResultDTO() {
        return resultDTO;
    }

    public void setResultDTO(strategyResultDTO resultDTO) {
        this.resultDTO = resultDTO;
    }







}
