package com.quantour.ssm.model;

import com.quantour.ssm.dto.customizeStrategy.ScreeningConditionDTO;

/**
 * Created by zhangzy on 2017/6/9.
 * 自定义策略的筛选条件
 */
public class ScreenCondition {
    private String strategyId;
    private String conditionName;
    private String compareSymbol;
    private String scope;
    private Double firstValue;
    private Double secondValue;


    public ScreenCondition(){
        super();
    }

    public ScreenCondition(ScreeningConditionDTO scd) {
        this.strategyId=scd.getStrategyId();
        this.conditionName=scd.getConditionName();
        this.compareSymbol=scd.getCompareSymbol();
        if(scd.getScope()!=null){
            this.scope=scd.getScope();
        }
        this.firstValue=scd.getFirstValue();
        this.secondValue=scd.getSecondValue();
        //scd的六个数据必须都要有
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getCompareSymbol() {
        return compareSymbol;
    }

    public void setCompareSymbol(String compareSymbol) {
        this.compareSymbol = compareSymbol;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Double firstValue) {
        this.firstValue = firstValue;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Double secondValue) {
        this.secondValue = secondValue;
    }
}
