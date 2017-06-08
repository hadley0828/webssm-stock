package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/8.
 * 自选股票功能中的用户-股票编号
 */
public class User_Stock {
    private String user_id;
    private String code_id;

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
}
