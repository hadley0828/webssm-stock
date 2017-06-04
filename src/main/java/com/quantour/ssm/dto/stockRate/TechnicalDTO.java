package com.quantour.ssm.dto.stockRate;

import com.quantour.ssm.dto.klineDTO;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/4.
 * 技术面诊股
 */
public class TechnicalDTO {
    double technicalScore;  //技术面的分数
    double partScore;   //根据下面的指标算出来的分数用来排名
    double defeatPercent;   //击败了多少百分比的股票
    //下面是一个平行于x轴的直线加上一段k线图
    double averageCode; //平均成本
    ArrayList<klineDTO> klineDTOArrayList;  //近60天的k线图信息
    //下面是两条折线图
    ArrayList<Double> lastTenDaysStockChange;  //过去十个交易日的涨跌幅
    ArrayList<Double> lastTenDaysMarketChange; //过去十个交易日所属大盘的涨跌幅
    double pressurePrice;   //压力位价格
    double supportPrice;    //支撑位价格

    @Override
    public String toString() {
        return "TechnicalDTO{" +
                "technicalScore=" + technicalScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", averageCode=" + averageCode +
                ", klineDTOArrayList=" + klineDTOArrayList +
                ", lastTenDaysStockChange=" + lastTenDaysStockChange +
                ", lastTenDaysMarketChange=" + lastTenDaysMarketChange +
                ", pressurePrice=" + pressurePrice +
                ", supportPrice=" + supportPrice +
                '}';
    }

    public double getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(double technicalScore) {
        this.technicalScore = technicalScore;
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

    public double getAverageCode() {
        return averageCode;
    }

    public void setAverageCode(double averageCode) {
        this.averageCode = averageCode;
    }

    public ArrayList<klineDTO> getKlineDTOArrayList() {
        return klineDTOArrayList;
    }

    public void setKlineDTOArrayList(ArrayList<klineDTO> klineDTOArrayList) {
        this.klineDTOArrayList = klineDTOArrayList;
    }

    public ArrayList<Double> getLastTenDaysStockChange() {
        return lastTenDaysStockChange;
    }

    public void setLastTenDaysStockChange(ArrayList<Double> lastTenDaysStockChange) {
        this.lastTenDaysStockChange = lastTenDaysStockChange;
    }

    public ArrayList<Double> getLastTenDaysMarketChange() {
        return lastTenDaysMarketChange;
    }

    public void setLastTenDaysMarketChange(ArrayList<Double> lastTenDaysMarketChange) {
        this.lastTenDaysMarketChange = lastTenDaysMarketChange;
    }

    public double getPressurePrice() {
        return pressurePrice;
    }

    public void setPressurePrice(double pressurePrice) {
        this.pressurePrice = pressurePrice;
    }

    public double getSupportPrice() {
        return supportPrice;
    }

    public void setSupportPrice(double supportPrice) {
        this.supportPrice = supportPrice;
    }

    public TechnicalDTO(){
        super();
    }



}
