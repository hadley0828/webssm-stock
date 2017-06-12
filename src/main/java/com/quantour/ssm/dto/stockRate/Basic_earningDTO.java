package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/12.
 * 营收能力
 */
public class Basic_earningDTO {
    String code;    //代码
    String name;    //名称
    String arTurnOver;  //应收账款周转率
    String arTurnDays;  //应收账款周转天数
    String inventoryTurnOver;   //存货周转率
    String inventoryDays;       //存货周转天数
    String currentAssetTurnOver;//流动资产周转率
    String currentAssetDays;    //流动资产周转天数

    public Basic_earningDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Basic_earningDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", arTurnOver='" + arTurnOver + '\'' +
                ", arTurnDays='" + arTurnDays + '\'' +
                ", inventoryTurnOver='" + inventoryTurnOver + '\'' +
                ", inventoryDays='" + inventoryDays + '\'' +
                ", currentAssetTurnOver='" + currentAssetTurnOver + '\'' +
                ", currentAssetDays='" + currentAssetDays + '\'' +
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

    public String getArTurnOver() {
        return arTurnOver;
    }

    public void setArTurnOver(String arTurnOver) {
        this.arTurnOver = arTurnOver;
    }

    public String getArTurnDays() {
        return arTurnDays;
    }

    public void setArTurnDays(String arTurnDays) {
        this.arTurnDays = arTurnDays;
    }

    public String getInventoryTurnOver() {
        return inventoryTurnOver;
    }

    public void setInventoryTurnOver(String inventoryTurnOver) {
        this.inventoryTurnOver = inventoryTurnOver;
    }

    public String getInventoryDays() {
        return inventoryDays;
    }

    public void setInventoryDays(String inventoryDays) {
        this.inventoryDays = inventoryDays;
    }

    public String getCurrentAssetTurnOver() {
        return currentAssetTurnOver;
    }

    public void setCurrentAssetTurnOver(String currentAssetTurnOver) {
        this.currentAssetTurnOver = currentAssetTurnOver;
    }

    public String getCurrentAssetDays() {
        return currentAssetDays;
    }

    public void setCurrentAssetDays(String currentAssetDays) {
        this.currentAssetDays = currentAssetDays;
    }





}
