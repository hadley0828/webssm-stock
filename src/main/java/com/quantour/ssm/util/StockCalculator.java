package com.quantour.ssm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzy on 2017/5/14.
 */

//用来计算涨跌幅 对数收益率和对数收益方差
public class StockCalculator {

    /**根据当天复权收盘价和前一天的复权收盘价得到涨跌幅 格式为四位小数
     * @param lastPrice
     * @param nowPrice
     * @return
     */
    public static double getUplift(double lastPrice,double nowPrice){
        double result=0.0000;
        result=(nowPrice-lastPrice)/lastPrice;
        String str=String.format("%.4f", result);
        result=Double.parseDouble(str);
        return result;

		/*stockCalculate sc=new stockCalculate();
		System.out.println(sc.getUplift(11.03, 11.16));*/
    }


    /**根据当天复权收盘价和前一天的复权收盘价来获得当天的对数收益率 小数点没有保留
     * @param lastPrice
     * @param nowPrice
     * @return
     */
    public static double getLogYield(double lastPrice,double nowPrice){
        double result=0.0000;
        result=Math.log(nowPrice/lastPrice);   //Ln
        return result;
        //stockCalculate sc=new stockCalculate();
        //System.out.println(sc.getLogYield(11.03, 11.16));
    }


    /**根据一个时间段内的复权收盘价列表来计算对数收益方差  此处的方差除以N-1
     * @param list 是一段时间的收盘价
     * @return
     */
    public static double getLogVariance(List<Double> list){
        double result=0.0000;
        ArrayList<Double> logList=new ArrayList<Double>();

        for(int count=0;count<list.size()-1;count++){
            double logYield=StockCalculator.getLogYield(list.get(count), list.get(count+1));
            logList.add(logYield);
        }

        double total=0.0000;
        double average=0.0000;
        double sqrTotal=0.0000;

        for(int count=0;count<logList.size();count++){
            total=total+logList.get(count);
        }
        average=total/logList.size();


        for(int count=0;count<logList.size();count++){
            sqrTotal=sqrTotal+Math.pow(logList.get(count)-average, 2);
        }
        result=sqrTotal/(logList.size()-1);

        return result;

		/*stockCalculate sc=new stockCalculate();
		List<Double> a=new ArrayList<Double>();
		a.add(1.01);
		a.add(1.10);
		a.add(1.56);
		a.add(2.34);
		System.out.println(sc.getLogVariance(a)); */
    }


    public static double getIncrease(double beforePrice,double nowPrice){
        double result=0.0;
//        double changePrice=nowPrice-beforePrice;
//        BigDecimal b1 = new BigDecimal(Double.toString(changePrice));
//        BigDecimal b2 = new BigDecimal(Double.toString(beforePrice));
//
//        result=b1.divide(b2).doubleValue();

        result=(nowPrice-beforePrice)/beforePrice;

        return result;
    }

    public static double getAverage(ArrayList<Double> doubleList){
        double result=0.0;
        double total=0.0;
        for(int count=0;count<doubleList.size();count++){
            total=total+doubleList.get(count);
        }
        result=total/doubleList.size();
        return result;
    }

    //方差 使用应用样本统计量
    public static double getVariance(ArrayList<Double> doubleList){
        double result=0.0;
        double average=getAverage(doubleList);
        double total=0.0;
        for(int count=0;count<doubleList.size();count++){
            total=total+Math.pow(doubleList.get(count)-average,2.0);
        }
        result=total/(doubleList.size()-1);
        return result;
    }


    //协方差
    public static double getCovariance(ArrayList<Double> firstList,ArrayList<Double> secondList){
        double result=0.0;
        double firstAverage=getAverage(firstList);
        double secondAverage=getAverage(secondList);
        ArrayList<Double> numberList=new ArrayList<Double>();
        for(int count=0;count<firstList.size();count++){
            numberList.add((firstList.get(count)-firstAverage)*(secondList.get(count)-secondAverage));
        }
        result=getAverage(numberList);

        return result;
    }

    public static double getOneYearRate(){
        double result=0.0175;
        return result;
    }

}
