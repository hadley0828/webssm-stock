package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/3.
 * 现金流量
 */
public class CashFlow {
    private String code;
    private String name;
    private String cf_sales;
    private String rateofreturn;
    private String cf_nm;
    private String cf_liabilities;
    private String cashflowratio;

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

    public String getCf_sales() {
        return cf_sales;
    }

    public void setCf_sales(String cf_sales) {
        this.cf_sales = cf_sales;
    }

    public String getRateofreturn() {
        return rateofreturn;
    }

    public void setRateofreturn(String rateofreturn) {
        this.rateofreturn = rateofreturn;
    }

    public String getCf_nm() {
        return cf_nm;
    }

    public void setCf_nm(String cf_nm) {
        this.cf_nm = cf_nm;
    }

    public String getCf_liabilities() {
        return cf_liabilities;
    }

    public void setCf_liabilities(String cf_liabilities) {
        this.cf_liabilities = cf_liabilities;
    }

    public String getCashflowratio() {
        return cashflowratio;
    }

    public void setCashflowratio(String cashflowratio) {
        this.cashflowratio = cashflowratio;
    }
}
