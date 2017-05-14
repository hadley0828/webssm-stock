package com.quantour.ssm.dto;

/**
 * Created by zhangzy on 2017/5/14.
 */
public class oneExtraProfitDTO {
    int cycle;                  //相对强弱计算周期;
    double extraProfit;         //超额收益率;
    double winRate;             //一年内胜率;

    @Override
    public String toString() {
        return "oneExtraProfitDTO{" +
                "cycle=" + cycle +
                ", extraProfit=" + extraProfit +
                ", winRate=" + winRate +
                '}';
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public double getExtraProfit() {
        return extraProfit;
    }

    public void setExtraProfit(double extraProfit) {
        this.extraProfit = extraProfit;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public oneExtraProfitDTO(){
        super();
    }
}
