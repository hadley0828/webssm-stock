package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/11.
 */
public class FundFlowMapDTO {
    String date;
    double singleFlow;
    double industryAverageFlow;

    public FundFlowMapDTO(){
        super();
    }

    @Override
    public String toString() {
        return "FundFlowMapDTO{" +
                "date='" + date + '\'' +
                ", singleFlow=" + singleFlow +
                ", industryAverageFlow=" + industryAverageFlow +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getSingleFlow() {
        return singleFlow;
    }

    public void setSingleFlow(double singleFlow) {
        this.singleFlow = singleFlow;
    }

    public double getIndustryAverageFlow() {
        return industryAverageFlow;
    }

    public void setIndustryAverageFlow(double industryAverageFlow) {
        this.industryAverageFlow = industryAverageFlow;
    }





}
