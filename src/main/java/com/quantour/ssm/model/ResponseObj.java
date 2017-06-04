package com.quantour.ssm.model;

import com.quantour.ssm.dto.userDTO;

/**
 * Created by loohaze on 2017/6/2.
 */
public class ResponseObj {

    private String code;

    private String ms;

    private userDTO data;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return ms;
    }

    public void setMessage(String message) {
        this.ms = message;
    }

    public userDTO getData() {
        return data;
    }

    public void setData(userDTO data) {

        this.data = data;
    }
}
