package com.quantour.ssm.dto.customizeStrategy;

import com.quantour.ssm.dto.strategyResultDTO;
import com.quantour.ssm.model.CustomizeStrategy;
import com.quantour.ssm.model.ScreenCondition;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/6.
 * 用来表示整个自定义策略
 */
public class CustomizeStrategyDTO {
    String strategyID;  //createrID+" "+createTime
    String createrID;   //创建者的用户ID
    String strategyName;    //策略名称
    String strategyExplanation; //策略说明
    String createTime;         //创建的时间
    StockPondDTO stockPondDTO;
    ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList;
    TradeModelDTO tradeModelDTO;
    strategyResultDTO resultDTO; //在保存策略的时候生成一个区间的结果 用于展示自定义策略的时候可以出现示意图

    @Override
    public String toString() {
        return "CustomizeStrategyDTO{" +
                "strategyID='" + strategyID + '\'' +
                ", createrID='" + createrID + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", strategyExplanation='" + strategyExplanation + '\'' +
                ", createTime='" + createTime + '\'' +
                ", stockPondDTO=" + stockPondDTO +
                ", screeningConditionDTOArrayList=" + screeningConditionDTOArrayList +
                ", tradeModelDTO=" + tradeModelDTO +
                ", resultDTO=" + resultDTO +
                '}';
    }





    public CustomizeStrategyDTO(){
        super();
    }


    public CustomizeStrategyDTO(CustomizeStrategy cs, ArrayList<ScreenCondition> screenConditionArrayList){
        this.strategyID=cs.getStrategyId();
        if(cs.getCreatorId()!=null){
            this.createrID=cs.getCreatorId();
        }
        if(cs.getStrategyName()!=null){
            this.strategyName=cs.getStrategyName();
        }
        if(cs.getStrategyExplanation()!=null){
            this.strategyExplanation=cs.getStrategyExplanation();
        }
        if(cs.getCreateTime()!=null){
            this.createTime=cs.getCreateTime();
        }


        StockPondDTO oneStockPond=new StockPondDTO();


        if(cs.getStockPondChosen()!=null){
            oneStockPond.setStockPondChosen(cs.getStockPondChosen());
        }


        if(cs.getIndexIngredient()!=null){
            oneStockPond.setIndexIngredient(cs.getIndexIngredient());
        }
        if(cs.getBlock()!=null){
            oneStockPond.setBlock(cs.getBlock());
        }
        if(cs.getIndustry()!=null){
            oneStockPond.setIndustry(cs.getIndustry());
        }
        if(cs.getConcept()!=null){
            oneStockPond.setConcept(cs.getConcept());
        }
        if(cs.getStStock()!=null){
            oneStockPond.setSTStock(cs.getStStock());
        }
        if(cs.getExchange()!=null){
            oneStockPond.setExchange(cs.getExchange());
        }
        if(cs.getRegion()!=null){
            oneStockPond.setRegion(cs.getRegion());
        }

        this.stockPondDTO=oneStockPond;


        ArrayList<ScreeningConditionDTO> allConditionList=new ArrayList<ScreeningConditionDTO>();

        if(screenConditionArrayList.size()!=0){
            for(int count=0;count<screenConditionArrayList.size();count++){
                ScreenCondition sc=screenConditionArrayList.get(count);
                ScreeningConditionDTO screeningConditionDTO=new ScreeningConditionDTO();

                if(sc.getConditionName()!=null){
                    screeningConditionDTO.setConditionName(sc.getConditionName());
                }
                if(sc.getCompareSymbol()!=null){
                    screeningConditionDTO.setCompareSymbol(sc.getCompareSymbol());
                }
                if(sc.getScope()!=null){
                    screeningConditionDTO.setScope(sc.getScope());
                }
                if(sc.getFirstValue()!=null){
                    screeningConditionDTO.setFirstValue(sc.getFirstValue());
                }
                if(sc.getSecondValue()!=null){
                    screeningConditionDTO.setSecondValue(sc.getSecondValue());
                }

                allConditionList.add(screeningConditionDTO);
            }
        }
        this.screeningConditionDTOArrayList=allConditionList;

        TradeModelDTO oneTradeModel=new TradeModelDTO();

        if(cs.getTransferCycle()!=null){
            oneTradeModel.setTransferCycle(cs.getTransferCycle());
        }
        if(cs.getMaxHoldStockNumber()!=null){
            oneTradeModel.setMaxHoldStockNumber(cs.getMaxHoldStockNumber());
        }

        this.tradeModelDTO=oneTradeModel;

        strategyResultDTO oneResult=new strategyResultDTO();

        if(cs.getStraId()!=null){
            oneResult.setStraId(cs.getStraId());
        }
        if(cs.getYearProfit()!=null){
            oneResult.setYearProfit(cs.getYearProfit());
        }
        if(cs.getStandardProfit()!=null){
            oneResult.setStandardProfit(cs.getStandardProfit());
        }
        if(cs.getAlpha()!=null){
            oneResult.setAlpha(cs.getAlpha());
        }
        if(cs.getBeta()!=null){
            oneResult.setBeta(cs.getBeta());
        }
        if(cs.getSharpRate()!=null){
            oneResult.setSharpRate(cs.getSharpRate());
        }
        if(cs.getProfitWaveRate()!=null){
            oneResult.setProfitWaveRate(cs.getProfitWaveRate());
        }
        if(cs.getInfoPercent()!=null){
            oneResult.setInfoPercent(cs.getInfoPercent());
        }
        if(cs.getMaxBack()!=null){
            oneResult.setMaxBack(cs.getMaxBack());
        }
        if(cs.getTurnoverRate()!=null){
            oneResult.setTurnoverRate(cs.getTurnoverRate());
        }
        if(cs.getCurrentStandardProfit()!=null){
            oneResult.setStandardProfit(cs.getStandardProfit());
        }
        if(cs.getCurrentStraProfit()!=null){
            oneResult.setCurrentStraProfit(cs.getCurrentStraProfit());
        }
        this.resultDTO=oneResult;





    }




    public String getStrategyID() {
        return strategyID;
    }

    public void setStrategyID(String strategyID) {
        this.strategyID = strategyID;
    }


    public String getCreaterID() {
        return createrID;
    }

    public void setCreaterID(String createrID) {
        this.createrID = createrID;
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

    public StockPondDTO getStockPondDTO() {
        return stockPondDTO;
    }

    public void setStockPondDTO(StockPondDTO stockPondDTO) {
        this.stockPondDTO = stockPondDTO;
    }

    public ArrayList<ScreeningConditionDTO> getScreeningConditionDTOArrayList() {
        return screeningConditionDTOArrayList;
    }

    public void setScreeningConditionDTOArrayList(ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList) {
        this.screeningConditionDTOArrayList = screeningConditionDTOArrayList;
    }

    public TradeModelDTO getTradeModelDTO() {
        return tradeModelDTO;
    }

    public void setTradeModelDTO(TradeModelDTO tradeModelDTO) {
        this.tradeModelDTO = tradeModelDTO;
    }

    public strategyResultDTO getResultDTO() {
        return resultDTO;
    }

    public void setResultDTO(strategyResultDTO resultDTO) {
        this.resultDTO = resultDTO;
    }




}
