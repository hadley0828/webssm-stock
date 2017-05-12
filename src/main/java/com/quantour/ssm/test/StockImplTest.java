package com.quantour.ssm.test;

import com.quantour.ssm.model.Stock;
import com.quantour.ssm.model.StockKey;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/5/12.
 */
public class StockImplTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();

    public static void main(String[] args) {

        StockImplTest test = new StockImplTest();
//        test.testGetTimesStocks();
//        test.testGetOneDayStocks();
        test.testGetYesterdayStock();
    }

    public void testGetTimesStocks(){
        HashMap<Object,Object> map = new HashMap<Object, Object>();
        map.put("code","000001");
        map.put("start", Date.valueOf("2007-04-03"));
        map.put("end",Date.valueOf("2007-06-21"));
        List<Stock> stockList = session.selectList("com.quantour.ssm.dao.StockMapper.getTimesStocks",map);

        for(Stock s: stockList){
            System.out.print(s.getStockCode() + " ");
            System.out.print(s.getOpenPrice() + " ");
            System.out.print(s.getClosePrice()+ " ");
            System.out.println(s.getStockDate());
        }
        session.commit();
        session.close();
    }

    public void testGetOneDayStocks(){
        Date date = Date.valueOf("2007-05-03");
        List<Stock> list = session.selectList("com.quantour.ssm.dao.StockMapper.getOneDayStocks",date);


        for(Stock s: list){
            System.out.print(s.getStockCode() + " ");
            System.out.print(s.getOpenPrice() + " ");
            System.out.print(s.getClosePrice()+ " ");
            System.out.println(s.getStockDate());
        }
    }

    public void testGetYesterdayStock(){
        StockKey stockKey = new StockKey();
        stockKey.setStockCode("000001");
        stockKey.setStockDate(Date.valueOf("2007-05-05"));
        Stock stock = session.selectOne("com.quantour.ssm.dao.StockMapper.getYesterdayStock",stockKey);

        System.out.print(stock.getStockCode() + " ");
        System.out.print(stock.getOpenPrice() + " ");
        System.out.print(stock.getClosePrice()+ " ");
        System.out.println(stock.getStockDate());

    }
}
