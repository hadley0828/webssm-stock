package com.quantour.ssm.model;

import java.sql.Date;

/**
 * Created by zhangzy on 2017/6/3.
 * 个股资金流向 今日 五日 十日 二十日
 */
public class SingleStockFundFlows {

    private String code;

    private Date date;

    private String inflow;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    //TODO
}
