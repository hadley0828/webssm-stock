package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

import com.google.gson.Gson;
import com.quantour.ssm.dto.*;
import com.quantour.ssm.dto.UserHistory.StockRecordDTO;
import com.quantour.ssm.model.StockRecord;
import com.quantour.ssm.service.HistoryService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import com.quantour.ssm.util.JsonConvert;
import com.quantour.ssm.util.NumberConvert;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/compare")
public class CompareController {
    private Logger log = Logger.getLogger(DashBoardController.class);


    @Resource
    private StockService stockService;
    @Resource
    private UserService userService;
    @Resource
    private HistoryService historyService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showCompare(@RequestParam(value = "id",required = false) String user_id, HttpServletRequest request, Model model){
        String date="2017-06-01";

        try {

            ArrayList<RankDTO> hot_list = stockService.getTopNStockByDays(10, date, 1);
            model.addAttribute("hot_list",hot_list);
        }catch (Exception e){
            e.printStackTrace();
        }


        try{
            ArrayList<String> allOptionalStock=stockService.getUserAllOptionalStock(user_id);
            ArrayList<stockDTO> optionalStockList=new ArrayList<stockDTO>();

            if(allOptionalStock.size()!=0){

                optionalStockList=stockService.getSeveralStockInfo(allOptionalStock,date);

            }
            model.addAttribute("optionalStockList",optionalStockList);


        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            ArrayList<StockRecordDTO> historyList=historyService.getUserAllStockRecord(user_id,date);
            ListSort(historyList);

            ArrayList<String> codeList=new ArrayList<String>();
            for(int count=0;count<historyList.size();count++){
                codeList.add(historyList.get(count).getCode_id());
            }

            ArrayList<stockDTO> historyStockList=new ArrayList<stockDTO>();
            historyStockList=stockService.getSeveralStockInfo(codeList,date);

            model.addAttribute("historyStockList",historyStockList);


        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            ArrayList<String> codeAndName = stockService.getAllCodeAndName();
            model.addAttribute("codeAndName",codeAndName);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            System.out.println(";"+user_id);
            userDTO user =userService.getOneUserByAccount(user_id);
            model.addAttribute("user",user);
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


    @RequestMapping(value = "/getInitialInfo", method = RequestMethod.POST)
    @ResponseBody
    public String getInitialInfo(String sdate,String ldate,String codename1, String codename2){

        String code1=stockService.getStockCodeByName(codename1);
        String code2=stockService.getStockCodeByName(codename2);

        ArrayList<compareDTO> compareDTOArrayList=stockService.getCompareInfo(code1,code2,sdate,ldate);

        compareDTO compare1=compareDTOArrayList.get(0);
        compareDTO compare2=compareDTOArrayList.get(1);

        HashMap<String,String> map=new HashMap<String, String>();

        map.put("name1",compare1.getName());
        map.put("high1",String.valueOf(compare1.getHighestPrice()));
        map.put("low1",String.valueOf(compare1.getLowestPrice()));
        map.put("change1", NumberConvert.doubleToPercentageString(compare1.getUpOrDown()));
        map.put("logVar1",String.valueOf(NumberConvert.saveNDouble(compare1.getLogVariance(),5)));

        map.put("name2",compare2.getName());
        map.put("high2",String.valueOf(compare2.getHighestPrice()));
        map.put("low2",String.valueOf(compare2.getLowestPrice()));
        map.put("change2",NumberConvert.doubleToPercentageString(compare2.getUpOrDown()));
        map.put("logVar2",String.valueOf(NumberConvert.saveNDouble(compare2.getLogVariance(),5)));

        return new Gson().toJson(map);


    }

    private static void ListSort(List<StockRecordDTO> list) {
        Collections.sort(list, new Comparator<StockRecordDTO>() {
            @Override
            public int compare(StockRecordDTO o1, StockRecordDTO o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    java.util.Date dt1 = format.parse(o1.getDate_time());
                    java.util.Date dt2 = format.parse(o2.getDate_time());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
