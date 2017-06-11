package com.quantour.ssm.service.impl;

import com.quantour.ssm.dto.stockRate.*;
import com.quantour.ssm.service.RateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangzy on 2017/6/3.
 * 评级功能
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RateServiceImpl implements RateService{


    @Override
    public GeneralScoreDTO getOneStockGeneralScore(String code,String date) {
        return null;
    }

    @Override
    public void calculateOneDayStockScore(String date) {

    }

    @Override
    public TechnicalDTO getOneStockTechnicalScore(String code, String date) {
        return null;
    }

    @Override
    public CapitalDTO getOneStockCapitalScore(String code, String date) {
        CapitalDTO capitalDTO=new CapitalDTO();




        return null;
    }

    @Override
    public MessageDTO getOneStockMessageScore(String code, String date) {
        return null;
    }

    @Override
    public IndustryDTO getOneStockIndustryScore(String code, String date) {
        return null;
    }

    @Override
    public BasicDTO getOneStockBasicScore(String code, String date) {
        return null;
    }
}
