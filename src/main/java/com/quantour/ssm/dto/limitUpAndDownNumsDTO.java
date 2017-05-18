package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class limitUpAndDownNumsDTO {
    int upNumber;  //上涨的支数
    int downNumber; //下跌的支数
    String date;    //当前日期

    @Override
    public String toString() {
        return "limitUpAndDownNumsDTO{" +
                "upNumber=" + upNumber +
                ", downNumber=" + downNumber +
                ", date='" + date + '\'' +
                '}';
    }

    public int getUpNumber() {
        return upNumber;
    }

    public void setUpNumber(int upNumber) {
        this.upNumber = upNumber;
    }

    public int getDownNumber() {
        return downNumber;
    }

    public void setDownNumber(int downNumber) {
        this.downNumber = downNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public limitUpAndDownNumsDTO(){
        super();
    }

}
