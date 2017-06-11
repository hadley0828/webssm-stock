package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/11.
 * 机构交易
 */
public class InstitutionTrade {

    private String stockCode;
    private String stockName;
    private Double bamount;
    private int bcount;
    private Double samount;
    private int scount;
    private Double net;

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

    public Double getBamount() {
        return bamount;
    }

    public void setBamount(Double bamount) {
        this.bamount = bamount;
    }

    public int getBcount() {
        return bcount;
    }

    public void setBcount(int bcount) {
        this.bcount = bcount;
    }

    public Double getSamount() {
        return samount;
    }

    public void setSamount(Double samount) {
        this.samount = samount;
    }

    public int getScount() {
        return scount;
    }

    public void setScount(int scount) {
        this.scount = scount;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }
}
