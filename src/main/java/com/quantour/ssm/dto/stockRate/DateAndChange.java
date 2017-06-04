package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/4.
 */
public class DateAndChange {
    String date;
    double changePercnet;

    @Override
    public String toString() {
        return "DateAndChange{" +
                "date='" + date + '\'' +
                ", changePercnet=" + changePercnet +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getChangePercnet() {
        return changePercnet;
    }

    public void setChangePercnet(double changePercnet) {
        this.changePercnet = changePercnet;
    }

    public DateAndChange(){
        super();
    }


}
