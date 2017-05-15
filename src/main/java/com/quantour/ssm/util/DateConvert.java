package com.quantour.ssm.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by zhangzy on 2017/5/14.
 * 该工具类用来处理不同格式的日期转换以及获得日期前后的日期
 */
public class DateConvert {

    //STRING到日期
    public static java.sql.Date stringToDate(String dateStr) {
        return java.sql.Date.valueOf(dateStr);
    }

    //日期到STRING
    public static String dateToString(java.sql.Date datee) {
        return datee.toString();
    }

    //获得全部日期列表中前n个日期
    public static String getLastNDate(ArrayList<String> allDateList, String date, int n) {
        String result = "NotExist";

        int index = allDateList.indexOf(date);
        if (index == -1 || index < n) {

        } else {
            result = allDateList.get(index - n);
        }


        return result;
    }

    /*
    获得sql的date类向前向后的String 日期
     */
    public static String getPreDoneScore(String holdDateStr,int n) {
        Date holdDate=DateConvert.stringToDate(holdDateStr);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(holdDate);
        // calendar的time转成java.util.Date格式日期
        java.util.Date utilDate = (java.util.Date) calendar.getTime();
        calendar.add(calendar.DATE, n);
        utilDate = (java.util.Date) calendar.getTime();
        //java.util.Date日期转换成转成java.sql.Date格式
        Date newDate = new Date(utilDate.getTime());

        return DateConvert.dateToString(newDate);
    }

    public static String getRealStartDate(String sDate,ArrayList<String> allDateList){
        String result=sDate;
        if(allDateList.contains(sDate)){
            result=sDate;
        }else{
            for(int count=1;count<100;count++){
                String currentDate=DateConvert.getPreDoneScore(sDate,count);
                if(allDateList.contains(currentDate)){
                    result=currentDate;
                    break;
                }
            }
        }
        return result;
    }

    public static String getRealEndDate(String lDate,ArrayList<String> allDateList){
        String result=lDate;

        if(allDateList.contains(lDate)){
            result=lDate;
        }else{
            for(int count=1;count<100;count++){
                String currentDate=DateConvert.getPreDoneScore(lDate,-count);
                if(allDateList.contains(currentDate)){
                    result=currentDate;
                    break;
                }
            }
        }

        return result;
    }

}