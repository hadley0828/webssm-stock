package com.quantour.ssm.model;

/**
 * Created by zhangzy on 2017/6/3.
 * 营收能力
 */
public class EarningAbility {
    private String code;
    private String name;
    private String arturnover;
    private String arturndays;
    private String inventory_turnover;
    private String inventory_days;
    private String currentasset_turnover;
    private String currentasset_days;

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

    public String getArturnover() {
        return arturnover;
    }

    public void setArturnover(String arturnover) {
        this.arturnover = arturnover;
    }

    public String getArturndays() {
        return arturndays;
    }

    public void setArturndays(String arturndays) {
        this.arturndays = arturndays;
    }

    public String getInventory_turnover() {
        return inventory_turnover;
    }

    public void setInventory_turnover(String inventory_turnover) {
        this.inventory_turnover = inventory_turnover;
    }

    public String getInventory_days() {
        return inventory_days;
    }

    public void setInventory_days(String inventory_days) {
        this.inventory_days = inventory_days;
    }

    public String getCurrentasset_turnover() {
        return currentasset_turnover;
    }

    public void setCurrentasset_turnover(String currentasset_turnover) {
        this.currentasset_turnover = currentasset_turnover;
    }

    public String getCurrentasset_days() {
        return currentasset_days;
    }

    public void setCurrentasset_days(String currentasset_days) {
        this.currentasset_days = currentasset_days;
    }
}
