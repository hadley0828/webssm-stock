package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.RateMapper;
import com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO;
import com.quantour.ssm.dto.customizeStrategy.ScreeningConditionDTO;
import com.quantour.ssm.dto.customizeStrategy.StockPondDTO;
import com.quantour.ssm.dto.customizeStrategy.TradeModelDTO;
import com.quantour.ssm.dto.strategyResultDTO;
import com.quantour.ssm.service.CustomizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/25.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomizeServiceImpl implements CustomizeService{

    @Resource
    private DayKLineMapper dayKLineMapper;
    private RateMapper rateMapper;

    @Override
    public ArrayList<String> getAllIndustryBlock() {
        return null;
    }

    @Override
    public ArrayList<String> getAllConceptBlock() {
        return null;
    }

    @Override
    public ArrayList<String> getAllAreaBlock() {
        return null;
    }

    @Override
    public strategyResultDTO getCustomizeStrategyResult(String sDate, String lDate, String blockCode, StockPondDTO stockPondDTO, ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList, TradeModelDTO tradeModelDTO) {
        return null;
    }

    @Override
    public CustomizeStrategyDTO getOneStrategy(String id, String strategyName) {
        return null;
    }

    @Override
    public boolean insertOneStrategy(CustomizeStrategyDTO customizeStrategyDTO) {
        return false;
    }

    @Override
    public boolean updateOneStrategy(CustomizeStrategyDTO customizeStrategyDTO) {
        return false;
    }

    @Override
    public ArrayList<CustomizeStrategyDTO> getAllCustomizeStrategy() {
        return null;
    }

    @Override
    public ArrayList<CustomizeStrategyDTO> getOneUserAllStrategy(String userID) {
        return null;
    }
}
