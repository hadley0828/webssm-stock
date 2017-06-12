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

    //K线图   市场表现    成交量
    //下面是一个k线图
    ArrayList<klineDTO> klineDTOArrayList;  //近60天的k线图信息

    //下面是市场表现   近十个交易日的涨跌幅  近十个交易日所属大盘的涨跌幅
    ArrayList<Technical_mapDTO> technicalMapDTOArrayList;

    double oneDayVolume;    //今日的成交量
    double fiveDayVolume;   //近五日的平均成交量
    double tenDayVolume;    //近十日的平均成交量

    public TechnicalDTO(){
        super();
    }

    @Override
    public String toString() {
        return "TechnicalDTO{" +
                "technicalScore=" + technicalScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", klineDTOArrayList=" + klineDTOArrayList +
                ", technicalMapDTOArrayList=" + technicalMapDTOArrayList +
                ", oneDayVolume=" + oneDayVolume +
                ", fiveDayVolume=" + fiveDayVolume +
                ", tenDayVolume=" + tenDayVolume +
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

    public ArrayList<klineDTO> getKlineDTOArrayList() {
        return klineDTOArrayList;
    }

    public void setKlineDTOArrayList(ArrayList<klineDTO> klineDTOArrayList) {
        this.klineDTOArrayList = klineDTOArrayList;
    }

    public ArrayList<Technical_mapDTO> getTechnicalMapDTOArrayList() {
        return technicalMapDTOArrayList;
    }

    public void setTechnicalMapDTOArrayList(ArrayList<Technical_mapDTO> technicalMapDTOArrayList) {
        this.technicalMapDTOArrayList = technicalMapDTOArrayList;
    }

    public double getOneDayVolume() {
        return oneDayVolume;
    }

    public void setOneDayVolume(double oneDayVolume) {
        this.oneDayVolume = oneDayVolume;
    }

    public double getFiveDayVolume() {
        return fiveDayVolume;
    }

    public void setFiveDayVolume(double fiveDayVolume) {
        this.fiveDayVolume = fiveDayVolume;
    }

    public double getTenDayVolume() {
        return tenDayVolume;
    }

    public void setTenDayVolume(double tenDayVolume) {
        this.tenDayVolume = tenDayVolume;
    }








}
