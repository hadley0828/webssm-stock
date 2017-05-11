package com.quantour.ssm.test;

import com.quantour.ssm.model.Stock;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/5/12.
 */
public class StockImplTest {

    public static void main(String[] args) {
        SqlSession session = FKSqlSessionFactory.getSqlSession();

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
}
