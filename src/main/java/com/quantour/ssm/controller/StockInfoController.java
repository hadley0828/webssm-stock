package com.quantour.ssm.controller;


import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public String showStock(HttpServletRequest request, Model model){
        stockDTO s = stockService.getStockInfo("000002","2017-05-23");
        model.addAttribute("stock",s);

        return "stock";
    }

}
