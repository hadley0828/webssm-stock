package com.quantour.ssm.dto.stockRate;

import java.util.List;

/**
 * Created by zhangzy on 2017/6/4.
 * 行业面诊股
 */
public class IndustryDTO {
    double industryScore;   //行业面得分
    double partScore;   //根据下面的指标获得的分数用来排名
    double defeatPercent;   //击败了多少百分比的股票
    String tenDaysIndustryChange;   //近十天该行业的涨跌幅
    String tenDaysMarketChange;     //近十天大盘的涨跌幅
    //下面画一张两条折线图
    String ChangeList;   //近十个交易日每天市场的涨跌幅

    @Override
    public String toString() {
        return "IndustryDTO{" +
                "industryScore=" + industryScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", tenDaysIndustryChange=" + tenDaysIndustryChange +
                ", tenDaysMarketChange=" + tenDaysMarketChange +
                ", ChangeList=" + ChangeList +
                '}';
    }

    public double getIndustryScore() {
        return industryScore;
    }

    public void setIndustryScore(double industryScore) {
        this.industryScore = industryScore;
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


    public String getTenDaysIndustryChange() {
        return tenDaysIndustryChange;
    }

    public void setTenDaysIndustryChange(String tenDaysIndustryChange) {
        this.tenDaysIndustryChange = tenDaysIndustryChange;
    }

    public String getTenDaysMarketChange() {
        return tenDaysMarketChange;
    }

    public void setTenDaysMarketChange(String tenDaysMarketChange) {
        this.tenDaysMarketChange = tenDaysMarketChange;
    }



    public String getChangeList() {
        return ChangeList;
    }

    public void setChangeList(String changeList) {
        ChangeList = changeList;
    }




    public IndustryDTO(){
        super();
    }
}
