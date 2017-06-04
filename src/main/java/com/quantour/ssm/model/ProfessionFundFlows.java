package com.quantour.ssm.model;

import java.sql.Date;

/**
 * Created by zhangzy on 2017/6/3.
 * 行业资金流向
 */
public class ProfessionFundFlows {


    private String industry;

    private Date date;

    private String inflow;

    private Double change_percent;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInflow() {
        return inflow;
    }

    public void setInflow(String inflow) {
        this.inflow = inflow;
    }


    public Double getChange_percent() {
        return change_percent;
    }

    public void setChange_percent(Double change_percent) {
        this.change_percent = change_percent;
    }


}
