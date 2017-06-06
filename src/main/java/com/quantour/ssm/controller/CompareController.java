package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

import com.quantour.ssm.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
