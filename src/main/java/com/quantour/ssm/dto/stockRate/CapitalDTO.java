package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/4.
 * 资金面诊股
 */
public class CapitalDTO {
    //分成两个2*2的表格 上面一行近五日资金流向   下面一行机构持股比率
    double capitalScore;    //资金面分数
    double partScore;   //根据指标得到的分数 用来排名
    double defeatPercent;   //击败的多少百分比的股票
    double todayStockFlow;  //今日股票流向
    double fiveStockFlow;   //五日股票流向
    double tenStockFlow;    //十日股票流向
    double twentyStockFlow; //二十日股票流向
    double todayIndustryFlow;   //今日行业流向
    double fiveIndustryFlow;    //五日行业流向
    double tenIndustryFlow;     //十日行业流向
    double twentyIndustryFlow;  //二十日行业流向
    double institutionPercent;  //机构持股比率

    @Override
    public String toString() {
        return "CapitalDTO{" +
                "capitalScore=" + capitalScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", todayStockFlow=" + todayStockFlow +
                ", fiveStockFlow=" + fiveStockFlow +
                ", tenStockFlow=" + tenStockFlow +
                ", twentyStockFlow=" + twentyStockFlow +
                ", todayIndustryFlow=" + todayIndustryFlow +
                ", fiveIndustryFlow=" + fiveIndustryFlow +
                ", tenIndustryFlow=" + tenIndustryFlow +
                ", twentyIndustryFlow=" + twentyIndustryFlow +
                ", institutionPercent=" + institutionPercent +
                '}';
    }

    public double getCapitalScore() {
        return capitalScore;
    }

    public void setCapitalScore(double capitalScore) {
        this.capitalScore = capitalScore;
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

    public double getTodayStockFlow() {
        return todayStockFlow;
    }

    public void setTodayStockFlow(double todayStockFlow) {
        this.todayStockFlow = todayStockFlow;
    }

    public double getFiveStockFlow() {
        return fiveStockFlow;
    }

    public void setFiveStockFlow(double fiveStockFlow) {
        this.fiveStockFlow = fiveStockFlow;
    }

    public double getTenStockFlow() {
        return tenStockFlow;
    }

    public void setTenStockFlow(double tenStockFlow) {
        this.tenStockFlow = tenStockFlow;
    }

    public double getTwentyStockFlow() {
        return twentyStockFlow;
    }

    public void setTwentyStockFlow(double twentyStockFlow) {
        this.twentyStockFlow = twentyStockFlow;
    }

    public double getTodayIndustryFlow() {
        return todayIndustryFlow;
    }

    public void setTodayIndustryFlow(double todayIndustryFlow) {
        this.todayIndustryFlow = todayIndustryFlow;
    }

    public double getFiveIndustryFlow() {
        return fiveIndustryFlow;
    }

    public void setFiveIndustryFlow(double fiveIndustryFlow) {
        this.fiveIndustryFlow = fiveIndustryFlow;
    }

    public double getTenIndustryFlow() {
        return tenIndustryFlow;
    }

    public void setTenIndustryFlow(double tenIndustryFlow) {
        this.tenIndustryFlow = tenIndustryFlow;
    }

    public double getTwentyIndustryFlow() {
        return twentyIndustryFlow;
    }

    public void setTwentyIndustryFlow(double twentyIndustryFlow) {
        this.twentyIndustryFlow = twentyIndustryFlow;
    }

    public double getInstitutionPercent() {
        return institutionPercent;
    }

    public void setInstitutionPercent(double institutionPercent) {
        this.institutionPercent = institutionPercent;
    }

    public CapitalDTO(){
        super();
    }


}
