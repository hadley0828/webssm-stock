package com.quantour.ssm.util;

/**
 * Created by zhangzy on 2017/5/15.
 * 该工具类用来判断某只股票在一段时间内的涨跌信息
 */

public class StockChangeHelper {
    /**
     * 该方法用当天和前一天的收盘价来判断该股票是否涨停
     * @param lastPrice
     * @param nowPrice
     * @return
     */
    public static boolean isLimitUp(double lastPrice,double nowPrice){
        boolean result=false;

        if((nowPrice-lastPrice)*100/lastPrice>=(10-0.01*100/lastPrice)){
            result=true;
        }
        return result;
    }

    /**
     * 该方法用当天和前一天的收盘价来判断该股票是否跌停
     * @param lastPrice
     * @param nowPrice
     * @return
     */
    public static boolean isLimitDown(double lastPrice,double nowPrice){
        boolean result=false;

        if((lastPrice-nowPrice)*100/lastPrice>=(10-0.01*100/lastPrice)){
            result=true;
        }

        return result;
    }

    /**
     * 判断是否涨了percent个百分比
     * @param lastPrice
     * @param nowPrice
     * @param percent
     * @return
     */
    public static boolean isUp(double lastPrice,double nowPrice,double percent){
        boolean result=false;
        double targetPrice=(1+percent)*lastPrice;

        if(nowPrice>targetPrice||nowPrice==targetPrice){
            result=true;
        }
        return result;
    }

    /**
     * 判断是否跌了percent个百分比
     * @param lasePrice
     * @param nowPrice
     * @param percent
     * @return
     */
    public static boolean isdown(double lasePrice,double nowPrice,double percent){
        boolean result=false;

        double targetPrice=(1-percent)*lasePrice;
        if(nowPrice<targetPrice||nowPrice==targetPrice){
            result=true;
        }
        return result;
    }

    /**
     * 开盘和收盘的差价是否涨了percent个百分比
     * @param lastPrice
     * @param nowBPrice
     * @param nowLPrice
     * @param percent
     * @return
     */
    public static boolean isCUp(double lastPrice,double nowBPrice,double nowLPrice,double percent){
        boolean result=false;
        double changePrice=nowBPrice-nowLPrice;
        if(changePrice<-percent*lastPrice){
            result=true;
        }
        return result;
    }

    /**
     * 开盘和收盘的差价是否跌了percent个百分比
     * @param lastPrice
     * @param nowBPrice
     * @param nowLPrice
     * @param percent
     * @return
     */
    public static boolean isCDown(double lastPrice,double nowBPrice,double nowLPrice,double percent){
        boolean result=false;
        double changePrice=nowBPrice-nowLPrice;
        if(changePrice>percent*lastPrice){
            result=true;
        }
        return result;
    }


}
