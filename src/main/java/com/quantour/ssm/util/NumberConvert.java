package com.quantour.ssm.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangzy on 2017/6/7.
 */
public class NumberConvert {

    /**
     * 把一个double类型的小数向上取整
     * @param d
     * @return
     */
    public static int doubleToBiggerInt(double d){
        return (int) Math.ceil(d);
    }

    public static int doubleToSmallerInt(double d){
        return (int) Math.floor(d);
    }

    public static String doubleToPercentageString(double d){
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(3);
        num.setMaximumFractionDigits(2);
        return String.valueOf(num.format(d));
    }


    public static void main(String[] args) {
//        System.out.println(doubleToBiggerInt(1.5));
//        System.out.println(doubleToSmallerInt(1.5));
//
//        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now=new Date();
//        String rq=myFmt1.format(now);
//        System.out.println(rq);
        System.out.println(doubleToPercentageString(0.12345667));
    }

}
