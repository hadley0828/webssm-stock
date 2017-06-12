package com.quantour.ssm.model;

import com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO;
import com.quantour.ssm.dto.customizeStrategy.StockPondDTO;
import com.quantour.ssm.dto.customizeStrategy.TradeModelDTO;
import com.quantour.ssm.dto.strategyResultDTO;
import com.quantour.ssm.util.DateConvert;

/**
 * Created by zhangzy on 2017/6/9.
 * 自定义策略
 */
public class CustomizeStrategy {

    private String strategyId;
    private String creatorId;
    private String strategyName;
    private String strategyExplanation;
    private String createTime;
    private String stockPondChosen;
    private String indexIngredient;
    private String block;
    private String industry;
    private String concept;
    private String stStock;
    private String exchange;
    private String region;
    private Integer transferCycle;
    private Integer maxHoldStockNumber;
    private String straId;
    private String yearProfit;
    private String standardProfit;
    private Double alpha;
    private Double beta;
    private Double sharpRate;
    private Double profitWaveRate;
    private Double infoPercent;
    private Double maxBack;
    private Double turnoverRate;
    private Double currentStandardProfit;
    private Double currentStraProfit;

    public CustomizeStrategy(){
        super();
    }

    public CustomizeStrategy(CustomizeStrategyDTO csd){
        this.strategyId=csd.getStrategyID();
        if(csd.getCreaterID()!=null){
            this.creatorId=csd.getCreaterID();
        }
        if(csd.getStrategyName()!=null){
            this.strategyName=csd.getStrategyName();
        }
        if(csd.getStrategyExplanation()!=null){
            this.strategyExplanation=csd.getStrategyExplanation();
        }
        if(csd.getCreateTime()!=null){
            this.createTime=csd.getCreateTime();
        }
        StockPondDTO spd=csd.getStockPondDTO();
        if(spd.getStockPondChosen()!=null){
            this.stockPondChosen=spd.getStockPondChosen();
        }
        if(spd.getIndexIngredient()!=null){
            this.indexIngredient=spd.getIndexIngredient();
        }
        if(spd.getBlock()!=null){
            this.block=spd.getBlock();
        }
        if(spd.getIndustry()!=null){
            this.industry=spd.getIndustry();
        }
        if(spd.getConcept()!=null){
            this.concept=spd.getConcept();
        }
        if(spd.getSTStock()!=null){
            this.stStock=spd.getSTStock();
        }
        if(spd.getExchange()!=null){
            this.exchange=spd.getExchange();
        }
        if(spd.getRegion()!=null){
            this.region=spd.getRegion();
        }
        TradeModelDTO tmd=csd.getTradeModelDTO();
        this.transferCycle=tmd.getTransferCycle();
        this.maxHoldStockNumber=tmd.getMaxHoldStockNumber();

        strategyResultDTO resultDTO=csd.getResultDTO();

        this.straId=resultDTO.getStraId();
        this.yearProfit=resultDTO.getYearProfit();
        this.standardProfit=resultDTO.getStandardProfit();
        this.alpha=resultDTO.getAlpha();
        this.beta=resultDTO.getBeta();
        this.sharpRate=resultDTO.getSharpRate();
        this.profitWaveRate=resultDTO.getProfitWaveRate();
        this.infoPercent=resultDTO.getInfoPercent();
        this.maxBack=resultDTO.getMaxBack();
        this.turnoverRate=resultDTO.getTurnoverRate();
        this.currentStandardProfit=resultDTO.getCurrentStandardProfit();
        this.currentStraProfit=resultDTO.getCurrentStraProfit();


    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }


    public void setYearProfit(String yearProfit) {
        this.yearProfit = yearProfit;
    }

    public void setStandardProfit(String standardProfit) {
        this.standardProfit = standardProfit;
    }


    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyExplanation() {
        return strategyExplanation;
    }

    public void setStrategyExplanation(String strategyExplanation) {
        this.strategyExplanation = strategyExplanation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStockPondChosen() {
        return stockPondChosen;
    }

    public void setStockPondChosen(String stockPondChosen) {
        this.stockPondChosen = stockPondChosen;
    }

    public String getIndexIngredient() {
        return indexIngredient;
    }

    public void setIndexIngredient(String indexIngredient) {
        this.indexIngredient = indexIngredient;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getStStock() {
        return stStock;
    }

    public void setStStock(String stStock) {
        this.stStock = stStock;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getTransferCycle() {
        return transferCycle;
    }

    public void setTransferCycle(Integer transferCycle) {
        this.transferCycle = transferCycle;
    }

    public Integer getMaxHoldStockNumber() {
        return maxHoldStockNumber;
    }

    public void setMaxHoldStockNumber(Integer maxHoldStockNumber) {
        this.maxHoldStockNumber = maxHoldStockNumber;
    }

    public String getStraId() {
        return straId;
    }

    public void setStraId(String straId) {
        this.straId = straId;
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

    public Double getSharpRate() {
        return sharpRate;
    }

    public void setSharpRate(Double sharpRate) {
        this.sharpRate = sharpRate;
    }

    public Double getProfitWaveRate() {
        return profitWaveRate;
    }

    public void setProfitWaveRate(Double profitWaveRate) {
        this.profitWaveRate = profitWaveRate;
    }

    public Double getInfoPercent() {
        return infoPercent;
    }

    public void setInfoPercent(Double infoPercent) {
        this.infoPercent = infoPercent;
    }

    public Double getMaxBack() {
        return maxBack;
    }

    public void setMaxBack(Double maxBack) {
        this.maxBack = maxBack;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Double getCurrentStandardProfit() {
        return currentStandardProfit;
    }

    public void setCurrentStandardProfit(Double currentStandardProfit) {
        this.currentStandardProfit = currentStandardProfit;
    }

    public String getYearProfit() {
        return yearProfit;
    }

    public String getStandardProfit() {
        return standardProfit;
    }

    public Double getCurrentStraProfit() {
        return currentStraProfit;
    }

    public void setCurrentStraProfit(Double currentStraProfit) {
        this.currentStraProfit = currentStraProfit;
    }
}
