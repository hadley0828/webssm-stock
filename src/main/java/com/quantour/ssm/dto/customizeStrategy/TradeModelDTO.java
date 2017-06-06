package com.quantour.ssm.dto.customizeStrategy;

/**
 * Created by zhangzy on 2017/6/6.
 * 自定义策略中的交易模型
 */
public class TradeModelDTO {
    int transferCycle;  //调仓周期
    int maxHoldStockNumber; //最大持仓股票数

    @Override
    public String toString() {
        return "TradeModelDTO{" +
                "transferCycle=" + transferCycle +
                ", maxHoldStockNumber=" + maxHoldStockNumber +
                '}';
    }

    public int getTransferCycle() {
        return transferCycle;
    }

    public void setTransferCycle(int transferCycle) {
        this.transferCycle = transferCycle;
    }

    public int getMaxHoldStockNumber() {
        return maxHoldStockNumber;
    }

    public void setMaxHoldStockNumber(int maxHoldStockNumber) {
        this.maxHoldStockNumber = maxHoldStockNumber;
    }

    public TradeModelDTO(){
        super();
    }


}
