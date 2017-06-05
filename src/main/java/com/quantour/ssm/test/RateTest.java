package com.quantour.ssm.test;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.dao.RateMapper;
import com.quantour.ssm.model.CashFlow;
import com.quantour.ssm.model.ProfessionFundFlows;
import com.quantour.ssm.model.SingleStockFundFlows;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/6/4.
 */
public class RateTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    RateMapper rateMapper = session.getMapper(RateMapper.class);
    DayKLineMapper dayKLineMapper=session.getMapper(DayKLineMapper.class);


    public static void main(String[] args) {
        RateTest test = new RateTest();
//        test.testGetOne();
//        test.testGetAll();
//        test.testAllSingleFundFlows();
        test.insertIndustryData();
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

    public void testAllSingleFundFlows(){
        ArrayList<SingleStockFundFlows> singleStockFundFlowsArrayList= (ArrayList<SingleStockFundFlows>) rateMapper.getAllSingleStockFundFlows();

        for(int count=0;count<singleStockFundFlowsArrayList.size();count++){
            System.out.println(singleStockFundFlowsArrayList.get(count).getCode()+" "+singleStockFundFlowsArrayList.get(count).getDate());
        }

        System.out.println(singleStockFundFlowsArrayList.size());
    }

    public void insertIndustryData(){
        ArrayList<String> allIndustryList= (ArrayList<String>) dayKLineMapper.getAllIndustryBlock();

        ArrayList<SingleStockFundFlows> singleStockFundFlowsArrayList= (ArrayList<SingleStockFundFlows>) rateMapper.getAllSingleStockFundFlows();


        HashMap<String,HashMap<String,Double>> allStockFlowMap=new HashMap<String, HashMap<String, Double>>();
        //前一个String存储的是股票编号 后一个String存储的是日期 有的日期可能不存在
        for(int count=0;count<singleStockFundFlowsArrayList.size();count++){
            SingleStockFundFlows singleStockFundFlows=singleStockFundFlowsArrayList.get(count);
            String stockCode=singleStockFundFlows.getCode();
            if(allStockFlowMap.containsKey(stockCode)){
                allStockFlowMap.get(stockCode).put(DateConvert.dateToString(singleStockFundFlows.getDate()),Double.parseDouble(singleStockFundFlows.getInflow()));
            }else{
                HashMap<String,Double> newMap=new HashMap<String, Double>();
                allStockFlowMap.put(stockCode,newMap);
                allStockFlowMap.get(stockCode).put(DateConvert.dateToString(singleStockFundFlows.getDate()),Double.parseDouble(singleStockFundFlows.getInflow()));
            }
        }
        //近二十个交易日的列表
        ArrayList<String> allDateList=new ArrayList<String>(Arrays.asList("2017-05-04","2017-05-05","2017-05-08","2017-05-09","2017-05-10","2017-05-11","2017-05-12","2017-05-15","2017-05-16","2017-05-17","2017-05-18","2017-05-19","2017-05-22","2017-05-23","2017-05-24","2017-05-25","2017-05-26","2017-05-31","2017-06-01","2017-06-02"));

        //对所有的行业进行循环
        for(int count=0;count<allIndustryList.size();count++){
            String industryName=allIndustryList.get(count);
            //当前行业的全部股票
            ArrayList<String> industryAllStock= (ArrayList<String>) dayKLineMapper.getIndustryBlockStockCodes(industryName);

            //以行业名称和日期来作Key值
            for(int index=0;index<20;index++){
                String date=allDateList.get(index);

                int numberOfStockInIndustry=0;
                double changePercent=0.0;
                double totalPercnet=0.0;

                double totalFlow=0.0;
                for(int i=0;i<industryAllStock.size();i++){
                    String code=industryAllStock.get(i);
                    if(allStockFlowMap.containsKey(code)){
                        if(allStockFlowMap.get(code).containsKey(date)){
                            totalFlow=totalFlow+allStockFlowMap.get(code).get(date);
                        }
                    }


                //TODO 处理行业的涨跌幅


                }

                ProfessionFundFlows p = new ProfessionFundFlows();

                p.setIndustry(industryName);
                p.setDate(Date.valueOf(date));
                p.setInflow(String.valueOf(totalFlow));
                p.setChange_percent(0.0);

                rateMapper.insertProfessionFundFlows(p);
            }
        }

        session.commit();
    }

}
