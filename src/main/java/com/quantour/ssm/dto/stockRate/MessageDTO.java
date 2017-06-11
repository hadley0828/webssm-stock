package com.quantour.ssm.dto.stockRate;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/6/4.
 * 消息面诊股
 */
public class MessageDTO {
    double MessageScore;    //消息面得分
    double partScore;   //根据下面的指标获得的分数 用来排名
    double defeatPercent;   //击败了多少百分比的股票

    int numberOfMessage;    //消息的数量
    //分配 业绩预测  新闻    distribute  forecast    news
    Message_allocationDTO message_allocationDTO;
    Message_forecastDTO message_forecastDTO;
    ArrayList<Message_NewsDTO> messageNewsDTOArrayList;

    public MessageDTO(){
        super();
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "MessageScore=" + MessageScore +
                ", partScore=" + partScore +
                ", defeatPercent=" + defeatPercent +
                ", numberOfMessage=" + numberOfMessage +
                ", message_allocationDTO=" + message_allocationDTO +
                ", message_forecastDTO=" + message_forecastDTO +
                ", messageNewsDTOArrayList=" + messageNewsDTOArrayList +
                '}';
    }

    public double getMessageScore() {
        return MessageScore;
    }

    public void setMessageScore(double messageScore) {
        MessageScore = messageScore;
    }

    public double getPartScore() {
        return partScore;
    }

    public void setPartScore(double partScore) {
        this.partScore = partScore;
    }

    public double getDefeatPercent() {
        return defeatPercent;
    }

    public void setDefeatPercent(double defeatPercent) {
        this.defeatPercent = defeatPercent;
    }

    public int getNumberOfMessage() {
        return numberOfMessage;
    }

    public void setNumberOfMessage(int numberOfMessage) {
        this.numberOfMessage = numberOfMessage;
    }

    public Message_allocationDTO getMessage_allocationDTO() {
        return message_allocationDTO;
    }

    public void setMessage_allocationDTO(Message_allocationDTO message_allocationDTO) {
        this.message_allocationDTO = message_allocationDTO;
    }

    public Message_forecastDTO getMessage_forecastDTO() {
        return message_forecastDTO;
    }

    public void setMessage_forecastDTO(Message_forecastDTO message_forecastDTO) {
        this.message_forecastDTO = message_forecastDTO;
    }

    public ArrayList<Message_NewsDTO> getMessageNewsDTOArrayList() {
        return messageNewsDTOArrayList;
    }

    public void setMessageNewsDTOArrayList(ArrayList<Message_NewsDTO> messageNewsDTOArrayList) {
        this.messageNewsDTOArrayList = messageNewsDTOArrayList;
    }
}
