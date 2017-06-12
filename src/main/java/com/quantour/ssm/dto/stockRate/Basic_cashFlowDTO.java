package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/12.
 * 现金流量
 */
public class Basic_cashFlowDTO {

    String code;    //代码
    String name;    //名称
    //String转为double的时候需要判断是不是nan
    String cfSales; //经营现金净流量对销售收入比率
    String rateOfReturn;    //资产的经营现金流量回报率
    String cfNm;    //经营现金净流量与净利润的比率
    String cfLiAbilities;   //经营现金净流量对负债比率
    String cashFlowRatio;   //现金流量比率

    public Basic_cashFlowDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Basic_cashFlowDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cfSales='" + cfSales + '\'' +
                ", rateOfReturn='" + rateOfReturn + '\'' +
                ", cfNm='" + cfNm + '\'' +
                ", cfLiAbilities='" + cfLiAbilities + '\'' +
                ", cashFlowRatio='" + cashFlowRatio + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCfSales() {
        return cfSales;
    }

    public void setCfSales(String cfSales) {
        this.cfSales = cfSales;
    }

    public String getRateOfReturn() {
        return rateOfReturn;
    }

    public void setRateOfReturn(String rateOfReturn) {
        this.rateOfReturn = rateOfReturn;
    }

    public String getCfNm() {
        return cfNm;
    }

    public void setCfNm(String cfNm) {
        this.cfNm = cfNm;
    }

    public String getCfLiAbilities() {
        return cfLiAbilities;
    }

    public void setCfLiAbilities(String cfLiAbilities) {
        this.cfLiAbilities = cfLiAbilities;
    }

    public String getCashFlowRatio() {
        return cashFlowRatio;
    }

    public void setCashFlowRatio(String cashFlowRatio) {
        this.cashFlowRatio = cashFlowRatio;
    }






}
