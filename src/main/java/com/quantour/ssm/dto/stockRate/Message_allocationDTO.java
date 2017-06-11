package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/11.
 * 分配预案
 */
public class Message_allocationDTO {

    String code;    //股票代码
    String name;    //股票名称
    String year;    //分配年份
    String report_date; //公布日期
    double divi;    //分红金额(每10股)
    int shares;     //转赠和送股数(每10股)

    public Message_allocationDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Message_allocationDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", report_date='" + report_date + '\'' +
                ", divi=" + divi +
                ", shares=" + shares +
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public double getDivi() {
        return divi;
    }

    public void setDivi(double divi) {
        this.divi = divi;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }


}
