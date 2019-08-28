package com.chaucer.blockchain.controller;

import com.chaucer.blockchain.pojo.SenseData;
import com.chaucer.blockchain.service.SenseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

/**
 * @author Chaucer
 * @date 2019-08-22 14:52
 */
@Controller
public class MainController {


    @Autowired
    SenseDataService senseDataService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "showByType", method = RequestMethod.GET)
    public String showDevices(Model model) {
        List<SenseData> senseData = senseDataService.getSenseDataByDataType("temptype004");
        model.addAttribute("senseData", senseData);
        return "showByType";
    }
}
