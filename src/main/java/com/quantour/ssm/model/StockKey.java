package com.quantour.ssm.model;

public class StockKey {
    private String stockCode;

    private String stockDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate == null ? null : stockDate.trim();
    }
}