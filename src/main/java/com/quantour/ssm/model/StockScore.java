package com.quantour.ssm.model;

/**
 * Created by loohaze on 2017/6/12.
 */
public class StockScore {

    private String code;

    private double technicalScore;
    private double capitalScore;
    private double messageScore;
    private double industryScore;
    private double basicScore;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(double technicalScore) {
        this.technicalScore = technicalScore;
    }

    public double getCapitalScore() {
        return capitalScore;
    }

    public void setCapitalScore(double capitalScore) {
        this.capitalScore = capitalScore;
    }

    public double getMessageScore() {
        return messageScore;
    }

    public void setMessageScore(double messageScore) {
        this.messageScore = messageScore;
    }

    public double getIndustryScore() {
        return industryScore;
    }

    public void setIndustryScore(double industryScore) {
        this.industryScore = industryScore;
    }

    public double getBasicScore() {
        return basicScore;
    }

    public void setBasicScore(double basicScore) {
        this.basicScore = basicScore;
    }

}
