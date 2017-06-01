package com.quantour.ssm.controller;

import com.quantour.ssm.dto.*;
import com.quantour.ssm.service.StaticService;
import com.quantour.ssm.service.StockService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Created by loohaze on 2017/5/11.
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    private Logger log = Logger.getLogger(StockController.class);

    @Resource
    private StockService stockService;
    @Resource
    private StaticService staticService;

    @RequestMapping("/test")
    public String showDateByCode(HttpServletRequest request, Model model){
//        List<String> data = stockService.getAllDateByCode("000001");
        stockDTO stockdto = stockService.getStockInfo("000001","2017-03-17");
        model.addAttribute("stockdto",stockdto);
        return "serviceTest/test";
    }

    @RequestMapping("/stockkline")
    public String showKline(HttpServletRequest request,Model model){
        ArrayList<klineDTO> klineDTOArrayList=stockService.getKline("000001","2007-11-22","2008-12-20");
        model.addAttribute("klineDTOArrayList",klineDTOArrayList);
        return "serviceTest/stockkline";
    }

    @RequestMapping("/market")
    public String showMarket(HttpServletRequest request,Model model){
        marketDTO marketdto=stockService.getMarketInfo("2008-11-28");
        model.addAttribute("marketdto",marketdto);
        return "serviceTest/market";
    }

    @RequestMapping("/compare")
    public String showCompare(HttpServletRequest request,Model model){
        ArrayList<compareDTO> compareDTOArrayList=stockService.getCompareInfo("000001","000002","2009-01-01","2009-02-13");
        model.addAttribute("compareDTOArrayList",compareDTOArrayList);
        return "serviceTest/compare";
    }

    @RequestMapping("/allStockName")
    public String showAllStockName(HttpServletRequest request,Model model){
        ArrayList<String> allStockNameList=stockService.getAllCodeAndName();
        model.addAttribute("allStockNameList",allStockNameList);
        return "serviceTest/allStockNameList";
    }

    @RequestMapping("/allPlateName")
    public String showAllPlateName(HttpServletRequest request, Model model){
        ArrayList<String> allPlateNameList=stockService.getAllPlateName();
        model.addAttribute("allPlateNameList",allPlateNameList);
        return "serviceTest/allPlateNameList";
    }

    @RequestMapping("/onePlateStocks")
    public String showOnePlateStocks(HttpServletRequest request,Model model){
        ArrayList<String> allStockList=stockService.getPlateAllStockCode("江苏");
        model.addAttribute("allStockList",allStockList);
        return "serviceTest/allStockList";
    }

    @RequestMapping("/oneStockPlates")
    public String showOneStockPlates(HttpServletRequest request,Model model){
        ArrayList<String> allPlateList=stockService.getOneStockAllPlate("000001");
        model.addAttribute("allPlateList",allPlateList);
        return "serviceTest/allPlateList";
    }

    @RequestMapping("/getTop")
    public String showGetTop(HttpServletRequest request,Model model){
        ArrayList<waveDTO> waveDTOArrayList=stockService.getTopNCodesByDays(20,"2006-09-11",1);
        model.addAttribute("waveDTOArrayList",waveDTOArrayList);
        ArrayList<waveDTO> waveDTOArrayList1=stockService.getTopNCodesByDays(10,"2006-09-11",2);
        model.addAttribute("waveDTOArrayList1",waveDTOArrayList1);
        return "serviceTest/waveDTOArrayList";
    }

    //4s左右
    @RequestMapping("/getChangeNumber")
    public String showChangeNumber(HttpServletRequest request,Model model){
        ArrayList<limitUpAndDownNumsDTO> numberList=stockService.getLimitUpAndDownNumber("2006-09-11");
        model.addAttribute("numberList",numberList);
        return "serviceTest/numberList";
    }


    @RequestMapping("/getStrategyOne")
    public String showStrategyOne(HttpServletRequest request,Model model){
        ArrayList<String> codeList=new ArrayList<String>();
        codeList.add("000001");

        long startTime = System.currentTimeMillis();

        strategyResultDTO resultDTO=staticService.getStraOneResult(20,10,"2010-09-11","2011-9-11",1,codeList,"sh000300");

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");

        model.addAttribute("resultDTO",resultDTO);
        return "serviceTest/resultOneDTO";
    }




}
