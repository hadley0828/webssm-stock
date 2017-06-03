package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/3.
 */
public class CashFlow {
    private String code;
    private String name;
    private Double cf_sales;
    private Double rateofreturn;
    private Double cf_nm;
    private Double cf_liabilities;
    private Double cashflowratio;

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

    public Double getCf_sales() {
        return cf_sales;
    }

    public void setCf_sales(Double cf_sales) {
        this.cf_sales = cf_sales;
    }

    public Double getRateofreturn() {
        return rateofreturn;
    }

    public void setRateofreturn(Double rateofreturn) {
        this.rateofreturn = rateofreturn;
    }

    public Double getCf_nm() {
        return cf_nm;
    }

    public void setCf_nm(Double cf_nm) {
        this.cf_nm = cf_nm;
    }

    public Double getCf_liabilities() {
        return cf_liabilities;
    }

    public void setCf_liabilities(Double cf_liabilities) {
        this.cf_liabilities = cf_liabilities;
    }

    public Double getCashflowratio() {
        return cashflowratio;
    }

    public void setCashflowratio(Double cashflowratio) {
        this.cashflowratio = cashflowratio;
    }
}
