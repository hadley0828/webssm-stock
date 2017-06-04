package com.quantour.ssm.test;

import com.quantour.ssm.dao.RateMapper;
import com.quantour.ssm.model.CashFlow;
import com.quantour.ssm.model.ProfessionFundFlows;
import com.quantour.ssm.model.SingleStockFundFlows;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/6/4.
 */
public class RateTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    RateMapper rateMapper = session.getMapper(RateMapper.class);


    public static void main(String[] args) {
        RateTest test = new RateTest();
//        test.testGetOne();
//        test.testGetAll();
        test.testInsert();
    }

    public void testGetOne(){
        String code = "000001";
        HashMap map = new HashMap();
        map.put( "code" ,"000001");
        map.put("date" , Date.valueOf("2017-06-02"));

        CashFlow cashFlow = rateMapper.getOneCashFlow(code);
        SingleStockFundFlows s = rateMapper.getSingleStockFundFlows(map);
        System.out.println(s.getCode()+ s.getDate() + s.getInflow());
//        System.out.println(cashFlow.getName());
    }

    public void testGetAll(){
        List<CashFlow> list = rateMapper.getAllCashFlow();
        List<SingleStockFundFlows> list1 = rateMapper.getAllSingleStockFundFlows();
//        for(CashFlow c : list){
//            System.out.println(c.getName());
//        }

        for(SingleStockFundFlows s : list1){
            System.out.println(s.getInflow());
        }
    }

    public void testInsert(){
        ProfessionFundFlows p = new ProfessionFundFlows();

        p.setIndustry("1212");
        p.setDate(Date.valueOf("2017-01-01"));
        p.setInflow("124123");
        p.setChange_percent(90.0);

        rateMapper.insertProfessionFundFlows(p);

        session.commit();
    }
}
