package com.quantour.ssm.util;

import com.google.gson.Gson;
import com.quantour.ssm.dto.indexProfitDTO;
import com.quantour.ssm.dto.klineDTO;
import com.quantour.ssm.dto.limitUpAndDownNumsDTO;
import com.quantour.ssm.dto.oneDayProfitDTO;

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

    public static String Stra2LineConvert(indexProfitDTO dto){
        Object[] obj = new Object[dto.getCycleChangeMap().size()+3];

        obj[0] = dto.getPlusCycles();
        obj[1] = dto.getMinusCycles();
        obj[2] = dto.getWinRate();
        int i = 3;
        Iterator iter = dto.getCycleChangeMap().entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            Object[] o = {
              entry.getKey(),entry.getValue()
            };
            obj[i] = o;
            i++;
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

        List<klineDTO> list = new ArrayList<klineDTO>();

        klineDTO k = new klineDTO();
        k.setId("000001");
        k.setName("平安银行");
        k.setDate("2007-11-22");
        k.setOpenPrice(9.667);
        k.setClosePrice(9.435);
        k.setHighPrice(9.995);
        k.setLowPrice(9.425);

        klineDTO k2 = new klineDTO();
        k2.setId("000001");
        k2.setName("平安银行");
        k2.setDate("2007-11-23");
        k2.setOpenPrice(9.425);
        k2.setClosePrice(9.717);
        k2.setHighPrice(9.798);
        k2.setLowPrice(9.404);

        list.add(k);
        list.add(k2);


        String result = JsonConvert.kLineConvert(list);
        System.out.println(result);

//        System.out.println(test.dateConvert("2018-01-01"));
    }
}
