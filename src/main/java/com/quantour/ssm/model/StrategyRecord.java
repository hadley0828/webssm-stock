package com.quantour.ssm.model;

import java.sql.Date;

/**
 * Created by zhangzy on 2017/6/8.
 */
public class StrategyRecord {
    private String user_id; //用户id
    private String result_time; //生成结果的时间
    private String strategy_name;   //策略的名称
    private String strategy_intro;  //策略的说明
    private Date start_time;
    private Date end_time;
    private String base_block;
    private Double year_profit;
    private Double standard_profit;
    private Double alpha;
    private Double beta;
    private Double sharp_rate;
    private Double profit_waverate;
    private Double info_percent;
    private Double max_back;
    private Double turnover_rate;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getResult_time() {
        return result_time;
    }

    public void setResult_time(String result_time) {
        this.result_time = result_time;
    }

    public String getStrategy_name() {
        return strategy_name;
    }

    public void setStrategy_name(String strategy_name) {
        this.strategy_name = strategy_name;
    }

    public String getStrategy_intro() {
        return strategy_intro;
    }

    public void setStrategy_intro(String strategy_intro) {
        this.strategy_intro = strategy_intro;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getBase_block() {
        return base_block;
    }

    public void setBase_block(String base_block) {
        this.base_block = base_block;
    }

    public Double getYear_profit() {
        return year_profit;
    }

    public void setYear_profit(Double year_profit) {
        this.year_profit = year_profit;
    }

    public Double getStandard_profit() {
        return standard_profit;
    }

    public void setStandard_profit(Double standard_profit) {
        this.standard_profit = standard_profit;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double getSharp_rate() {
        return sharp_rate;
    }

    public void setSharp_rate(Double sharp_rate) {
        this.sharp_rate = sharp_rate;
    }

    public Double getProfit_waverate() {
        return profit_waverate;
    }

    public void setProfit_waverate(Double profit_waverate) {
        this.profit_waverate = profit_waverate;
    }

    public Double getInfo_percent() {
        return info_percent;
    }

    public void setInfo_percent(Double info_percent) {
        this.info_percent = info_percent;
    }

    public Double getMax_back() {
        return max_back;
    }

    public void setMax_back(Double max_back) {
        this.max_back = max_back;
    }

    public Double getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(Double turnover_rate) {
        this.turnover_rate = turnover_rate;
    }
}
