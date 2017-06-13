package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/8.
 */

import com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO;
import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.service.CustomizeService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
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
}
