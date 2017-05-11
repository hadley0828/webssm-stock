package com.quantour.ssm.dao;

import com.quantour.ssm.model.Stock;
import com.quantour.ssm.model.StockKey;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface StockMapper {
    int deleteByPrimaryKey(StockKey key);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(StockKey key);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    List<String> getAllDateByCode(String code);

    Stock getOneStock(StockKey stockKey);

    /**
     * 用hashmap封装参数 key值分别为 String code, String startDate, String endDate
     * @param map
     * @return
     */
    List<Stock> getTimesStocks(HashMap map);
}