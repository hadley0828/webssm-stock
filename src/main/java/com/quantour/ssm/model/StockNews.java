package com.quantour.ssm.model;

import java.sql.Date;

/**
 * Created by zhangzy on 2017/6/5.
 * 获取所有股票五月一号以来的消息
 */
public class StockNews {
    private String stockCode;
    private String title;   //信息标题
    private String type;    //信息类型
    private Date date;  //公告日期
    private String url; //信息内容url

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
