package com.quantour.ssm.test;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.DayKLineKey;
import com.quantour.ssm.model.StockBasicInfo;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/5/12.
 */
public class DayKLineImplTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    DayKLineMapper dayKLineMapper = session.getMapper(DayKLineMapper.class);


    public static void main(String[] args) {


        DayKLineImplTest test = new DayKLineImplTest();
//        test.testGetAllDateByCode();
//        test.testGetOneDayKLine();
        test.testGetTimesDayKLines();
//        test.testGetOneDayDayKLines();
//        test.testGetYesterdayDayKLine();
//        test.testGetYesterdayDayKLines();
//        test.testGetAllDayKLinesByCode();
//        test.testGetAllStockInfos();
//        test.testGetOneBlockInfo();
//        test.testGetAllConceptBlock();
//        test.testGetAllAreaBlock();
//        test.testGetAllIndustryBlock();
//        test.testGetMarketDates();
//        test.testGetBlockAllDate();
//        test.testGetConceptBlockStockCodes();
//        test.testGetIndustryBlockStockCodes();
//        test.testGetAreaBlockStockCodes();

    }

    /**
     * pass
     */
    public void testGetAllDateByCode(){
        String code = "000001";

        List<String> list = dayKLineMapper.getAllDateByCode(code);

        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetOneDayKLine(){
        DayKLineKey dayKLineKey = new DayKLineKey();
        dayKLineKey.setStockCode("000001");
        dayKLineKey.setStockDate(Date.valueOf("2017-03-21"));
        DayKLine dayKLine = dayKLineMapper.getOneDayKLine(dayKLineKey);
        printDayKLine(dayKLine);
    }

    /**
     * pass
     */
    public void testGetTimesDayKLines(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("code","000001");
        map.put("start",Date.valueOf("2017-01-01"));
        map.put("end",Date.valueOf("2017-01-31"));

        List<DayKLine> list = dayKLineMapper.getTimesDayKLines(map);
        for(DayKLine dayKLine : list){
            printDayKLine(dayKLine);
        }
    }

    /**
     * pass
     */
    public void testGetOneDayDayKLines(){
        Date date = Date.valueOf("2017-03-13");
        List<DayKLine> list = dayKLineMapper.getOneDayDayKLines(date);
        for(DayKLine dayKLine : list){
            printDayKLine(dayKLine);
        }
    }




    /**
     * pass 效率同上 略低
     */
    public void testGetYesterdayDayKLines(){
        Date date = Date.valueOf("2017-03-24");
        List<DayKLine> list = dayKLineMapper.getYesterdayDayKLines(date);
        for(DayKLine dayKLine : list){
            printDayKLine(dayKLine);
        }
    }

    /**
     * pass
     */
    public void testGetAllDayKLinesByCode(){
        String code = "000001";
        List<DayKLine> list = dayKLineMapper.getAllDayKLinesByCode(code);
        for(DayKLine dayKLine : list){
            printDayKLine(dayKLine);
        }
    }

    /**
     * pass
     */
    public void testGetAllStockInfos(){
        List<StockBasicInfo> list = dayKLineMapper.getAllStockInfos();
        for(StockBasicInfo stockBasicInfo : list){
            System.out.print(stockBasicInfo.getCode() + " ");
            System.out.print(stockBasicInfo.getName() + " ");
            System.out.print(stockBasicInfo.getIndustry()+ " ");
            System.out.println(stockBasicInfo.getArea());
        }
    }

    /**
     * pass
     */
    public void testGetMarketDates(){
        List<Date> list = dayKLineMapper.getMarketDates();

        for(Date date : list){
            System.out.println(date);
        }
    }

    /**
     * pass
     */
    public void testGetBlockAllDate(){
        String block = "hs300";
        List<Date> list = dayKLineMapper.getBlockAllDate(block);
        for(Date date : list){
            System.out.println(date);
        }
    }

    public void testGetLastDaysDayKLineInfo(){

    }

    /**
     * pass
     */
    public void testGetOneBlockInfo(){
        DayKLineKey dayKLineKey = new DayKLineKey();
        dayKLineKey.setStockCode("hs300");
        dayKLineKey.setStockDate(Date.valueOf("2017-03-21"));
        DayKLine dayKLine = dayKLineMapper.getOneDayKLine(dayKLineKey);
        printDayKLine(dayKLine);

    }

    /**
     * pass
     */
    public void testGetAllConceptBlock(){
        List<String> list = dayKLineMapper.getAllConceptBlock();
        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetAllIndustryBlock(){
        List<String> list = dayKLineMapper.getAllIndustryBlock();
        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetAllAreaBlock(){
        List<String> list = dayKLineMapper.getAllAreaBlock();
        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetConceptBlockStockCodes(){
        String blockName = "保险重仓";
        List<String> list = dayKLineMapper.getConceptBlockStockCodes(blockName);

        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetIndustryBlockStockCodes(){
        String blockName = "金融行业";
        List<String> list = dayKLineMapper.getIndustryBlockStockCodes(blockName);

        for(String s : list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetAreaBlockStockCodes(){
        String blockName = "上海";
        List<String> list = dayKLineMapper.getAreaBlockStockCodes(blockName);

        for(String s : list){
            System.out.println(s);
        }
    }


    public static void printDayKLine(DayKLine dayKLine){
        System.out.print(dayKLine.getStockCode()+ " ");
        System.out.print(dayKLine.getStockDate()+ " ");
        System.out.print(dayKLine.getVolume()+ " ");
        System.out.print(dayKLine.getOpenPrice()+ " ");
        System.out.print(dayKLine.getClosePrice()+ " ");
        System.out.print(dayKLine.getHighPrice()+ " ");
        System.out.println(dayKLine.getLowPrice()+ " ");


    }
}
