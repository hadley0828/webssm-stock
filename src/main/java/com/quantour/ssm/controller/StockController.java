package com.quantour.ssm.controller;

import com.quantour.ssm.dto.*;
import com.quantour.ssm.dto.UserHistory.StockRecordDTO;
import com.quantour.ssm.dto.UserHistory.StrategyResultRecordDTO;
import com.quantour.ssm.dto.customizeStrategy.CustomizeStrategyDTO;
import com.quantour.ssm.dto.customizeStrategy.ScreeningConditionDTO;
import com.quantour.ssm.dto.customizeStrategy.StockPondDTO;
import com.quantour.ssm.dto.customizeStrategy.TradeModelDTO;
import com.quantour.ssm.dto.stockRate.BasicDTO;
import com.quantour.ssm.dto.stockRate.CapitalDTO;
import com.quantour.ssm.dto.stockRate.IndustryDTO;
import com.quantour.ssm.dto.stockRate.MessageDTO;
import com.quantour.ssm.model.*;
import com.quantour.ssm.service.*;
import com.quantour.ssm.util.DateConvert;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;


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
    @Resource
    private CustomizeService customizeService;
    @Resource
    private UserService userService;
    @Resource
    private HistoryService historyService;
    @Resource
    private RateService rateService;

    @RequestMapping("/test")
    public String showDateByCode(HttpServletRequest request, Model model){
//        List<String> data = stockService.getAllDateByCode("000001");
        stockDTO stockdto = stockService.getStockInfo("000004","2017-05-23");
        model.addAttribute("stockdto",stockdto);
        return "serviceTest/test";
    }

    @RequestMapping("/testDateValid")
    public String showDateValid(HttpServletRequest request,Model model){
        Boolean result=stockService.isDateValid("000001","2017-05-23");
        model.addAttribute("result",result);
        return "serviceTest/result";
    }

    @RequestMapping("/stockkline")
    public String showKline(HttpServletRequest request,Model model){
        ArrayList<klineDTO> klineDTOArrayList=stockService.getKline("000002","2007-11-22","2008-12-20");
        model.addAttribute("klineDTOArrayList",klineDTOArrayList);
        return "serviceTest/stockkline";
    }

    @RequestMapping("/market")
    public String showMarket(HttpServletRequest request,Model model){
        marketDTO marketdto=stockService.getMarketInfo("2016-11-28");
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
        ArrayList<waveDTO> waveDTOArrayList=stockService.getTopNCodesByDays(10,"2006-09-11",1);
        model.addAttribute("waveDTOArrayList",waveDTOArrayList);
        ArrayList<RankDTO> waveDTOArrayList1=stockService.getTopNStockByDays(10,"2006-09-11",1);
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

        strategyResultDTO resultDTO=staticService.getStraOneResult(20,10,"2010-09-11","2011-09-11",1,codeList,"sh000300");

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");

        model.addAttribute("resultDTO",resultDTO);
        return "serviceTest/resultOneDTO";
    }


    @RequestMapping("/getStrategyTwo")
    public String showStrategyTwo(HttpServletRequest request,Model model){
        ArrayList<String> codeList=new ArrayList<String>();
        codeList.add("000001");

        strategyResultDTO resultDTO=staticService.getStraTwoResult(10,10,5,"2010-09-11","2011-09-11",1,codeList,"sh000300");

        model.addAttribute("resultDTO",resultDTO);
        return "serviceTest/resultTwoDTO";
    }

    @RequestMapping("/getCustomize")
    public String showCustomize(HttpServletRequest request,Model model){
        StockPondDTO stockPondDTO=new StockPondDTO();
        stockPondDTO.setStockPondChosen("全部股票");
        stockPondDTO.setIndexIngredient("全选");
        stockPondDTO.setBlock("全选");
        stockPondDTO.setIndustry("全选");
        stockPondDTO.setConcept("全选");
        stockPondDTO.setSTStock("包含ST");
        stockPondDTO.setExchange("全选");
        stockPondDTO.setRegion("全选");


        ArrayList<ScreeningConditionDTO> screeningConditionDTOArrayList=new ArrayList<ScreeningConditionDTO>();
        ScreeningConditionDTO screeningConditionDTO=new ScreeningConditionDTO();
        screeningConditionDTO.setConditionName("开盘价");
        screeningConditionDTO.setCompareSymbol("排名最大");
        screeningConditionDTO.setScope("全部");
        screeningConditionDTO.setFirstValue(10.0);
        screeningConditionDTOArrayList.add(screeningConditionDTO);

        TradeModelDTO tradeModelDTO=new TradeModelDTO();
        tradeModelDTO.setMaxHoldStockNumber(10);
        tradeModelDTO.setTransferCycle(10);

        strategyResultDTO resultDTO=customizeService.getCustomizeStrategyResult("po","2010-09-11","2011-09-11","sh000300",stockPondDTO,screeningConditionDTOArrayList,tradeModelDTO);

        model.addAttribute("resultDTO",resultDTO);
        return "serviceTest/customizeResultDTO";

    }

    @RequestMapping("/getSeveralStock")
    public String showSeveralStock(HttpServletRequest request,Model model){
        ArrayList<String> codeList=new ArrayList<String>();
        codeList.add("000001");
        codeList.add("000002");
        codeList.add("000004");
        codeList.add("000005");

        ArrayList<stockDTO> stockDTOArrayList=stockService.getSeveralStockInfo(codeList,"2010-09-11");

        model.addAttribute("stockDTOArrayList",stockDTOArrayList);
        return "serviceTest/stockDTOArrayList";
    }

    @RequestMapping("/getStockCode")
    public String showStockCode(HttpServletRequest request,Model model){
        String stockCode=stockService.getStockCodeByName("平安银行");
        model.addAttribute("stockCode",stockCode);

        return "serviceTest/stockCode";
    }

    @RequestMapping("/getStockCodes")
    public String showStockCodes(HttpServletRequest request,Model model){
        ArrayList<String> nameList=new ArrayList<String>();
        nameList.add("平安银行");
        nameList.add("国农科技");
        nameList.add("世纪星源");

        ArrayList<String> codeList=stockService.getStockCodesByNames(nameList);
        model.addAttribute("codeList",codeList);

        return "serviceTest/stockCodes";

    }


    @RequestMapping("/getUser")
    public String showUser(HttpServletRequest request,Model model){
        userDTO userdto=userService.getOneUserByAccount("loohaze");
        model.addAttribute("userdto",userdto);
        return "serviceTest/userdto";
    }

    @RequestMapping("/getBlockKLine")
    public String showBlockKLine(HttpServletRequest request,Model model){
        ArrayList<klineDTO> dayKLineArrayList=stockService.getBlockKline("sh000001","2008-05-11","2009-08-09");
        model.addAttribute("dayKLineArrayList",dayKLineArrayList);
        return "serviceTest/blockKLine";
    }

    @RequestMapping("/getOptionalStock")
    public String showOptionalStock(HttpServletRequest request,Model model){

//        stockService.addOneNewOptionalStock("123","000001");
//        stockService.addOneNewOptionalStock("123","000002");
//        stockService.addOneNewOptionalStock("123","000004");

        stockService.deleteUserAllOptionalStock("123");
//        stockService.deleteOneOptionalStock("po","000001");


        ArrayList<String> userAllStockList=stockService.getUserAllOptionalStock("123");



        model.addAttribute("userAllStockList",userAllStockList);

        return "serviceTest/optionalStock";


    }

    @RequestMapping("/getCapitalScore")
    public String showCapitalScore(HttpServletRequest request,Model model){
        CapitalDTO capitalDTO=rateService.getOneStockCapitalScore("000011","2017-06-02");

        model.addAttribute("capitalDTO",capitalDTO);

        return "serviceTest/capitalScore";
    }

    @RequestMapping("/getMessageScore")
    public String showMessageScore(HttpServletRequest request,Model model){
        MessageDTO messageDTO=rateService.getOneStockMessageScore("000006","2017-06-02");

        model.addAttribute("messageDTO",messageDTO);
        return "serviceTest/messageScore";
    }


    @RequestMapping("/getIndustryScore")
    public String showIndustryScore(HttpServletRequest request,Model model){
        IndustryDTO industryDTO=rateService.getOneStockIndustryScore("000001","2017-06-02");

        model.addAttribute("industryDTO",industryDTO);
        return "serviceTest/industryScore";

    }

    @RequestMapping("/getBasicScore")
    public String showBasicScore(HttpServletRequest request,Model model){
        BasicDTO basicDTO=rateService.getOneStockBasicScore("000001","2017-06-02");

        model.addAttribute("basicDTO",basicDTO);
        return "serviceTest/basicScore";
    }


    @RequestMapping("/getUserAllStockRecord")
    public String showUserAllStockRecord(HttpServletRequest request,Model model){
        historyService.deleteOneStockRecord("po","2017-06-10 10:23:09");
        StockRecordDTO stockRecordDTO=new StockRecordDTO();
        stockRecordDTO.setUser_id("po");
        stockRecordDTO.setCode_id("000005");
        stockRecordDTO.setDate_time(customizeService.getCurrentTime());

        historyService.createNewStockRecord(stockRecordDTO);

        ArrayList<StockRecordDTO> userDTOArrayList=historyService.getUserAllStockRecord("po","2014-08-09");


        model.addAttribute("userDTOArrayList",userDTOArrayList);
        return "serviceTest/userAllStockRecord";
    }

    @RequestMapping("/getUserAllStrategyRecord")
    public String showUserAllStrategyRecord(HttpServletRequest request,Model model){
        StrategyResultRecordDTO srrd=new StrategyResultRecordDTO();
        srrd.setUser_id("po");
        srrd.setResult_time(customizeService.getCurrentTime());
        srrd.setStrategy_name("用来测试");
        srrd.setStrategy_intro("用来测试插入数据");
        srrd.setStart_time(DateConvert.stringToDate("2010-09-11"));
        srrd.setEnd_time(DateConvert.stringToDate("2011-09-11"));
        srrd.setBase_block("sh000300");
        srrd.setYear_profit(0.0);
        srrd.setStandard_profit(0.0);
        srrd.setAlpha(0.0);
        srrd.setBeta(0.0);
        srrd.setSharp_rate(0.0);
        srrd.setProfit_waverate(0.0);
        srrd.setInfo_percent(0.0);
        srrd.setMax_back(0.0);
        srrd.setTurnover_rate(0.0);


        historyService.createNewStrategyRecord(srrd);
        ArrayList<StrategyResultRecordDTO> strategyRecordArrayList=historyService.getUserAllStrategyRecord("po");

        model.addAttribute("strategyRecordArrayList",strategyRecordArrayList);
        return "serviceTest/userAllStrategyRecord";

    }

    @RequestMapping("/getAllCustomizeStrategy")
    public String showAllCustomizeStrategy(HttpServletRequest request,Model model){
        ArrayList<CustomizeStrategyDTO> allList=customizeService.getOneUserAllStrategy("123");
        model.addAttribute("allList",allList);
        return "serviceTest/allCustomizeStrategy";

    }


    @RequestMapping("/getCustomizeStrategy")
    public String showCustomizeStrategy(HttpServletRequest request,Model model){

        CustomizeStrategyDTO oneStrategy=customizeService.getOneStrategy("po 2017-06-10 14:04:19");

        CustomizeStrategyDTO twoStrategy=new CustomizeStrategyDTO();
        String userId="123";
        String time=customizeService.getCurrentTime();

        twoStrategy.setStrategyID(userId+" "+time);
        twoStrategy.setCreaterID(userId);
        twoStrategy.setStrategyName("新创建的策略");
        twoStrategy.setStrategyExplanation("该策略为新创建的策略");
        twoStrategy.setCreateTime(time);

        StockPondDTO stockPondDTO=new StockPondDTO();
        stockPondDTO.setStockPondChosen("全部股票");
        stockPondDTO.setIndexIngredient("全选");
        stockPondDTO.setBlock("全选");
        stockPondDTO.setIndustry("全选");
        stockPondDTO.setConcept("全选");
        stockPondDTO.setSTStock("包含ST");
        stockPondDTO.setExchange("全选");
        stockPondDTO.setRegion("全选");
        twoStrategy.setStockPondDTO(stockPondDTO);

        ArrayList<ScreeningConditionDTO> conditionList=new ArrayList<ScreeningConditionDTO>();
        ScreeningConditionDTO oneCondition=new ScreeningConditionDTO();
        oneCondition.setStrategyId(userId+" "+time);
        oneCondition.setConditionName("当日成交量");
        oneCondition.setCompareSymbol("大于");
        oneCondition.setScope("全部");
        oneCondition.setFirstValue(10);
        oneCondition.setSecondValue(20);

        conditionList.add(oneCondition);
        twoStrategy.setScreeningConditionDTOArrayList(conditionList);

        TradeModelDTO tradeModelDTO=new TradeModelDTO();
        tradeModelDTO.setMaxHoldStockNumber(10);
        tradeModelDTO.setTransferCycle(8);
        twoStrategy.setTradeModelDTO(tradeModelDTO);

        strategyResultDTO resultDTO=new strategyResultDTO();
        resultDTO.setStraId(userId+" "+time);
        resultDTO.setYearProfit(1.0);
        resultDTO.setStandardProfit(1.0);
        resultDTO.setAlpha(1.0);
        resultDTO.setBeta(1.0);
        resultDTO.setSharpRate(1.0);
        resultDTO.setProfitWaveRate(1.0);
        resultDTO.setInfoPercent(1.0);
        resultDTO.setMaxBack(1.0);
        resultDTO.setTurnoverRate(1.0);
        resultDTO.setCurrentStraProfit(1.0);
        resultDTO.setCurrentStandardProfit(1.0);
        twoStrategy.setResultDTO(resultDTO);



        customizeService.insertOneStrategy(twoStrategy);

//        customizeService.deleteOneStrategy("123 2017-06-10 15:21:46");

//        customizeService.deleteUserAllStrategy("123");

        model.addAttribute("oneStrategy",oneStrategy);
        return "serviceTest/customizeStrategy";
    }



}
