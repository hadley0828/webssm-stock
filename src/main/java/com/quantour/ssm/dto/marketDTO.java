package com.quantour.ssm.dto;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class marketDTO {

    String name;
    long Volume;   //当天成交量 long类型
    String date;   //日期

    int limitup;	//涨停股票数;
    int limitdown;	//跌停股票数;

    int upfive;		//涨幅超过5%的股票数;
    int downfive;	//跌幅超过5%的股票数;

    int upnum;		//开盘-收盘小于-5%*上一个交易日收盘价的股票个数;
    int downnum;	//开盘-收盘大于5%*上一个交易日收盘价的股票个数;

    int riseStockNumber;    //上涨的股票个数
    int declineStockNumber; //下跌的股票个数

    ArrayList<Integer> changePercentNumberList; //10-8-6-4-2-0-2-4-6-8-10

    @Override
    public String toString() {
        return "marketDTO{" +
                "name='" + name + '\'' +
                ", Volume=" + Volume +
                ", date='" + date + '\'' +
                ", limitup=" + limitup +
                ", limitdown=" + limitdown +
                ", upfive=" + upfive +
                ", downfive=" + downfive +
                ", upnum=" + upnum +
                ", downnum=" + downnum +
                ", riseStockNumber=" + riseStockNumber +
                ", declineStockNumber=" + declineStockNumber +
                ", changePercentNumberList=" + changePercentNumberList +
                '}';
    }

    public marketDTO(){

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVolume() {
        return Volume;
    }

    public void setVolume(long volume) {
        Volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLimitup() {
        return limitup;
    }

    public void setLimitup(int limitup) {
        this.limitup = limitup;
    }

    public int getLimitdown() {
        return limitdown;
    }

    public void setLimitdown(int limitdown) {
        this.limitdown = limitdown;
    }

    public int getUpfive() {
        return upfive;
    }

    public void setUpfive(int upfive) {
        this.upfive = upfive;
    }

    public int getDownfive() {
        return downfive;
    }

    public void setDownfive(int downfive) {
        this.downfive = downfive;
    }

    public int getUpnum() {
        return upnum;
    }

    public void setUpnum(int upnum) {
        this.upnum = upnum;
    }

    public int getDownnum() {
        return downnum;
    }

    public void setDownnum(int downnum) {
        this.downnum = downnum;
    }

    public int getRiseStockNumber() {
        return riseStockNumber;
    }

    public void setRiseStockNumber(int riseStockNumber) {
        this.riseStockNumber = riseStockNumber;
    }

    public int getDeclineStockNumber() {
        return declineStockNumber;
    }

    public void setDeclineStockNumber(int declineStockNumber) {
        this.declineStockNumber = declineStockNumber;
    }

    public ArrayList<Integer> getChangePercentNumberList() {
        return changePercentNumberList;
    }

    public void setChangePercentNumberList(ArrayList<Integer> changePercentNumberList) {
        this.changePercentNumberList = changePercentNumberList;
    }

}
