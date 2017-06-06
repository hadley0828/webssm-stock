package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/5/16.
 */

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
@RequestMapping("/dashboard")
public class DashBoardController {

    private Logger log = Logger.getLogger(DashBoardController.class);

    @Resource
    private StockService stockService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showBoard(HttpServletRequest request, Model model){
        return "dashboard";
    }


//    @RequestMapping("/temple")
//    public String showBoardtemple(HttpServletRequest request, Model model){
//        return "temple";
//    }

//    @RequestMapping("/temple.form")
//    public String testPrint(HttpServletRequest request,Model model){
//        String name = request.getParameter("name");
//        model.addAttribute("name",name);
//        return "temple";
//    }

    @RequestMapping("/login")
    public String doLogin(HttpServletRequest request,Model model){
        return "userLogin";
    }

    @RequestMapping("/regist")
    public String doRegist(HttpServletRequest request,Model model){
        return "userRegist";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    @ResponseBody
//    public String showRank(int limit,int offset,String department,String statu){
//
//    }

//    @RequestMapping("/compare")
//    public String showCompare(HttpServletRequest request, Model model){
//        return "compare";
//    }
//
//    @RequestMapping("/doctor")
//    public String showDoctor(HttpServletRequest request, Model model){
//        return "doctor";
//    }
}
