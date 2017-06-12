package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/4.
 * 基本面诊股
 */
public class BasicDTO {
    double basicScore;
    double partScore;
    double defeatPercent;

    Basic_cashFlowDTO basicCashFlowDTO; //现金流量
    Basic_earningDTO basicEarningDTO;   //营收能力
    Basic_growDTO basicGrowDTO;         //成长能力
    Basic_paymentDTO basicPaymentDTO;   //偿债能力
    Basic_profitDTO basicProfitDTO;     //盈利能力


    public BasicDTO(){
        super();
    }

    @Override
    public String toString() {
        return "BasicDTO{" +
                "basicScore=" + basicScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", basicCashFlowDTO=" + basicCashFlowDTO +
                ", basicEarningDTO=" + basicEarningDTO +
                ", basicGrowDTO=" + basicGrowDTO +
                ", basicPaymentDTO=" + basicPaymentDTO +
                ", basicProfitDTO=" + basicProfitDTO +
                '}';
    }





    public double getBasicScore() {
        return basicScore;
    }

    public void setBasicScore(double basicScore) {
        this.basicScore = basicScore;
    }

    public double getPartScore() {
        return partScore;
    }

    public void setPartScore(double partScore) {
        this.partScore = partScore;
    }

    public double getDefeatPercent() {
        return defeatPercent;
    }

    public void setDefeatPercent(double defeatPercent) {
        this.defeatPercent = defeatPercent;
    }

    public Basic_cashFlowDTO getBasicCashFlowDTO() {
        return basicCashFlowDTO;
    }

    public void setBasicCashFlowDTO(Basic_cashFlowDTO basicCashFlowDTO) {
        this.basicCashFlowDTO = basicCashFlowDTO;
    }

    public Basic_earningDTO getBasicEarningDTO() {
        return basicEarningDTO;
    }

    public void setBasicEarningDTO(Basic_earningDTO basicEarningDTO) {
        this.basicEarningDTO = basicEarningDTO;
    }

    public Basic_growDTO getBasicGrowDTO() {
        return basicGrowDTO;
    }

    public void setBasicGrowDTO(Basic_growDTO basicGrowDTO) {
        this.basicGrowDTO = basicGrowDTO;
    }

    public Basic_paymentDTO getBasicPaymentDTO() {
        return basicPaymentDTO;
    }

    public void setBasicPaymentDTO(Basic_paymentDTO basicPaymentDTO) {
        this.basicPaymentDTO = basicPaymentDTO;
    }

    public Basic_profitDTO getBasicProfitDTO() {
        return basicProfitDTO;
    }

    public void setBasicProfitDTO(Basic_profitDTO basicProfitDTO) {
        this.basicProfitDTO = basicProfitDTO;
    }



}
