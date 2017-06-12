package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/6/13.
 * 预测明天的股票信息
 */
public class NextDateStockDTO {
    String stockCode;
    String date;
    double RisingBreakthroughPrice; //上升突破价位
    double RisingResistancePrice;   //上升阻力价位
    double declineSupportPrice;     //下跌支撑价位
    double declineReversePrice;     //下跌反转点价位
    double targetPrice;             //心理价位
    double closePrice;              //收盘价位

    public NextDateStockDTO(){
        super();
    }

    @Override
    public String toString() {
        return "NextDateStockDTO{" +
                "stockCode='" + stockCode + '\'' +
                ", date='" + date + '\'' +
                ", RisingBreakthroughPrice=" + RisingBreakthroughPrice +
                ", RisingResistancePrice=" + RisingResistancePrice +
                ", declineSupportPrice=" + declineSupportPrice +
                ", declineReversePrice=" + declineReversePrice +
                ", targetPrice=" + targetPrice +
                ", closePrice=" + closePrice +
                '}';
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRisingBreakthroughPrice() {
        return RisingBreakthroughPrice;
    }

    public void setRisingBreakthroughPrice(double risingBreakthroughPrice) {
        RisingBreakthroughPrice = risingBreakthroughPrice;
    }

    public double getRisingResistancePrice() {
        return RisingResistancePrice;
    }

    public void setRisingResistancePrice(double risingResistancePrice) {
        RisingResistancePrice = risingResistancePrice;
    }

    public double getDeclineSupportPrice() {
        return declineSupportPrice;
    }

    public void setDeclineSupportPrice(double declineSupportPrice) {
        this.declineSupportPrice = declineSupportPrice;
    }

    public double getDeclineReversePrice() {
        return declineReversePrice;
    }

    public void setDeclineReversePrice(double declineReversePrice) {
        this.declineReversePrice = declineReversePrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }




}
