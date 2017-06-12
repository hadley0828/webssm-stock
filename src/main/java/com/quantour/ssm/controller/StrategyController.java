package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/10.
 */

import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.service.CustomizeService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/strategy")
public class StrategyController {
    private Logger log = Logger.getLogger(DashBoardController.class);

    @Resource
    private StockService stockService;
    @Resource
    private UserService userService;
    @Resource
    private CustomizeService customizeService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String showStrategy(@RequestParam(value = "id",required = false) String user_id, HttpServletRequest request, Model model){
        try{
            System.out.println(";"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);

            model.addAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "strategy";
    }

    @RequestMapping(value="/createStrategy")
    public ModelAndView createStrategy(@RequestParam(value = "id",required = false) String id, HttpServletRequest request, ModelAndView mav){

        try {
            userDTO user = userService.getOneUserByAccount(id);
            mav.addObject("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<String> conceptBlock = customizeService.getAllConceptBlock();
        ArrayList<String> industryBlock = customizeService.getAllIndustryBlock();
        ArrayList<String> areaBlock = customizeService.getAllAreaBlock();


        mav.addObject("conceptBlock",conceptBlock);
        mav.addObject("industryBlock",industryBlock);
        mav.addObject("areaBlock",areaBlock);

        mav.setViewName("createStrategy");

        return mav;
    }

    @RequestMapping(value = "/createCustomizeStrategy", method = RequestMethod.POST ,produces = "charset=utf-8")
    @ResponseBody
    public String createCustomizeStrategy(String map){
        HashMap<String,Object> datamap = new HashMap<String, Object>();
        String[] data = map.split("&");
        for(String s : data){
            String[] items = s.split("=");
            datamap.put(items[0],items[1]);
        }

        Iterator iter = datamap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        return null;
    }
}
