package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/3.
 * 盈利能力
 */
public class ProfitAbility {
    private String code;
    private String name;
    private Double roe;
    private Double net_profit_ratio;
    private Double gross_profit_rate;
    private Double net_profits;
    private Double esp;
    private Double business_income;
    private Double bips;

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

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }

    public Double getNet_profit_ratio() {
        return net_profit_ratio;
    }

    public void setNet_profit_ratio(Double net_profit_ratio) {
        this.net_profit_ratio = net_profit_ratio;
    }

    public Double getGross_profit_rate() {
        return gross_profit_rate;
    }

    public void setGross_profit_rate(Double gross_profit_rate) {
        this.gross_profit_rate = gross_profit_rate;
    }

    public Double getNet_profits() {
        return net_profits;
    }

    public void setNet_profits(Double net_profits) {
        this.net_profits = net_profits;
    }

    public Double getEsp() {
        return esp;
    }

    public void setEsp(Double esp) {
        this.esp = esp;
    }

    public Double getBusiness_income() {
        return business_income;
    }

    public void setBusiness_income(Double business_income) {
        this.business_income = business_income;
    }

    public Double getBips() {
        return bips;
    }

    public void setBips(Double bips) {
        this.bips = bips;
    }
}
