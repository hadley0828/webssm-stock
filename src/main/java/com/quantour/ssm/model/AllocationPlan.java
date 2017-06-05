package com.quantour.ssm.model;

import java.sql.Date;

/**
 * Created by zhangzy on 2017/6/5.
 * 分配预案 取2017年或者2016年的数据 top越大越好
 */
public class AllocationPlan {
    private String code;
    private String name;
    private String year;
    private Date report_name;
    private String divi;
    private String shares;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getReport_name() {
        return report_name;
    }

    public void setReport_name(Date report_name) {
        this.report_name = report_name;
    }

    public String getDivi() {
        return divi;
    }

    public void setDivi(String divi) {
        this.divi = divi;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }
}
