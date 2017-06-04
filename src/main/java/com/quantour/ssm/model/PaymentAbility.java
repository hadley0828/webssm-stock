package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/3.
 * 偿债能力
 */
public class PaymentAbility {
    private String code;
    private String name;
    private String currentratio;
    private String quickratio;
    private String cashratio;
    private String icratio;
    private String sheqratio;
    private String adratio;

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

    public String getCurrentratio() {
        return currentratio;
    }

    public void setCurrentratio(String currentratio) {
        this.currentratio = currentratio;
    }

    public String getQuickratio() {
        return quickratio;
    }

    public void setQuickratio(String quickratio) {
        this.quickratio = quickratio;
    }

    public String getCashratio() {
        return cashratio;
    }

    public void setCashratio(String cashratio) {
        this.cashratio = cashratio;
    }

    public String getIcratio() {
        return icratio;
    }

    public void setIcratio(String icratio) {
        this.icratio = icratio;
    }

    public String getSheqratio() {
        return sheqratio;
    }

    public void setSheqratio(String sheqratio) {
        this.sheqratio = sheqratio;
    }

    public String getAdratio() {
        return adratio;
    }

    public void setAdratio(String adratio) {
        this.adratio = adratio;
    }
}
