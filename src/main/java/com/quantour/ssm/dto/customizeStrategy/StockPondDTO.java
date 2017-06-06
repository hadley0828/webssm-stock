package com.quantour.ssm.dto.customizeStrategy;

/**
 * Created by zhangzy on 2017/6/6.
 * 自选策略中的选股设置
 */
public class StockPondDTO {

    String stockPondChosen;    //0：全部股票    1：自选股票池
    String IndexIngredient;    //0：全选  1：sh000016:上证50  2：sh000300:沪深300 3：sh000905:中证500
    String block;              //0：全选  1：主板    2：创业板   3：中小板
    String industry;           //全选加上全部的行业板块(金融行业等)
    String concept;            //全选加上全部的概念板块(保险重仓、券商重仓等)
    String STStock;            //包含ST   排除ST    仅有ST
    String exchange;           //全选 上海  深圳

    @Override
    public String toString() {
        return "StockPondDTO{" +
                "stockPondChosen='" + stockPondChosen + '\'' +
                ", IndexIngredient='" + IndexIngredient + '\'' +
                ", block='" + block + '\'' +
                ", industry='" + industry + '\'' +
                ", concept='" + concept + '\'' +
                ", STStock='" + STStock + '\'' +
                ", exchange='" + exchange + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public String getStockPondChosen() {
        return stockPondChosen;
    }

    public void setStockPondChosen(String stockPondChosen) {
        this.stockPondChosen = stockPondChosen;
    }

    public String getIndexIngredient() {
        return IndexIngredient;
    }

    public void setIndexIngredient(String indexIngredient) {
        IndexIngredient = indexIngredient;
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

    public String getSTStock() {
        return STStock;
    }

    public void setSTStock(String STStock) {
        this.STStock = STStock;
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

    String region;             //全选加上全部的地域板块(深圳、北京等)

    public StockPondDTO(){
        super();
    }
}
