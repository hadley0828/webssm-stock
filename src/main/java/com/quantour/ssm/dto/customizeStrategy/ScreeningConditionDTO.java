package com.quantour.ssm.dto.customizeStrategy;

/**
 * Created by zhangzy on 2017/6/6.
 * 选股指标中的筛选条件
 */
public class ScreeningConditionDTO {
    String conditionName;       //开盘价 收盘价 最高价 最低价 前日收盘价
                                //当日成交量 5日平均成交量 20日平均成交量
                                //1日涨幅 5日涨幅 20日涨幅   上市天数 交易天数
    String compareSymbol;       //大于 小于 等于 区间 排名最大 排名最小 排名区间
                                //排名%最大 排名%最小 排名%区间
    String scope;               //全部 行业内   只有六个排名需要显示
    double firstValue;
    double secondValue;         //第二个输入的值 只有三个区间需要显示

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

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return "ScreeningConditionDTO{" +
                "conditionName='" + conditionName + '\'' +
                ", compareSymbol='" + compareSymbol + '\'' +
                ", scope='" + scope + '\'' +
                ", firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }

    public ScreeningConditionDTO(){
        super();
    }



}
