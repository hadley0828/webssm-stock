package com.quantour.ssm.test;

import com.quantour.ssm.dao.HistoryMapper;
import com.quantour.ssm.model.StockRecord;
import com.quantour.ssm.model.StrategyRecord;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by loohaze on 2017/6/8.
 */
public class HistoryTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    HistoryMapper historyMapper = session.getMapper(HistoryMapper.class);

    public static void main(String[] args) {
        HistoryTest test = new HistoryTest();

//        test.testInsertOneStockRecord();
//        test.testDeleteOneStockRecord();
//        test.testGetAllStockRecord();
//        test.testSelectOneStockRecord();
//        test.testDeleteAllStockRecord();

//        test.testInsertOneStragetyRecord();
//        test.testGetAllStragetyRecord();
//        test.testGetOneStragetyRecord();
//        test.testDeleteOneStragetyRecord();
    }


    public void testGetAllStockRecord(){
        ArrayList<StockRecord> list = historyMapper.getUserAllStockRecord("loohaze");
        for(StockRecord s : list){
            System.out.println(s.getCode_id());
        }
    }

    public void testSelectOneStockRecord(){
        String userid = "loohaze";
        String date_time = "Thu Jun 08 19:57:27 CST 2017";

        StockRecord s = historyMapper.selectOneStockRecord(userid,date_time);

    }


    public void testInsertOneStockRecord(){
        StockRecord stockRecord = new StockRecord();

        stockRecord.setUser_id("loohaze");
        stockRecord.setDate_time(new Date().toString());
        stockRecord.setCode_id("000001");

        historyMapper.insertOneStockRecord(stockRecord);

        session.commit();
    }

    public void testDeleteOneStockRecord(){
        String userid = "loohaze";

        String date_time = "Thu Jun 08 19:57:04 CST 2017";

        historyMapper.deleteOneStockRecord(userid,date_time);
        session.commit();
    }

    public void testDeleteAllStockRecord(){
        String userid = "loohaze";

        historyMapper.deleteAllStockRecord(userid);

        session.commit();
    }

    public void testGetAllStragetyRecord(){
        String userid = "loohaze";

        ArrayList<StrategyRecord> list = historyMapper.getUserAllStrategyRecord(userid);

        for(StrategyRecord s : list){
            System.out.println(s.getStrategy_name());
        }


    }

    public void testGetOneStragetyRecord(){
        String userid = "loohaze";
        String result_time = "Thu Jun 08 20:12:49 CST 2017";

        StrategyRecord s = historyMapper.selectOneStrategyRecord(userid,result_time);

        System.out.println(s.getStrategy_name());
    }

    public void testInsertOneStragetyRecord(){
        StrategyRecord s = new StrategyRecord();
        s.setUser_id("loohaze");
        s.setResult_time(new Date().toString());
        s.setStrategy_name("Stragety1");
        s.setStrategy_intro("nonono");
        s.setStart_time(java.sql.Date.valueOf("2017-06-01"));
        s.setEnd_time(java.sql.Date.valueOf("2017-06-02"));
        s.setBase_block("000300");
        s.setYear_profit("100.0");
        s.setStandard_profit("100.0");
        s.setAlpha(1.0);
        s.setBeta(2.0);
        s.setSharp_rate(3.0);
        s.setProfit_waverate(4.0);
        s.setInfo_percent(5.0);
        s.setMax_back(6.0);
        s.setTurnover_rate(7.0);

        historyMapper.insertOneStrategyRecord(s);

        session.commit();
    }

    public void testDeleteOneStragetyRecord(){
        String userid = "loohaze";
        String result_time = "Thu Jun 08 20:12:49 CST 2017";

        historyMapper.deleteOneStrategyRecord(userid,result_time);

        session.commit();
    }
}
