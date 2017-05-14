package com.quantour.ssm.service.impl;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.StockMapper;
import com.quantour.ssm.dto.*;
import com.quantour.ssm.model.Block;
import com.quantour.ssm.model.Stock;
import com.quantour.ssm.model.StockKey;
import com.quantour.ssm.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by loohaze on 2017/5/11.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockServiceImpl implements StockService {

    @Resource
    private DayKLineMapper dayklinemapper;


    @Override
    public stockDTO getStockInfo(String code, String date) {
        return null;
    }

    @Override
    public ArrayList<klineDTO> getKline(String code, String sDate, String lDate) {
        return null;
    }

    @Override
    public marketDTO getMarketInfo(String date) {
        return null;
    }

    @Override
    public ArrayList<averageDTO> getAverageLine(String code, String sDate, String lDate, int days) {
        return null;
    }

    @Override
    public ArrayList<compareDTO> getCompareInfo(String firstCode, String secondCode, String sDate, String lDate) {
        return null;
    }

    @Override
    public boolean isStockValid(String input) {
        return false;
    }

    @Override
    public boolean isDateValid(String input, String date) {
        return false;
    }

    @Override
    public boolean isDateValid(String date) {
        return false;
    }

    @Override
    public ArrayList<String> getAllCodeAndName() {
        return null;
    }

    @Override
    public ArrayList<String> getAllPlateName() {
        return null;
    }

    @Override
    public ArrayList<String> getPlateAllStockCode(String plateName) {
        return null;
    }

    @Override
    public ArrayList<String> getOneStockAllPlate(String stockCode) {
        return null;
    }

    @Override
    public ArrayList<waveDTO> getTopNCodesByDays(int n, String date, int changeDays) {
        return null;
    }

    @Override
    public ArrayList<limitUpAndDownNumsDTO> getLimitUpAndDownNumber(String date) {
        return null;
    }

    @Override
    public boolean isMarketDateValid(String date) {
        return false;
    }

    @Override
    public boolean isBlockDateValid(String date, String blockCode) {
        return false;
    }

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
