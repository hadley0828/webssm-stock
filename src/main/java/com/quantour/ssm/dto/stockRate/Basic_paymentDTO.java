package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/12.
 * 偿债能力
 */
public class Basic_paymentDTO {

    String code;
    String name;
    String currentRatio;
    String quickRatio;
    String cashRatio;
    String icRatio;
    String sheqRatio;
    String adRatio;


    public Basic_paymentDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Basic_paymentDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", currentRatio='" + currentRatio + '\'' +
                ", quickRatio='" + quickRatio + '\'' +
                ", cashRatio='" + cashRatio + '\'' +
                ", icRatio='" + icRatio + '\'' +
                ", sheqRatio='" + sheqRatio + '\'' +
                ", adRatio='" + adRatio + '\'' +
                '}';
    }

    //对暂无数据的处理
    public void lackData(){
        if (this.code.equals("--") || this.code.equals("nan"))
            this.code = "暂无数据";
        if (this.name.equals("--") || this.name.equals("nan"))
            this.name = "暂无数据";
        if (this.currentRatio.equals("--") || this.currentRatio.equals("nan"))
            this.currentRatio = "暂无数据";
        if (this.quickRatio.equals("--") || this.quickRatio.equals("nan"))
            this.quickRatio = "暂无数据";
        if (this.cashRatio.equals("--") || this.cashRatio.equals("nan"))
            this.cashRatio = "暂无数据";
        if (this.icRatio.equals("--") || this.icRatio.equals("nan"))
            this.icRatio = "暂无数据";
        if (this.sheqRatio.equals("--") || this.sheqRatio.equals("nan"))
            this.sheqRatio = "暂无数据";
        if (this.adRatio.equals("--") || this.adRatio.equals("nan"))
            this.adRatio = "暂无数据";
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

    public String getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(String currentRatio) {
        this.currentRatio = currentRatio;
    }

    public String getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(String quickRatio) {
        this.quickRatio = quickRatio;
    }

    public String getCashRatio() {
        return cashRatio;
    }

    public void setCashRatio(String cashRatio) {
        this.cashRatio = cashRatio;
    }

    public String getIcRatio() {
        return icRatio;
    }

    public void setIcRatio(String icRatio) {
        this.icRatio = icRatio;
    }

    public String getSheqRatio() {
        return sheqRatio;
    }

    public void setSheqRatio(String sheqRatio) {
        this.sheqRatio = sheqRatio;
    }

    public String getAdRatio() {
        return adRatio;
    }

    public void setAdRatio(String adRatio) {
        this.adRatio = adRatio;
    }

}
