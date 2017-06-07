package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

import com.google.gson.Gson;
import com.quantour.ssm.model.ResponseObj;
import com.quantour.ssm.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public String doCompare(String id_List){
        int j = 0;

        System.out.println(id_List);

        return new Gson().toJson(id_List);
    }
}
