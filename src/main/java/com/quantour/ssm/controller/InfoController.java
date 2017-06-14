package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/8.
 */

import com.google.gson.Gson;
import com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO;
import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.service.CustomizeService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping("/userInfo")
public class InfoController {

    private Logger log = Logger.getLogger(InfoController.class);

    @Resource
    private UserService userService;
    @Resource
    private StockService stockService;
    @Resource
    private CustomizeService customizeService;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView showUserInfo(@RequestParam(value = "id",required = false) String user_id,HttpServletRequest request){

        ModelAndView model = new ModelAndView();
        model.setViewName("userInfo");




        try{

            userDTO user = userService.getOneUserByAccount(user_id);
            model.addObject("user",user);

            ArrayList<String> stockCodeList=stockService.getUserAllOptionalStock(user_id);
            ArrayList<stockDTO> stockInfoList= new ArrayList<stockDTO>();

            if(stockCodeList.size()!=0){
                stockInfoList=stockService.getSeveralStockInfo(stockCodeList,"2017-06-02");

            }
            model.addObject("optionalStockList",stockInfoList);


            ArrayList<CustomizeStrategyDTO> customizeStrategyDTOArrayList=customizeService.getOneUserAllStrategy(user_id);
            model.addObject("strategyList",customizeStrategyDTOArrayList);


        }catch (Exception e){
            e.printStackTrace();
        }

        return model;
    }


    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    @ResponseBody
    public String update(String id,String sex,String age,String address,String phone,String mail,String intro,String name,String birthday){

        HashMap<String,String> map = new HashMap<String, String>();
        boolean result = false;

        try{
            userDTO user = userService.getOneUserByAccount(id);

            user.setSex(sex);
            user.setAge(Integer.valueOf(age));
            user.setAddress(address);
            user.setHandsetNumber(phone);
            user.setMail(mail);
            user.setIntroduction(intro);
            user.setName(name);
            user.setBirthday(birthday);

            result = userService.updateUser(id,user);

        }catch (Exception e){
            e.printStackTrace();
        }

        if(result){
            map.put("result","更新成功");
        }else{
            map.put("result","更新失败");
        }

        return new Gson().toJson(map);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST )
    @ResponseBody
    public String delete(String id,String code){
        boolean result = false;
        HashMap<String,String> map = new HashMap<String, String>();

        try{
            result = stockService.deleteOneOptionalStock(id,code);

        }catch (Exception e){
            e.printStackTrace();
        }

        if(result){
            map.put("result","删除成功");
        }else{
            map.put("result","删除失败");
        }

        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/deleteStra",method = RequestMethod.POST )
    @ResponseBody
    public String deleteStra(String straid){
        boolean result = false;
        HashMap<String,String> map = new HashMap<String, String>();

        try{
            result = customizeService.deleteOneStrategy(straid);

        }catch (Exception e){
            e.printStackTrace();
        }

        if(result){
            map.put("result","删除成功");
        }else{
            map.put("result","删除失败");
        }

        return new Gson().toJson(map);
    }
}
