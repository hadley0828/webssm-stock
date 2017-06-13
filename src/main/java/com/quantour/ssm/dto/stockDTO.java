package com.quantour.ssm.dto;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class stockDTO {
    String id;
    String name;
    String market;
    double openPrice;
    double closePrice;
    double highPrice;
    double lowPrice;
    String uplift;		//涨跌幅;
    double adjClose;	//复权股价;

    long Volume;		//成交量;
    double logYield;	//对数收益率;
    double logVariance;	//对数收益方差;

    String stockIndustry;
    String stockArea;
    ArrayList<NewsDTO> newsDTOArrayList;

    @Override
    public String toString() {
        return "stockDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", market='" + market + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", uplift=" + uplift +
                ", adjClose=" + adjClose +
                ", Volume=" + Volume +
                ", logYield=" + logYield +
                ", logVariance=" + logVariance +
                ", stockIndustry='" + stockIndustry + '\'' +
                ", stockArea='" + stockArea + '\'' +
                ", newsDTOArrayList=" + newsDTOArrayList +
                '}';
    }

    public stockDTO(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public long getVolume() {
        return Volume;
    }

    public void setVolume(long volume) {
        Volume = volume;
    }

    public double getLogYield() {
        return logYield;
    }

    public void setLogYield(double logYield) {
        this.logYield = logYield;
    }

    public double getLogVariance() {
        return logVariance;
    }

    public void setLogVariance(double logVariance) {
        this.logVariance = logVariance;
    }

    public String getStockIndustry() {
        return stockIndustry;
    }

    public void setStockIndustry(String stockIndustry) {
        this.stockIndustry = stockIndustry;
    }

    public String getStockArea() {
        return stockArea;
    }

    public void setStockArea(String stockArea) {
        this.stockArea = stockArea;
    }

    public ArrayList<NewsDTO> getNewsDTOArrayList() {
        return newsDTOArrayList;
    }

    public void setNewsDTOArrayList(ArrayList<NewsDTO> newsDTOArrayList) {
        this.newsDTOArrayList = newsDTOArrayList;
    }


    public String getUplift() {
        return uplift;
    }

    public void setUplift(String uplift) {
        this.uplift = uplift;
    }



}
