package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/11.
 * 业绩预测
 */
public class Message_forecastDTO {

    String code;    //代码
    String name;    //名称
    String type;    //业绩变动类型
    String report_date; //发布日期
    double pre_eps;     //上年同期每股收益
    String out_range;   //业绩变动范围

    public Message_forecastDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Message_forecastDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", report_date='" + report_date + '\'' +
                ", pre_eps=" + pre_eps +
                ", out_range='" + out_range + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public double getPre_eps() {
        return pre_eps;
    }

    public void setPre_eps(double pre_eps) {
        this.pre_eps = pre_eps;
    }

    public String getOut_range() {
        return out_range;
    }

    public void setOut_range(String out_range) {
        this.out_range = out_range;
    }


}
