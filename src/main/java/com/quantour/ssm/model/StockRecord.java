package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/8.
 * 个股信息的历史记录
 */
public class StockRecord {
    private String user_id;
    private String code_id;
    private String date_time;

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
}
