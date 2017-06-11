package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/11.
 */
public class Message_NewsDTO {
    String code;
    String date;
    String type;
    String title;
    String url;

    public Message_NewsDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Message_NewsDTO{" +
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
