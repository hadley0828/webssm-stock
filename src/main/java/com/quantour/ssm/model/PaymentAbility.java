package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/3.
 * 偿债能力
 */
public class PaymentAbility {
    private String code;
    private String name;
    private Double currentratio;
    private Double quickratio;
    private Double cashratio;
    private Double icratio;
    private Double sheqratio;
    private Double adratio;

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

    public Double getCurrentratio() {
        return currentratio;
    }

    public void setCurrentratio(Double currentratio) {
        this.currentratio = currentratio;
    }

    public Double getQuickratio() {
        return quickratio;
    }

    public void setQuickratio(Double quickratio) {
        this.quickratio = quickratio;
    }

    public Double getCashratio() {
        return cashratio;
    }

    public void setCashratio(Double cashratio) {
        this.cashratio = cashratio;
    }

    public Double getIcratio() {
        return icratio;
    }

    public void setIcratio(Double icratio) {
        this.icratio = icratio;
    }

    public Double getSheqratio() {
        return sheqratio;
    }

    public void setSheqratio(Double sheqratio) {
        this.sheqratio = sheqratio;
    }

    public Double getAdratio() {
        return adratio;
    }

    public void setAdratio(Double adratio) {
        this.adratio = adratio;
    }
}
