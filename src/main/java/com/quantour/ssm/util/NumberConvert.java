package com.quantour.ssm.util;

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


    public static void main(String[] args) {
        System.out.println(doubleToBiggerInt(1.5));
        System.out.println(doubleToSmallerInt(1.5));

    }

}
