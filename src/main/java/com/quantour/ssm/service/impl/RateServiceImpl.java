package com.quantour.ssm.service.impl;

import com.quantour.ssm.dto.stockRate.GeneralScoreDTO;
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
    public GeneralScoreDTO getOneStockGeneralScore(String code) {
        return null;
    }

    @Override
    public void calculateOneDayStockScore(String date) {

    }
}
