package com.quantour.ssm.dto.UserHistory;

import com.quantour.ssm.model.StockRecord;

/**
 * Created by zhangzy on 2017/6/8.
 */
public class StockRecordDTO {

    private String user_id;
    private String code_id;
    private String date_time;   //不需要显示
    private String stock_name;
    private String market;
    private double openPrice;
    private double closePrice;
    private double highPrice;
    private double lowPrice;
    private long volume;
    private double logYield;

    public StockRecordDTO(){
        super();
    }

    public StockRecordDTO(StockRecord stockRecord){
        this.user_id=stockRecord.getUser_id();
        this.code_id=stockRecord.getCode_id();
        this.date_time=stockRecord.getDate_time();
    }

    @Override
    public String toString() {
        return "StockRecordDTO{" +
                "user_id='" + user_id + '\'' +
                ", code_id='" + code_id + '\'' +
                ", date_time='" + date_time + '\'' +
                ", stock_name='" + stock_name + '\'' +
                ", market='" + market + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", volume=" + volume +
                ", logYield=" + logYield +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCode_id() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
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

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getLogYield() {
        return logYield;
    }

    public void setLogYield(double logYield) {
        this.logYield = logYield;
    }

}
