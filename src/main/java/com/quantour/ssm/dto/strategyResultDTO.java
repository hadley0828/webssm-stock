package com.quantour.ssm.dto;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class strategyResultDTO {
    String straId;         //该次回测使用的策略编号
    double yearProfit;     //年化收益率
    double standardProfit; //基准年化收益率
    double alpha;          //阿尔法值
    double beta;           //贝塔值
    double sharpRate;      //夏普比率
    double profitWaveRate; //收益波动率
    double infoPercent;    //信息比率
    double maxBack;        //最大回撤
    double turnoverRate;   //换手率
    double currentStandardProfit;
    double currentStraProfit;

    @Override
    public String toString() {
        return "strategyResultDTO{" +
                "straId='" + straId + '\'' +
                ", yearProfit=" + yearProfit +
                ", standardProfit=" + standardProfit +
                ", alpha=" + alpha +
                ", beta=" + beta +
                ", sharpRate=" + sharpRate +
                ", profitWaveRate=" + profitWaveRate +
                ", infoPercent=" + infoPercent +
                ", maxBack=" + maxBack +
                ", turnoverRate=" + turnoverRate +
                ", currentStandardProfit=" + currentStandardProfit +
                ", currentStraProfit=" + currentStraProfit +
                ", daysProfitList=" + daysProfitList +
                ", indexprofitvo=" + indexprofitvo +
                '}';
    }

    ArrayList<oneDayProfitDTO> daysProfitList;  //画a图的列表
    indexProfitDTO indexprofitvo;  //画c图的vo

    public String getStraId() {
        return straId;
    }

    public void setStraId(String straId) {
        this.straId = straId;
    }

    public double getYearProfit() {
        return yearProfit;
    }

    public void setYearProfit(double yearProfit) {
        this.yearProfit = yearProfit;
    }

    public double getStandardProfit() {
        return standardProfit;
    }

    public void setStandardProfit(double standardProfit) {
        this.standardProfit = standardProfit;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getSharpRate() {
        return sharpRate;
    }

    public void setSharpRate(double sharpRate) {
        this.sharpRate = sharpRate;
    }

    public double getProfitWaveRate() {
        return profitWaveRate;
    }

    public void setProfitWaveRate(double profitWaveRate) {
        this.profitWaveRate = profitWaveRate;
    }

    public double getInfoPercent() {
        return infoPercent;
    }

    public void setInfoPercent(double infoPercent) {
        this.infoPercent = infoPercent;
    }

    public double getMaxBack() {
        return maxBack;
    }

    public void setMaxBack(double maxBack) {
        this.maxBack = maxBack;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public double getCurrentStandardProfit() {
        return currentStandardProfit;
    }

    public void setCurrentStandardProfit(double currentStandardProfit) {
        this.currentStandardProfit = currentStandardProfit;
    }

    public double getCurrentStraProfit() {
        return currentStraProfit;
    }

    public void setCurrentStraProfit(double currentStraProfit) {
        this.currentStraProfit = currentStraProfit;
    }

    public ArrayList<oneDayProfitDTO> getDaysProfitList() {
        return daysProfitList;
    }

    public void setDaysProfitList(ArrayList<oneDayProfitDTO> daysProfitList) {
        this.daysProfitList = daysProfitList;
    }

    public indexProfitDTO getIndexprofitvo() {
        return indexprofitvo;
    }

    public void setIndexprofitvo(indexProfitDTO indexprofitvo) {
        this.indexprofitvo = indexprofitvo;
    }

    public strategyResultDTO(){
        super();
    }

}
