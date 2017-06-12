package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/6/10.
 */
public class RankDTO {
    String stockCode;
    String stockName;
    String changePercent;
    double newestPrice;

    public RankDTO(){
        super();
    }

    @Override
    public String toString() {
        return "RankDTO{" +
                "stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", changePercent='" + changePercent + '\'' +
                ", newestPrice=" + newestPrice +
                '}';
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public double getNewestPrice() {
        return newestPrice;
    }

    public void setNewestPrice(double newestPrice) {
        this.newestPrice = newestPrice;
    }







}
