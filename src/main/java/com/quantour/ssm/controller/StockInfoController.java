package com.quantour.ssm.controller;


import com.google.gson.Gson;
import com.quantour.ssm.dto.*;
import com.quantour.ssm.dto.UserHistory.StockRecordDTO;
import com.quantour.ssm.service.HistoryService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import com.quantour.ssm.util.JsonConvert;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by loohaze on 2017/6/6.
 */
@Controller
@RequestMapping("/stockinfo")
public class StockInfoController {


    private Logger log = Logger.getLogger(StockInfoController.class);

    @Resource
    private StockService stockService;
    @Resource
    private UserService userService;
    @Resource
    private HistoryService historyService;


    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ModelAndView  showStock(
            @RequestParam(value = "id",required = false) String user_id,
            @RequestParam(value = "stockCode",required = false) String stock_code,
            HttpServletRequest request,
            ModelAndView model){
        stock_code = stock_code.substring(0,6);

        String date="2017-06-01";

        stockDTO s = stockService.getStockInfo(stock_code,date);
        NextDateStockDTO ns = stockService.getNextDayStockInfo(stock_code,date);
        List<RankDTO> hot_list = stockService.getTopNStockByDays(5,date,1);

        model.setViewName("stock");
        System.out.println(s.getId());
        model.addObject("stock",s);
        model.addObject("nextDay",ns);
        model.addObject("hot_list",hot_list);
        try{
//            System.out.println(";"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);

//            model.setViewName("user");
            model.addObject("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            ArrayList<String> allOptionalStock=stockService.getUserAllOptionalStock(user_id);
            ArrayList<stockDTO> optionalStockList=new ArrayList<stockDTO>();
            if(allOptionalStock.size()!=0){
                optionalStockList=stockService.getSeveralStockInfo(allOptionalStock,date);

            }
            model.addObject("optionalStockList",optionalStockList);


        }catch (Exception e){
            e.printStackTrace();
        }

        StockRecordDTO dto = new StockRecordDTO();

        SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now=new java.util.Date();
        String rq=myFmt1.format(now);

        dto.setDate_time(rq);
        dto.setCode_id(stock_code);
        dto.setUser_id(user_id);

        try{
            historyService.createNewStockRecord(dto);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            ArrayList<StockRecordDTO> allRecordStock=historyService.getUserAllStockRecord(user_id,date);

            model.addObject("allRecordStock",allRecordStock);


        }catch(Exception e){
            e.printStackTrace();
        }

        return model;
    }


    @RequestMapping(value = "/getDayKLineInfo")
    @ResponseBody
    public String getDayKLineInfo(String codeid, String sdate, String ldate){

//        System.out.println(codeid);
//        System.out.println(sdate);
//        System.out.println(ldate);
        List<klineDTO> list = stockService.getKline(codeid, sdate, ldate);

        String data = JsonConvert.kLineConvert(list);
//        System.out.println(data);
        return data;
    }

    @RequestMapping(value = "/getShDayKLine")
    @ResponseBody
    public String getSH000001KLine(String codeid, String sdate, String ldate){
        List<klineDTO> list = stockService.getBlockKline(codeid,sdate,ldate);

        String data = JsonConvert.kLineConvert(list);

        return data;
    }

    @RequestMapping(value = "/getLimit")
    @ResponseBody
    public String getLimitInfo(String date){
        List<limitUpAndDownNumsDTO> list = stockService.getLimitUpAndDownNumber(date);

        String data = JsonConvert.limitLineConvert(list);

        return data;
    }

    @RequestMapping(value = "/getUpDown")
    @ResponseBody
    public String getUpDownInfo(String date){
        marketDTO market= stockService.getMarketInfo(date);

        String data = JsonConvert.upDownLineConvert(market.getChangePercentNumberList());
        return data;
    }

    @RequestMapping(value = "/checkStock")
    @ResponseBody
    public String checkStock(String stock_code){
        String date = "2017-06-01";
        stock_code = stock_code.substring(0,6);
        if(!stockService.isDateValid(stock_code,date)){
            return new Gson().toJson("2017-06-01");
        }else{
            return new Gson().toJson("pass");
        }
    }

    @RequestMapping(value = "/addStock")
    @ResponseBody
    public String addMineStock(String user_id,String code_id){

        boolean bool = false;
        try{
            bool = stockService.addOneNewOptionalStock(user_id,code_id);
        }catch (Exception e){
            e.printStackTrace();
        }

        HashMap<String,String> map = new HashMap<String, String>();
        if(bool){
            map.put("result","success");
        }else{
            map.put("result","false");
        }

        return new Gson().toJson(map);
    }
}
