package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class limitUpAndDownNumsDTO {
    int upNumber;
    int downNumber;
    String date;

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
