package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

import com.google.gson.Gson;
import com.quantour.ssm.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/compare")
public class CompareController {
    private Logger log = Logger.getLogger(DashBoardController.class);


    @Resource
    private StockService stockService;

    @RequestMapping("")
    public String showCompare(HttpServletRequest request, Model model){
        return "compare";
    }

    @RequestMapping(value="/doCompare",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doCompare(String codename1, String codename2){

        String codeid1 = stockService.getStockCodeByName(codename1);
        String codeid2 = stockService.getStockCodeByName(codename2);

        HashMap<String,String> map = new HashMap<String, String>();
        map.put("codeid1",codeid1);
        map.put("codeid2",codeid2);

        return new Gson().toJson(map);
    }
}
