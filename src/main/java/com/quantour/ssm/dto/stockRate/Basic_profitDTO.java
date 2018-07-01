package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/12.
 * 盈利能力
 */
public class Basic_profitDTO {
    String code;    //代码
    String name;    //名称
    String roe;     //净资产收益率
    String netProfitRatio;  //净利率
    String grossProfitRate; //毛利率
    String netProfits;      //净利润
    String esp;     //每股收益
    String bussinessIncome; //营业收入
    String bips;    //每股主营业务收入

    public Basic_profitDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Basic_profitDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", roe='" + roe + '\'' +
                ", netProfitRatio='" + netProfitRatio + '\'' +
                ", grossProfitRate='" + grossProfitRate + '\'' +
                ", netProfits='" + netProfits + '\'' +
                ", esp='" + esp + '\'' +
                ", bussinessIncome='" + bussinessIncome + '\'' +
                ", bips='" + bips + '\'' +
                '}';
    }

    //对暂无数据的处理
    public void lackData(){
        if (this.code.equals("--") || this.code.equals("nan"))
            this.code = "暂无数据";
        if (this.name.equals("--") || this.name.equals("nan"))
            this.name = "暂无数据";
        if (this.roe.equals("--") || this.roe.equals("nan"))
            this.roe = "暂无数据";
        if (this.netProfitRatio.equals("--") || this.netProfitRatio.equals("nan"))
            this.netProfitRatio = "暂无数据";
        if (this.grossProfitRate.equals("--") || this.grossProfitRate.equals("nan"))
            this.grossProfitRate = "暂无数据";
        if (this.netProfits.equals("--") || this.netProfits.equals("nan"))
            this.netProfits = "暂无数据";
        if (this.esp.equals("--") || this.esp.equals("nan"))
            this.esp = "暂无数据";
        if (this.bussinessIncome.equals("--") || this.bussinessIncome.equals("nan"))
            this.bussinessIncome = "暂无数据";
        if (this.bips.equals("--") || this.bips.equals("nan"))
            this.bips = "暂无数据";
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

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getNetProfitRatio() {
        return netProfitRatio;
    }

    public void setNetProfitRatio(String netProfitRatio) {
        this.netProfitRatio = netProfitRatio;
    }

    public String getGrossProfitRate() {
        return grossProfitRate;
    }

    public void setGrossProfitRate(String grossProfitRate) {
        this.grossProfitRate = grossProfitRate;
    }

    public String getNetProfits() {
        return netProfits;
    }

    public void setNetProfits(String newProfits) {
        this.netProfits = newProfits;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getBussinessIncome() {
        return bussinessIncome;
    }

    public void setBussinessIncome(String bussinessIncome) {
        this.bussinessIncome = bussinessIncome;
    }

    public String getBips() {
        return bips;
    }

    public void setBips(String bips) {
        this.bips = bips;
    }



}
