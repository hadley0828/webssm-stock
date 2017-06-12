package com.quantour.ssm.test;

import com.quantour.ssm.dao.StrategyMapper;
import com.quantour.ssm.model.CustomizeStrategy;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by loohaze on 2017/6/9.
 */
public class StragetyTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    StrategyMapper strategyMapper = session.getMapper(StrategyMapper.class);

    public static void main(String[] args) {
        StragetyTest test = new StragetyTest();

//        test.testInsertCustomizeStrategy();
//        test.testGetAllCustomizeStrategy();
//        test.testSelectCustomizeStragety();
        test.testDeleteC();
    }




    public void testGetAllCustomizeStrategy(){
        ArrayList<CustomizeStrategy> list = strategyMapper.getAllCustomizeStrategy();
        for(CustomizeStrategy c : list){
            System.out.println(c.getCreateTime());
        }
    }

    public void testSelectCustomizeStragety(){
        String stragetyId = "loohaze133241";
        CustomizeStrategy c = strategyMapper.selectCustomizeStrategy(stragetyId);
        System.out.println(c.getCreateTime());
    }

    public void testInsertCustomizeStrategy(){
        CustomizeStrategy c = new CustomizeStrategy();

        c.setCreatorId("loohaze");
        c.setStrategyName("133242");
        c.setStrategyId(c.getCreatorId()+c.getStrategyName());
        c.setStrategyExplanation("intro");
        c.setCreateTime(new Date().toString());
        c.setStockPondChosen("142");
        c.setIndexIngredient("21412");
        c.setBlock("124");
        c.setIndustry("15341");
        c.setConcept("1241");
        c.setStStock("1");
        c.setExchange("2");
        c.setRegion("123");
        c.setTransferCycle(12);
        c.setMaxHoldStockNumber(124);
        c.setStraId("1241");
        c.setYearProfit("12312.2");
        c.setStandardProfit("123.2");
        c.setAlpha(213.2);
        c.setBeta(21.2);
        c.setSharpRate(12.3);
        c.setProfitWaveRate(1.22);
        c.setInfoPercent(2.21);
        c.setMaxBack(12.2);
        c.setTurnoverRate(1.2);
        c.setCurrentStandardProfit(2.2);
        c.setCurrentStraProfit(1.2);

        strategyMapper.insertCustomizeStrategy(c);
        session.commit();
    }

    public void testDeleteC(){
        String s = "loohaze133241";
        strategyMapper.deleteCustomizeStrategy(s);
        session.commit();
    }
}
