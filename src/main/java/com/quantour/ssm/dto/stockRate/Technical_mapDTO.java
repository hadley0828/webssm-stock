package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/12.
 */
public class Technical_mapDTO {
    String date;                //日期
    double blockChangePercent;  //所属大盘的涨跌幅
    double stockChangePercent;  //该股票的涨跌幅

    public Technical_mapDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Technical_mapDTO{" +
                "date='" + date + '\'' +
                ", blockChangePercent=" + blockChangePercent +
                ", stockChangePercent=" + stockChangePercent +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBlockChangePercent() {
        return blockChangePercent;
    }

    public void setBlockChangePercent(double blockChangePercent) {
        this.blockChangePercent = blockChangePercent;
    }

    public double getStockChangePercent() {
        return stockChangePercent;
    }

    public void setStockChangePercent(double stockChangePercent) {
        this.stockChangePercent = stockChangePercent;
    }






}
