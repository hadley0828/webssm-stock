package com.quantour.ssm.service.impl;

import com.quantour.ssm.dto.oneExtraProfitDTO;
import com.quantour.ssm.dto.strategyResultDTO;
import com.quantour.ssm.service.StaticService;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/5/17.
 */
public class StaticServiceImpl implements StaticService {
    @Override
    public strategyResultDTO getStraOneResult(int formDays, int holdDays, String sDate, String lDate, int type, ArrayList<String> codeList, String blockCode) {
        return null;
    }

    @Override
    public ArrayList<oneExtraProfitDTO> getOneExtraProfit(int daysType, int days, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        return null;
    }

    @Override
    public strategyResultDTO getStraTwoResult(int averageDays, int holdDays, int stockNumbers, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        return null;
    }

    @Override
    public ArrayList<oneExtraProfitDTO> getTwoExtraProfit(int averageDays, int stockNumbers, String sDate, String lDate, int stockType, ArrayList<String> codeList, String blockCode) {
        return null;
    }
}
