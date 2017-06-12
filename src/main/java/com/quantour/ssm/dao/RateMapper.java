package com.quantour.ssm.dao;

import com.quantour.ssm.model.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangzy on 2017/6/4.
 */
@Repository
public interface RateMapper {

    /**
     * 现金流量
     * @param stockCode
     * @return
     */
    public CashFlow getOneCashFlow(String stockCode);

    public List<CashFlow> getAllCashFlow();

    /**
     * 营收能力
     * @param stockCode
     * @return
     */
    public EarningAbility getOneEarningAbility(String stockCode);

    public List<EarningAbility> getAllEarningAbility();

    /**
     * 成长能力
     * @param stockCode
     * @return
     */
    public GrowAbility getOneGrowAbility(String stockCode);

    public List<GrowAbility> getAllGrowAbility();

    /**
     * 偿债能力
     * @param stockCode
     * @return
     */
    public PaymentAbility getOnePaymentAbility(String stockCode);

    public List<PaymentAbility> getAllPaymentAbility();

    /**
     * 盈利能力
     * @param stockCode
     * @return
     */
    public ProfitAbility getOneProfitAbility(String stockCode);

    public List<ProfitAbility> getAllProfitAbility();

    /**
     * 行业资金流向
     * @param map industry & date
     * @return
     */
    public ProfessionFundFlows getOneProfessionFundFlows(HashMap map);

    public List<ProfessionFundFlows> getAllProfessionFundFlows();

    /**
     * 个股资金流向
     * @param map code & date
     * @return
     */
    public SingleStockFundFlows getSingleStockFundFlows(HashMap map);

    public List<SingleStockFundFlows> getAllSingleStockFundFlows();


    public void insertProfessionFundFlows(ProfessionFundFlows professionFundFlows);

    /**
     * 获得一个股票全部的机构交易
     * @param stockCode
     * @return
     */
    public InstitutionTrade getOneInstitutionTrade(String stockCode);

    /**
     * 获得全部的机构交易信息
     * @return
     */
    public List<InstitutionTrade> getAllInstitutionTrade();


    public List<StockScore> getAllStockScore();

    public StockScore getOneStockScore(String code);

    public void insertStockScore(StockScore stockScore);
}
