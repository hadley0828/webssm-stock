package com.quantour.ssm.controller;

import com.google.gson.Gson;
import com.quantour.ssm.model.User;
import com.quantour.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by loohaze on 2017/5/9.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }

    @RequestMapping("/insert")
    public String insertUser(HttpServletRequest request, Model model){
        log.info("");
        boolean flag = userService.setNewAccount("000001","sdfadfdas");
        model.addAttribute("flag",flag);
        return "insert";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(HttpServletRequest req, User user){
        String result;
        if(user == null){

        }

        return new Gson().toJson("");
    }
}
