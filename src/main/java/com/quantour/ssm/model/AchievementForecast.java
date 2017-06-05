package com.quantour.ssm.model;

import java.sql.Date;

/**
 * Created by zhangzy on 2017/6/5.
 * 业绩预测 获得2017年第一季度的数据
 */
public class AchievementForecast {
    private String code;
    private String name;
    private String type;
    private Date report_date;
    private String pre_eps;
    private String range;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getPre_eps() {
        return pre_eps;
    }

    public void setPre_eps(String pre_eps) {
        this.pre_eps = pre_eps;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
