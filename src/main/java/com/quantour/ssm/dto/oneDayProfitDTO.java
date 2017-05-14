package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class oneDayProfitDTO {
    double straProfit;  //策略下的累计收益率
    double standardProfit; //基准的累计收益率
    String date;  //当天的日期

    @Override
    public String toString() {
        return "oneDayProfitDTO{" +
                "straProfit=" + straProfit +
                ", standardProfit=" + standardProfit +
                ", date='" + date + '\'' +
                '}';
    }

    public double getStraProfit() {
        return straProfit;
    }

    public void setStraProfit(double straProfit) {
        this.straProfit = straProfit;
    }

    public double getStandardProfit() {
        return standardProfit;
    }

    public void setStandardProfit(double standardProfit) {
        this.standardProfit = standardProfit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public oneDayProfitDTO(){
        super();
    }

}
