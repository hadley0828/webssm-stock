package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/6/9.
 * 股票的一条新闻
 */
public class NewsDTO {
    private String code;
    private String date;
    private String type;
    private String title;
    private String url;

    public NewsDTO(){
        super();
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
