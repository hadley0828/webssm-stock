package com.quantour.ssm.dto.stockRate;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/4.
 * 资金面诊股
 */
public class CapitalDTO {
    double capitalScore;    //资金面分数
    double partScore;   //根据指标得到的分数 用来排名
    double defeatPercent;   //击败的多少百分比的股票

    String flowMapList;  //绘制资金流向图的数据

    double todayStockFlow;  //今日股票流向
    double fiveStockFlow;   //五日股票流向
    double tenStockFlow;    //十日股票流向
    double twentyStockFlow; //二十日股票流向

    double todayIndustryFlow;   //今日行业流向
    double fiveIndustryFlow;    //五日行业流向
    double tenIndustryFlow;     //十日行业流向
    double twentyIndustryFlow;  //二十日行业流向

    String stockCode;   //股票编号
    String stockName;   //股票名称
    double bAmount;     //累计购买额
    int bCount;         //买入席位数
    double sAmount;     //累计卖出额
    int sCount;         //卖出席位数
    double net;         //净额(万)

    public double getCapitalScore() {
        return capitalScore;
    }

    @Override
    public String toString() {
        return "CapitalDTO{" +
                "capitalScore=" + capitalScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", flowMapList=" + flowMapList +
                ", todayStockFlow=" + todayStockFlow +
                ", fiveStockFlow=" + fiveStockFlow +
                ", tenStockFlow=" + tenStockFlow +
                ", twentyStockFlow=" + twentyStockFlow +
                ", todayIndustryFlow=" + todayIndustryFlow +
                ", fiveIndustryFlow=" + fiveIndustryFlow +
                ", tenIndustryFlow=" + tenIndustryFlow +
                ", twentyIndustryFlow=" + twentyIndustryFlow +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", bAmount=" + bAmount +
                ", bCount=" + bCount +
                ", sAmount=" + sAmount +
                ", sCount=" + sCount +
                ", net=" + net +
                '}';
    }


    public void setCapitalScore(double capitalScore) {
        this.capitalScore = capitalScore;
    }

    public double getPartScore() {
        return partScore;
    }

    public void setPartScore(double partScore) {
        this.partScore = partScore;
    }

    public double getDefeatPercent() {
        return defeatPercent;
    }

    public void setDefeatPercent(double defeatPercent) {
        this.defeatPercent = defeatPercent;
    }


    public double getTodayStockFlow() {
        return todayStockFlow;
    }

    public void setTodayStockFlow(double todayStockFlow) {
        this.todayStockFlow = todayStockFlow;
    }

    public double getFiveStockFlow() {
        return fiveStockFlow;
    }

    public void setFiveStockFlow(double fiveStockFlow) {
        this.fiveStockFlow = fiveStockFlow;
    }

    public double getTenStockFlow() {
        return tenStockFlow;
    }

    public void setTenStockFlow(double tenStockFlow) {
        this.tenStockFlow = tenStockFlow;
    }

    public double getTwentyStockFlow() {
        return twentyStockFlow;
    }

    public void setTwentyStockFlow(double twentyStockFlow) {
        this.twentyStockFlow = twentyStockFlow;
    }

    public double getTodayIndustryFlow() {
        return todayIndustryFlow;
    }

    public void setTodayIndustryFlow(double todayIndustryFlow) {
        this.todayIndustryFlow = todayIndustryFlow;
    }

    public double getFiveIndustryFlow() {
        return fiveIndustryFlow;
    }

    public void setFiveIndustryFlow(double fiveIndustryFlow) {
        this.fiveIndustryFlow = fiveIndustryFlow;
    }

    public double getTenIndustryFlow() {
        return tenIndustryFlow;
    }

    public void setTenIndustryFlow(double tenIndustryFlow) {
        this.tenIndustryFlow = tenIndustryFlow;
    }

    public double getTwentyIndustryFlow() {
        return twentyIndustryFlow;
    }

    public void setTwentyIndustryFlow(double twentyIndustryFlow) {
        this.twentyIndustryFlow = twentyIndustryFlow;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getbAmount() {
        return bAmount;
    }

    public void setbAmount(double bAmount) {
        this.bAmount = bAmount;
    }

    public int getbCount() {
        return bCount;
    }

    public void setbCount(int bCount) {
        this.bCount = bCount;
    }

    public double getsAmount() {
        return sAmount;
    }

    public void setsAmount(double sAmount) {
        this.sAmount = sAmount;
    }

    public int getsCount() {
        return sCount;
    }

    public void setsCount(int sCount) {
        this.sCount = sCount;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    public CapitalDTO(){
        super();
    }


    public String getFlowMapList() {
        return flowMapList;
    }

    public void setFlowMapList(String flowMapList) {
        this.flowMapList = flowMapList;
    }







}
