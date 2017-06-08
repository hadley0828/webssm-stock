package com.quantour.ssm.controller;


import com.quantour.ssm.dto.klineDTO;
import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.util.JsonConvert;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ModelAndView showStock(HttpServletRequest request, ModelAndView model){
        stockDTO s = stockService.getStockInfo("000001","2017-05-23");

        model.setViewName("stock");
        System.out.println(s.getId());
        model.addObject("stock",s);


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

}
