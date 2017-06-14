package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

import com.quantour.ssm.dto.RankDTO;
import com.quantour.ssm.dto.stockDTO;
import com.quantour.ssm.dto.stockRate.*;
import com.quantour.ssm.dto.userDTO;
import com.quantour.ssm.service.RateService;
import com.quantour.ssm.service.StockService;
import com.quantour.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private Logger log = Logger.getLogger(DashBoardController.class);

    @Resource
    private StockService stockService;
    @Resource
    private UserService userService;
    @Resource
    private RateService rateService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String showMain(@RequestParam(value = "id",required = false) String id,HttpServletRequest request,Model model){
        String date = "2017-06-01";

        try{
            userDTO user = userService.getOneUserByAccount(id);

            model.addAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            List<String> codeAndName = stockService.getAllCodeAndName();
            model.addAttribute("codeAndName",codeAndName);

            ArrayList<RankDTO> one_day_list = stockService.getTopNStockByDays(10,date,1);

            model.addAttribute("one_day_list",one_day_list);

            ArrayList<stockDTO> commendList=stockService.getIntelligentStock(id,date);

            model.addAttribute("commendList",commendList);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "mainDoctor";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String showDotor(@RequestParam(value = "id",required=false ) String user_id,
                            @RequestParam(value = "stockcode",required = false) String stock_id,
                            HttpServletRequest request,
                            Model model){

        try{
            System.out.println(";"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);

            model.addAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            stock_id = stock_id.substring(0,6);

            GeneralScoreDTO generalScoreDTO = rateService.getOneStockGeneralScore(stock_id,"2017-06-02");
            BasicDTO basicDTO = generalScoreDTO.getBasicDTO();
            CapitalDTO capitalDTO = generalScoreDTO.getCapitalDTO();
            IndustryDTO industryDTO = generalScoreDTO.getIndustryDTO();
            MessageDTO messageDTO = generalScoreDTO.getMessageDTO();
            TechnicalDTO technicalDTO = generalScoreDTO.getTechnicalDTO();


            model.addAttribute("basic",basicDTO);
            model.addAttribute("capital",capitalDTO);
            model.addAttribute("industry",industryDTO);
            model.addAttribute("message",messageDTO);
            model.addAttribute("technical",technicalDTO);
            model.addAttribute("generalScore",generalScoreDTO);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "doctor";
    }
}
