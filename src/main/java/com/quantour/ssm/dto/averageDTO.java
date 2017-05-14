package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class averageDTO {
    String code;
    String date;
    double oneAveragePrice;

    @Override
    public String toString() {
        return "averageDTO{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", oneAveragePrice=" + oneAveragePrice +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOneAveragePrice() {
        return oneAveragePrice;
    }

    public void setOneAveragePrice(double oneAveragePrice) {
        this.oneAveragePrice = oneAveragePrice;
    }

    public averageDTO(){
        super();
    }
}
