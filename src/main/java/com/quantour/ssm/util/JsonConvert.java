package com.quantour.ssm.util;

import com.google.gson.Gson;
import com.quantour.ssm.dto.indexProfitDTO;
import com.quantour.ssm.dto.klineDTO;
import com.quantour.ssm.dto.limitUpAndDownNumsDTO;
import com.quantour.ssm.dto.oneDayProfitDTO;
import com.quantour.ssm.dto.stockRate.DateAndChange;
import com.quantour.ssm.dto.stockRate.FundFlowMapDTO;
import com.quantour.ssm.dto.stockRate.Technical_mapDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/6/7.
 */
public class JsonConvert {


    public JsonConvert(){

    }

    public static String kLineConvert(List<klineDTO> list){
        String result = "";

        Object[] obj = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            klineDTO dto = list.get(i);
            Object[] o = {
                    dateConvert(dto.getDate()),dto.getOpenPrice(),dto.getClosePrice(),
                    dto.getLowPrice(),dto.getHighPrice()
            };

            obj[i] = o;
        }

        return new Gson().toJson(obj);
    }


    public static String logLineConvert(List<Double> data, List<String>date){
        String result = "";

        Object[] obj = new Object[data.size()];
        for(int i = 0; i < data.size(); i++){
            Object[] o = {
                    dateConvert(date.get(i)),data.get(i)
            };
            obj[i] = o;
        }

        return new Gson().toJson(obj);
    }

    public static String limitLineConvert(List<limitUpAndDownNumsDTO> list){

        Object[] obj = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            Object[] o = {
              dateConvert(list.get(i).getDate()),list.get(i).getUpNumber(),list.get(i).getDownNumber()
            };
            obj[i] = o;
        }

        return new Gson().toJson(obj);
    }

    public static String upDownLineConvert(List<Integer> list){
        Object[] obj = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            obj[i] = list.get(i);
        }

        return new Gson().toJson(obj);
    }

    public static String Stra1LineConvert(List<oneDayProfitDTO> list){
        Object[] obj = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            Object[] o = {
                    dateConvert(list.get(i).getDate()),list.get(i).getStandardProfit(),list.get(i).getStraProfit()
            };
            obj[i] = o;
        }

        return new Gson().toJson(obj);
    }

    public static String markerlineConvert(ArrayList<Technical_mapDTO> list){
        Object[] obj = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            Object[] o = {
                    dateConvert(list.get(i).getDate()),list.get(i).getBlockChangePercent(),list.get(i).getStockChangePercent()
            };
            obj[i] = o;
        }
        return new Gson().toJson(obj);
    }

    public static String capitalLineConvert(ArrayList<FundFlowMapDTO> list){
        Object[] obj = new Object[list.size()];

        for(int i = 0; i < list.size(); i++){
            Object[] o = {
                   dateConvert(list.get(i).getDate()),list.get(i).getSingleFlow(),list.get(i).getIndustryAverageFlow()
            };
            obj[i] = o;
        }
        return new Gson().toJson(obj);
    }

    public static String ChangeListConvert(ArrayList<DateAndChange> list){
//        for(DateAndChange s : list){
//            System.out.println(s.getDate());
//            System.out.println(s.getIndustryChangePercent());
//            System.out.println(s.getBlockChangePercent());
//        }


        Object[] obj = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            Object[] o = {
                    dateConvert(list.get(i).getDate()),list.get(i).getIndustryChangePercent(),list.get(i).getBlockChangePercent()
            };
            obj[i] = o;
        }
        return new Gson().toJson(obj);
    }

    public static String Stra2LineConvert(indexProfitDTO dto){
        Object[] obj = new Object[dto.getCycleChangeMap().size()+3];

        List<Double> keylist = new ArrayList<Double>();
        List<Integer> valuelist = new ArrayList<Integer>();

        obj[0] = dto.getPlusCycles();
        obj[1] = dto.getMinusCycles();
        obj[2] = dto.getWinRate();
        Iterator iter = dto.getCycleChangeMap().entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            keylist.add(Double.valueOf(entry.getKey().toString()));
            valuelist.add(Integer.valueOf(entry.getValue().toString()) );
//            Object[] o = {
//              entry.getKey(),entry.getValue()
//            };
//            obj[i] = o;
        }
        for(int i = 0; i < keylist.size() -1; i++){
            for(int j = 0; j < keylist.size() - 1- i; j++){
                if(keylist.get(j) > keylist.get(j+1)){
                    double temp = keylist.get(j);
                    keylist.set(j,keylist.get(j+1));
                    keylist.set(j+1,temp);

                    int temp1 = valuelist.get(j);
                    valuelist.set(j,valuelist.get(j+1));
                    valuelist.set(j+1,temp1);
                }
            }
        }

        for(int i = 3; i < obj.length; i++){
            Object[] o = {
              keylist.get(i-3),valuelist.get(i-3)
            };
            obj[i] = o;
        }

        System.out.println(new Gson().toJson(obj));

        return new Gson().toJson(obj);
    }



    private static String[] kLineConvert(klineDTO dto){
        String[] data = new String[5];
        data[0] = dateConvert(dto.getDate());
        data[1] = String.valueOf(dto.getOpenPrice());
        data[2] = String.valueOf(dto.getClosePrice());
        data[3] = String.valueOf(dto.getLowPrice());
        data[4] = String.valueOf(dto.getHighPrice());
        return data;
    }


    private static String dateConvert(String date){

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];

        if(month.charAt(0) == '0'){
            month = month.substring(1);
        }
        if(day.charAt(0) == '0'){
            day = day.substring(1);
        }

        return year+"/"+month+"/"+day;
    }

    public static void main(String[] args) {
        JsonConvert test = new JsonConvert();


    }
}
