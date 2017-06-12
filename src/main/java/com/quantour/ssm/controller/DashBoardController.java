package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/5/16.
 */

import com.quantour.ssm.dto.RankDTO;
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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showBoard(@RequestParam(value = "id",required=false ) String user_id, HttpServletRequest request, ModelAndView model){

        model.setViewName("dashboard");
        try{
            System.out.println(":"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);
            System.out.println(user.getAccount());

            model.addObject("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            ArrayList<String> codeAndName = stockService.getAllCodeAndName();
            ArrayList<RankDTO> one_day_list = stockService.getTopNStockByDays(5,"2017-05-08",1);

            model.addObject("codeAndName",codeAndName);
            model.addObject("one_day_list",one_day_list);
        }catch (Exception e){
            e.printStackTrace();
        }

//        ArrayList<RankDTO> five_day_list= stockService.getTopNStockByDays(5,"2016-05-08",5);
//
//        model.addAttribute("one_day_list",one_day_list);
//        model.addAttribute("five_day_list",five_day_list);
        return model;
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
