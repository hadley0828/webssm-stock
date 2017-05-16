package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/5/16.
 */

import com.quantour.ssm.model.User;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {

    private Logger log = Logger.getLogger(StockController.class);

    @Resource
    private StockService stockService;

    @RequestMapping("")
    public String showBoard(HttpServletRequest request, Model model){
        return "dashboard";
    }

    @RequestMapping("/temple")
    public String showBoardtemple(HttpServletRequest request, Model model){
        return "temple";
    }
}
