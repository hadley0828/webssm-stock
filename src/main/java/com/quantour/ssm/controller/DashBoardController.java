package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/5/16.
 */

import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {

    private Logger log = Logger.getLogger(DashBoardController.class);

    @Resource
    private StockService stockService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showBoard(@RequestParam(value = "id",required=false ) String user_id, HttpServletRequest request, Model model){

        try{
            System.out.println(":"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);
            System.out.println(user.getAccount());

            ArrayList<String> codeAndName = stockService.getAllCodeAndName();
            model.addAttribute("codeAndName",codeAndName);
        }catch (Exception e){
            e.printStackTrace();
        }


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

    @RequestMapping("/stockData")
    public String showData(HttpServletRequest request,Model model){
        String searchCode = request.getParameter("searchCode");

        stockDTO s = new stockDTO();
        s = stockService.getStockInfo(searchCode,"2013-03-06");
//        s.setName("万科A");
//        s.setId("000001");

        model.addAttribute("stock",s);

        return "stock";
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
