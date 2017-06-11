package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/4.
 */
public class DateAndChange {
    String date;
    double blockChangePercent;    //大盘的涨跌幅
    double industryChangePercent; //行业的涨跌幅

    public DateAndChange(){
        super();
    }

    @Override
    public String toString() {
        return "DateAndChange{" +
                "date='" + date + '\'' +
                ", blockChangePercent=" + blockChangePercent +
                ", industryChangePercent=" + industryChangePercent +
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

    public double getIndustryChangePercent() {
        return industryChangePercent;
    }

    public void setIndustryChangePercent(double industryChangePercent) {
        this.industryChangePercent = industryChangePercent;
    }




}
