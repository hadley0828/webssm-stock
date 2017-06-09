package com.quantour.ssm.test;

import com.quantour.ssm.dao.DayKLineMapper;
import com.quantour.ssm.model.DayKLine;
import com.quantour.ssm.model.DayKLineKey;
import com.quantour.ssm.model.StockBasicInfo;
import com.quantour.ssm.util.CodeIndustryMap;
import com.quantour.ssm.util.DateConvert;
import com.quantour.ssm.util.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.util.*;

/**
 * Created by loohaze on 2017/5/12.
 */
public class DayKLineImplTest {

    SqlSession session = FKSqlSessionFactory.getSqlSession();
    DayKLineMapper dayKLineMapper = session.getMapper(DayKLineMapper.class);


    public static void main(String[] args) {


        DayKLineImplTest test = new DayKLineImplTest();

//        test.testInsertUserStock();
//        test.testGetUserAllStock();
//        test.testDeleteUserStock();
//        test.testGetAllIndustryAndCode();
//        test.printTwoIndustryMap();
//        test.testGetAllDateByCode();
//        test.testGetOneDayKLine();
//        test.testGetTimesDayKLines();
//        test.testGetOneDayDayKLines();
//        test.testGetYesterdayDayKLines();
//        test.testGetAllDayKLinesByCode();
//        test.testGetAllStockInfos();
//        test.testGetOneBlockInfo();

//        test.testGetYesterdayDayKline();
        test.testGetTimesBlockInfo();
//        test.testGetAllConceptBlock();
//        test.testGetAllAreaBlock();
//        test.testGetAllIndustryBlock();
//        try {
//            long startTime = System.currentTimeMillis();
//            test.testGetYesterdayDayKLines();
//            long endTime = System.currentTimeMillis();
//            System.out.println((endTime - startTime) + "ms");
//
//            long startTime2 = System.currentTimeMillis();
//            test.testGetOneDayDayKLines();
//            long endTime2 = System.currentTimeMillis();
//            System.out.println((endTime2 - startTime2) + "ms");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        test.testGetBlockAllDate();
//        test.testGetConceptBlockStockCodes();
//        test.testGetIndustryBlockStockCodes();
//        test.testGetAreaBlockStockCodes();
//        test.testGetAllCodeByBlock();
//        test.testGetBlockByStock();
//        test.testGetOneStockInfo();
//        test.testGetStocksByTimes();
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
        dayKLineKey.setStockCode("000018");
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

        HashMap<String,Object> map1 = new HashMap<String, Object>();
        map1.put("code","000002");
        map1.put("start",Date.valueOf("2017-01-01"));
        map1.put("end", Date.valueOf("2017-01-31"));

        List<DayKLine> list = dayKLineMapper.getTimesDayKLines(map);
        List<DayKLine> list1 = dayKLineMapper.getTimesDayKLines(map1);
        for(DayKLine dayKLine : list){
            printDayKLine(dayKLine);
        }

        for(DayKLine dayKLine : list1){
            printDayKLine(dayKLine);
        }
    }

    /**
     * pass
     */
    public void testGetOneDayDayKLines(){
        Date date = Date.valueOf("2017-03-13");
        List<DayKLine> list = dayKLineMapper.getOneDayDayKLines(date);
//        for(DayKLine dayKLine : list){
//            printDayKLine(dayKLine);
//        }
    }


    public void testGetYesterdayDayKline(){
        DayKLineKey dayKLineKey = new DayKLineKey();
        dayKLineKey.setStockCode("000001");
        dayKLineKey.setStockDate(Date.valueOf("2017-03-24"));

        DayKLine dayKLine = dayKLineMapper.getYesterdayDayKLine(dayKLineKey);
    }


    /**
     * pass 效率同上 略低
     */
    public void testGetYesterdayDayKLines(){
        Date date = Date.valueOf("2017-03-24");
        List<DayKLine> list = dayKLineMapper.getYesterdayDayKLines(date);
//        for(DayKLine dayKLine : list){
//            printDayKLine(dayKLine);
//        }
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

    public void testGetOneStockInfo(){
        String code = "000001";
        StockBasicInfo stockBasicInfo = dayKLineMapper.getOneStockInfo(code);
        System.out.print(stockBasicInfo.getCode() + " ");
        System.out.print(stockBasicInfo.getName() + " ");
        System.out.print(stockBasicInfo.getIndustry()+ " ");
        System.out.println(stockBasicInfo.getArea());
    }
    /**
     * pass
     */
    public void testGetAllStockInfos(){
        List<StockBasicInfo> list = dayKLineMapper.getAllStockInfos();
//        try {
//            FileWriter fileWriter = new FileWriter("codelist");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            for(StockBasicInfo s : list){
//                bufferedWriter.write(s.getCode());
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        String block = "sh000300";
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
        dayKLineKey.setStockCode("sh000300");
        dayKLineKey.setStockDate(Date.valueOf("2017-03-21"));
        DayKLine dayKLine = dayKLineMapper.getOneBlockInfo(dayKLineKey);
        printDayKLine(dayKLine);

    }

    /**
     * pass
     */
    public void testGetTimesBlockInfo(){
        HashMap<String,Object> map = new HashMap<String, Object>();

        map.put("block", "sh000001");
        map.put("start",Date.valueOf("2017-01-01"));
        map.put("end",Date.valueOf("2017-01-31"));

        List<DayKLine> list = dayKLineMapper.getTimesBlockInfo(map);

        for(DayKLine dayKLine : list){
            printDayKLine(dayKLine);
        }
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

    /**
     * pass
     */
    public void testGetAllCodeByBlock(){
        List<String> gemlist = dayKLineMapper.getAllCodeByGemBlock();
        List<String> smelist = dayKLineMapper.getAllCodeBySmeBlock();
        List<String> hs300list= dayKLineMapper.getAllCodeByHs300Block();
        List<String> sz50list = dayKLineMapper.getAllCodeBySz50Block();
        List<String> zz500list = dayKLineMapper.getAllCodeByZz500Block();

        for(String s : zz500list){
            System.out.println(s);
        }
    }

    /**
     * pass
     */
    public void testGetBlockByStock(){
        String code = "000001";

        List<String> list1 = dayKLineMapper.getIndustryByStock(code);
        List<String> list2 = dayKLineMapper.getAreaByStock(code);
        List<String> list3 = dayKLineMapper.getConceptByStock(code);


        System.out.println("行业:");
        for(String s : list1){
            System.out.println(s);
        }
        System.out.println("地域:");
        for(String s : list2){
            System.out.println(s);
        }
        System.out.println("概念板块:");
        for(String s : list3){
            System.out.println(s);
        }
    }

    public void testGetStocksByTimes(){
        HashMap<String,Date> map = new HashMap<String, Date>();
        map.put("start",Date.valueOf("2014-01-01"));
        map.put("end",Date.valueOf("2017-02-01"));
        List<DayKLine> dayKLines = dayKLineMapper.getStocksByTimes(map);
//        for(DayKLine dayKLine : dayKLines){
//            printDayKLine(dayKLine);
//        }
    }

    /**
     * 当天存在信息的股票支数会比行业中的全部股票要少
     */
    public void testGetAllIndustryAndCode(){
        HashMap<String,String> map = dayKLineMapper.getAllIndustryAndCode(new CodeIndustryMap("code","industry"));

//        System.out.println(map.keySet());
        int number=0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            number++;
        }

        System.out.println(number);

        ArrayList<DayKLine> oneDayAllStock= (ArrayList<DayKLine>) dayKLineMapper.getOneDayDayKLines(DateConvert.stringToDate("2017-03-06"));
        System.out.println(oneDayAllStock.size());
    }

    public void test(){
        List<String> codeList = new ArrayList<String>();
        List<StockBasicInfo> list = dayKLineMapper.getAllStockInfos();
        List<DayKLine> result = new ArrayList<DayKLine>();
        for(StockBasicInfo s : list){
            codeList.add(s.getCode());
        }

        for(String s : codeList){
            DayKLineKey dayKLineKey = new DayKLineKey();
            dayKLineKey.setStockCode(s);
            dayKLineKey.setStockDate(Date.valueOf("2017-03-17"));
            result.add(dayKLineMapper.getYesterdayDayKLine(dayKLineKey));
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

    public void testGetUserAllStock(){

        ArrayList<String> list = dayKLineMapper.getUserAllStock("loohaze");

        for(String s : list){
            System.out.println(s);
        }
    }

    public void testInsertUserStock(){
        String userid = "loohaze";
        String stockCode = "000001";

        dayKLineMapper.insertOneOptionalStock(userid,stockCode);

        session.commit();
    }

    public void testDeleteUserStock(){
        String userid = "loohaze";
        String stockCode = "000001";

        dayKLineMapper.deleteOneOptionalStock(userid,stockCode);

        session.commit();
    }

    public void printTwoIndustryMap(){
        HashMap<String,String> codeToIndustryMap=dayKLineMapper.getAllIndustryAndCode(new CodeIndustryMap("code","industry"));
        //      行业名称 股票编号的set
        HashMap<String,HashSet<String>> industryToCodeMap=new HashMap<String, HashSet<String>>();

        for (Map.Entry<String, String> entry : codeToIndustryMap.entrySet()) {
            String oneCode=entry.getKey();
            String oneIndustry=entry.getValue();

            if(industryToCodeMap.containsKey(oneIndustry)){
                industryToCodeMap.get(oneIndustry).add(oneCode);
            }else {
                HashSet<String> newSet=new HashSet<String>();
                industryToCodeMap.put(oneIndustry,newSet);
                industryToCodeMap.get(oneIndustry).add(oneCode);
            }
        }

        for (Map.Entry<String, HashSet<String>> entry : industryToCodeMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue().size());
            HashSet<String> set=entry.getValue();

            for(String str:set){
                System.out.println(str);
            }
        }
    }
}
