package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/10.
 */
import com.quantour.ssm.service.StockService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/strategy")
public class StrategyController {
    private Logger log = Logger.getLogger(DashBoardController.class);

    @Resource
    private StockService stockService;

    @RequestMapping("")
    public String showStrategy(HttpServletRequest request, Model model){
        return "strategy";
    }

    @RequestMapping("/createStrategy")
    public String createStrategy(HttpServletRequest request, Model model){
        return "createStrategy";
    }
}
