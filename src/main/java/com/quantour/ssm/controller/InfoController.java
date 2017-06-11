package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/8.
 */

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


@Controller
@RequestMapping("/userInfo")
public class InfoController {

    private Logger log = Logger.getLogger(InfoController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String showUserInfo(@RequestParam(value = "id",required = false) String user_id,HttpServletRequest request,Model model){
        try{
            System.out.println(";"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);

            model.addAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "userInfo";
    }
}
