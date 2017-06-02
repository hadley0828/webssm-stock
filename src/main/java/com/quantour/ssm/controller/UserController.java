package com.quantour.ssm.controller;

import com.google.gson.Gson;
import com.quantour.ssm.model.User;
import com.quantour.ssm.service.UserService;
import com.quantour.ssm.util.ResponseObj;
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
    private ResponseObj responseObj;

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
    public String login(String id, String password){
//        String result = "";
//        System.out.println(id);
        User user = userService.getOneUserByAccount(id);

        if(user == null){
            responseObj = new ResponseObj();
            responseObj.setCode("0");
            responseObj.setMessage("未找到该用户");
            return new Gson().toJson(responseObj);

        }else{
            if(userService.isPasswordValid(id,password)){
                responseObj = new ResponseObj();
                responseObj.setCode("1");
                responseObj.setMessage("pass");
                return new Gson().toJson(responseObj);
            }
        }
//        String result = "true";
//        System.out.println(userPwd.getId());
//        System.out.println(userPwd.getPassword());
//        if(!userService.isAccountValid(userPwd.getId())){
//            result = "false";
//        }
//        else if(!userService.isPasswordValid(userPwd.getId(),userPwd.getPassword())){
//            result = "fasle";
//        }
        return null;
    }
}
