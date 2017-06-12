package com.quantour.ssm.controller;

/**
 * Created by lenovo on 2017/6/6.
 */

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showDotor(@RequestParam(value = "id",required=false ) String user_id, HttpServletRequest request, Model model){

        try{
            System.out.println(";"+user_id);
            userDTO user = userService.getOneUserByAccount(user_id);

            model.addAttribute("user",user);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            BasicDTO basicDTO = rateService.getOneStockBasicScore("000001","2017-06-02");
            CapitalDTO capitalDTO = rateService.getOneStockCapitalScore("000001","2017-06-02");
            IndustryDTO industryDTO = rateService.getOneStockIndustryScore("000001","2017-06-02");
            MessageDTO messageDTO = rateService.getOneStockMessageScore("000001","2017-06-02");
            TechnicalDTO technicalDTO = rateService.getOneStockTechnicalScore("000001","2017-06-02");
            GeneralScoreDTO generalScoreDTO = rateService.getOneStockGeneralScore("000001","2017-06-02");

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
