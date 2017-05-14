package com.quantour.ssm.dto;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class compareDTO {
    String bDate;   //开始日期
    String lDate;   //结束日期
    String name; //股票名字
    double lowestPrice;   //这段时间的最低价
    double highestPrice;  //这段时间的最高价
    double upOrDown;    //这段时间的涨跌幅
    ArrayList<Double> closePriceList;   //每天的收盘价
    ArrayList<Double> logYieldList;     //每天的对数收益率
    ArrayList<String> dateList; //这段时间的有效日期
    double logVariance; //这段时间的对数收益方差


    public compareDTO(){
        super();
    }

    @Override
    public String toString() {
        return "compareDTO{" +
                "bDate='" + bDate + '\'' +
                ", lDate='" + lDate + '\'' +
                ", name='" + name + '\'' +
                ", lowestPrice=" + lowestPrice +
                ", highestPrice=" + highestPrice +
                ", upOrDown=" + upOrDown +
                ", closePriceList=" + closePriceList +
                ", logYieldList=" + logYieldList +
                ", dateList=" + dateList +
                ", logVariance=" + logVariance +
                '}';
    }

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String getlDate() {
        return lDate;
    }

    public void setlDate(String lDate) {
        this.lDate = lDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(double upOrDown) {
        this.upOrDown = upOrDown;
    }

    public ArrayList<Double> getClosePriceList() {
        return closePriceList;
    }

    public void setClosePriceList(ArrayList<Double> closePriceList) {
        this.closePriceList = closePriceList;
    }

    public ArrayList<Double> getLogYieldList() {
        return logYieldList;
    }

    public void setLogYieldList(ArrayList<Double> logYieldList) {
        this.logYieldList = logYieldList;
    }

    public ArrayList<String> getDateList() {
        return dateList;
    }

    public void setDateList(ArrayList<String> dateList) {
        this.dateList = dateList;
    }

    public double getLogVariance() {
        return logVariance;
    }

    public void setLogVariance(double logVariance) {
        this.logVariance = logVariance;
    }
}
