package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/4.
 * 消息面诊股
 */
public class MessageDTO {
    double MessageScore;    //消息面得分
    double partScore;   //根据下面的指标获得的分数 用来排名
    double defeatPercent;   //击败了多少百分比的股票

}
