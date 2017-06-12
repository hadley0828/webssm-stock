package com.quantour.ssm.dto;

import java.util.HashMap;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class indexProfitDTO {
    int plusCycles;         //正收益周期数;
    int minusCycles;        //负收益周期数;
    String winRate;         //赢率;

    HashMap<Double,Integer> cycleChangeMap;   //double是靠近的那个变化率  integer是数量

    @Override
    public String toString() {
        return "indexProfitDTO{" +
                "plusCycles=" + plusCycles +
                ", minusCycles=" + minusCycles +
                ", winRate=" + winRate +
                ", cycleChangeMap=" + cycleChangeMap +
                '}';
    }

    public int getPlusCycles() {
        return plusCycles;
    }

    public void setPlusCycles(int plusCycles) {
        this.plusCycles = plusCycles;
    }

    public int getMinusCycles() {
        return minusCycles;
    }

    public void setMinusCycles(int minusCycles) {
        this.minusCycles = minusCycles;
    }


    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }


    public HashMap<Double, Integer> getCycleChangeMap() {
        return cycleChangeMap;
    }

    public void setCycleChangeMap(HashMap<Double, Integer> cycleChangeMap) {
        this.cycleChangeMap = cycleChangeMap;
    }



    public indexProfitDTO(){
        super();
    }
}
