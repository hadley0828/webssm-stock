package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/10.
 */

import com.google.gson.Gson;
import com.quantour.ssm.dto.customizeStrategy.ScreeningConditionDTO;
import com.quantour.ssm.dto.customizeStrategy.StockPondDTO;
import com.quantour.ssm.dto.customizeStrategy.TradeModelDTO;
import com.quantour.ssm.dto.strategyResultDTO;
import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.service.CustomizeService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import com.quantour.ssm.util.JsonConvert;
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

    @RequestMapping(value = "/runStrategy", method = RequestMethod.POST, produces = "charset=utf-8")
    @ResponseBody
    public String runStrategy(String map){
        HashMap<String,Object> datamap = new HashMap<String, Object>();
        HashMap<String,Object> result = new HashMap<String, Object>();

        String[] data = map.split("&");
        for(String s : data){
            String[] items = s.split("=");
            datamap.put(items[0],items[1]);
        }

        Iterator iter = datamap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
//            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        String userId = (String) datamap.get("userId");
        String sDate = (String) datamap.get("sDate");
        String lDate = (String) datamap.get("lDate");
        String blockCode = (String) datamap.get("blockCode");


        StockPondDTO stockPondDTO = new StockPondDTO();
        stockPondDTO.setBlock((String) datamap.get("block"));
        stockPondDTO.setConcept((String) datamap.get("concept"));
        stockPondDTO.setExchange((String) datamap.get("exchange"));
        stockPondDTO.setIndexIngredient((String) datamap.get("IndexIngredient"));
        stockPondDTO.setIndustry((String) datamap.get("industry"));
        stockPondDTO.setRegion((String) datamap.get("region"));
        stockPondDTO.setStockPondChosen((String) datamap.get("stockPondChosen"));
        stockPondDTO.setSTStock((String) datamap.get("STStock"));


        ArrayList<ScreeningConditionDTO> screenlist = new ArrayList<ScreeningConditionDTO>();

        for(int i = 1; i <= 13; i++){
            if(datamap.containsKey("conditionName" + String.valueOf(i))){
                ScreeningConditionDTO s = new ScreeningConditionDTO();
                s.setConditionName((String) datamap.get("conditionName" + String.valueOf(i)));
                s.setCompareSymbol((String) datamap.get("compareSymbol" + String.valueOf(i)));
                s.setScope((String) datamap.get("scope" + String.valueOf(i)));
                s.setFirstValue(Double.valueOf((String)datamap.get("firstValue"+ String.valueOf(i))));
                if(datamap.containsKey("secondValue" + String.valueOf(i))){
                    s.setSecondValue(Double.valueOf((String) datamap.get("secondValue" + String.valueOf(i))));
                }
                screenlist.add(s);
            }
        }


        TradeModelDTO tradeModelDTO = new TradeModelDTO();
        tradeModelDTO.setTransferCycle(Integer.valueOf((String) datamap.get("transferCycle")));
        tradeModelDTO.setMaxHoldStockNumber(Integer.valueOf((String)datamap.get("max_num")));

        strategyResultDTO resultDTO = customizeService.getCustomizeStrategyResult(userId,sDate,lDate,blockCode,stockPondDTO,screenlist,tradeModelDTO);

        result.put("straId",resultDTO.getStraId());
        result.put("yearProfit",resultDTO.getYearProfit());
        result.put("standardProfit",resultDTO.getStandardProfit());
        result.put("alpha",resultDTO.getAlpha());
        result.put("beta",resultDTO.getBeta());
        result.put("sharpRate",resultDTO.getSharpRate());
        result.put("profitWaveRate",resultDTO.getProfitWaveRate());
        result.put("infoPercent",resultDTO.getInfoPercent());
        result.put("maxBack",resultDTO.getMaxBack());
        result.put("turnoverRate",resultDTO.getTurnoverRate());
        result.put("currentStandardProfit",resultDTO.getCurrentStandardProfit());
        result.put("currentStraProfit",resultDTO.getCurrentStraProfit());
        result.put("daysProfitList", JsonConvert.Stra1LineConvert(resultDTO.getDaysProfitList()));
        result.put("indexprofitvo",JsonConvert.Stra2LineConvert(resultDTO.getIndexprofitvo()));

//        System.out.println(resultDTO.getAlpha());
//        System.out.println(resultDTO.getBeta());
//        System.out.println(resultDTO.getCurrentStandardProfit());
//        System.out.println(resultDTO.getCurrentStraProfit());
//        System.out.println(resultDTO.getDaysProfitList());
//        System.out.println(resultDTO.getIndexprofitvo());
//        System.out.println(resultDTO.getInfoPercent());
//        System.out.println(resultDTO.getMaxBack());
//        System.out.println(resultDTO.getProfitWaveRate());
//        System.out.println(resultDTO.getSharpRate());
//        System.out.println(resultDTO.getStraId());
//        System.out.println(resultDTO.getTurnoverRate());
//        System.out.println(resultDTO.getYearProfit());
//        System.out.println(resultDTO.getStandardProfit());

        return new Gson().toJson(result);
    }
}
