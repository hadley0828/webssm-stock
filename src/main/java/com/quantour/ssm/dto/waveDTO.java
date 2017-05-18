package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class waveDTO implements Comparable<Object>{
    String stockCode;   //股票编号
    double changePercent;  //股票的涨跌幅

    @Override
    public String toString() {
        return "waveDTO{" +
                "stockCode='" + stockCode + '\'' +
                ", changePercent=" + changePercent +
                '}';
    }

    public waveDTO(){
        super();
    }

    public waveDTO(String code,double changePercent){
        super();
        this.stockCode=code;
        this.changePercent=changePercent;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }


    @Override
    public int compareTo(Object o) {

        if(this ==o){
            return 0;
        }
        else if (o!=null && o instanceof waveDTO) {
            waveDTO vo = (waveDTO) o;
            if(changePercent >=vo.getChangePercent()){
                return -1;
            }else{
                return 1;
            }
        }else{
            return -1;
        }
    }
}
