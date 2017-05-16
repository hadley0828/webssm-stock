package com.quantour.ssm.controller;

import com.quantour.ssm.dto.klineDTO;
import com.quantour.ssm.dto.marketDTO;
import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Created by loohaze on 2017/5/11.
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    private Logger log = Logger.getLogger(StockController.class);

    @Resource
    private StockService stockService;

    @RequestMapping("/test")
    public String showDateByCode(HttpServletRequest request, Model model){
//        List<String> data = stockService.getAllDateByCode("000001");
        stockDTO stockdto = stockService.getStockInfo("000001","2017-03-17");
        model.addAttribute("stockdto",stockdto);
        return "test";
    }

    @RequestMapping("/stockkline")
    public String showKline(HttpServletRequest request,Model model){
        ArrayList<klineDTO> klineDTOArrayList=stockService.getKline("000001","2008-11-22","2008-12-20");
        model.addAttribute("klineDTOArrayList",klineDTOArrayList);
        return "stockkline";
    }

    @RequestMapping("/market")
    public String showMarket(HttpServletRequest request,Model model){
        marketDTO marketdto=stockService.getMarketInfo("2008-11-28");
        model.addAttribute("marketdto",marketdto);
        return "market";
    }


}
