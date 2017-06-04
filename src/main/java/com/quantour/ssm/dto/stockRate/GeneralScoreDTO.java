package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/4.
 * 总体得分 包含其他五个dto
 */
public class GeneralScoreDTO {
    String stockCode;   //股票编号
    String stockName;   //股票名称
    double totalScore;  //最终得分
    double defeatPercent;   //击败了多少百分比的股票
    String suggestion;  //卖出 减持 中性 增持 买入
    double technicalScore;  //技术面得分
    double capitalScore;    //资金面得分
    double messageScore;    //消息面得分
    double industryScore;   //行业面得分
    double basicScore;      //基本面得分
    TechnicalDTO technicalDTO;
    CapitalDTO capitalDTO;
    MessageDTO messageDTO;
    IndustryDTO industryDTO;
    BasicDTO basicDTO;

    public GeneralScoreDTO(){
        super();
    }

    @Override
    public String toString() {
        return "GeneralScoreDTO{" +
                "stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", totalScore=" + totalScore +
                ", defeatPercent=" + defeatPercent +
                ", suggestion='" + suggestion + '\'' +
                ", technicalScore=" + technicalScore +
                ", capitalScore=" + capitalScore +
                ", messageScore=" + messageScore +
                ", industryScore=" + industryScore +
                ", basicScore=" + basicScore +
                ", technicalDTO=" + technicalDTO +
                ", capitalDTO=" + capitalDTO +
                ", messageDTO=" + messageDTO +
                ", industryDTO=" + industryDTO +
                ", basicDTO=" + basicDTO +
                '}';
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getDefeatPercent() {
        return defeatPercent;
    }

    public void setDefeatPercent(double defeatPercent) {
        this.defeatPercent = defeatPercent;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
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

    public TechnicalDTO getTechnicalDTO() {
        return technicalDTO;
    }

    public void setTechnicalDTO(TechnicalDTO technicalDTO) {
        this.technicalDTO = technicalDTO;
    }

    public CapitalDTO getCapitalDTO() {
        return capitalDTO;
    }

    public void setCapitalDTO(CapitalDTO capitalDTO) {
        this.capitalDTO = capitalDTO;
    }

    public MessageDTO getMessageDTO() {
        return messageDTO;
    }

    public void setMessageDTO(MessageDTO messageDTO) {
        this.messageDTO = messageDTO;
    }

    public IndustryDTO getIndustryDTO() {
        return industryDTO;
    }

    public void setIndustryDTO(IndustryDTO industryDTO) {
        this.industryDTO = industryDTO;
    }

    public BasicDTO getBasicDTO() {
        return basicDTO;
    }

    public void setBasicDTO(BasicDTO basicDTO) {
        this.basicDTO = basicDTO;
    }



}
