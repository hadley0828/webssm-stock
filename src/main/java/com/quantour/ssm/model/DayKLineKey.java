package com.quantour.ssm.model;

import java.sql.Date;

public class DayKLineKey {
    private String stockCode;

    private Date stockDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }
}