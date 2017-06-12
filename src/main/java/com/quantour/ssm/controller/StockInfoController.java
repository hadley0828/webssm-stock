package com.quantour.ssm.controller;


import com.quantour.ssm.dto.*;
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


    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ModelAndView showStock(
            @RequestParam(value = "id",required = false) String user_id,
            @RequestParam(value = "stockCode",required = false) String stock_code,
            HttpServletRequest request,
            ModelAndView model){
        stock_code = stock_code.substring(0,6);

        stockDTO s = stockService.getStockInfo(stock_code,"2017-05-23");

        model.setViewName("stock");
        System.out.println(s.getId());
        model.addObject("stock",s);
        try{
            System.out.println(";"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);

            model.setViewName("user");
            model.addObject("user",user);
        }catch (Exception e){
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


}
