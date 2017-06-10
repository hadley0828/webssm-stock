package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

import com.google.gson.Gson;
import com.quantour.ssm.dto.RankDTO;
import com.quantour.ssm.dto.compareDTO;
import com.quantour.ssm.dto.waveDTO;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.util.JsonConvert;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/compare")
public class CompareController {
    private Logger log = Logger.getLogger(DashBoardController.class);


    @Resource
    private StockService stockService;

    @RequestMapping("")
    public String showCompare(HttpServletRequest request, Model model){
        try {
            ArrayList<RankDTO> hot_list = stockService.getTopNStockByDays(10, "2017-05-08", 1);
            model.addAttribute("hot_list",hot_list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "compare";
    }

    @RequestMapping(value="/doCompare",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doCompare(String codename1, String codename2){

        String codeid1 = stockService.getStockCodeByName(codename1);
        String codeid2 = stockService.getStockCodeByName(codename2);

        HashMap<String,String> map = new HashMap<String, String>();
        map.put("codeid1",codeid1);
        map.put("codeid2",codeid2);


        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/getLogLine", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getLogLineInfo(String codeid1, String codeid2,String sdate, String ldate){

        System.out.println(codeid1);
        System.out.println(codeid2);
        List<compareDTO> list = stockService.getCompareInfo(codeid1,codeid2,sdate,ldate);

        compareDTO c1 = list.get(0);
        compareDTO c2 = list.get(1);

        ArrayList<Double> data1 = c1.getLogYieldList();
        ArrayList<String> date1 = c1.getDateList();

        ArrayList<Double> data2 = c2.getLogYieldList();
        ArrayList<String> date2 = c2.getDateList();

        HashMap<String,String> map = new HashMap<String, String>();

        String result1 = JsonConvert.logLineConvert(data1,date1);
        String result2 = JsonConvert.logLineConvert(data2,date2);

        map.put("code1",result1);
        map.put("code2",result2);
//        map.put("code1",)
        return new Gson().toJson(map);
    }
}
