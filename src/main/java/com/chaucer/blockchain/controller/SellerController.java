package com.chaucer.blockchain.controller;

import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.service.SenseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Chaucer
 * @date 2019-10-07 15:40
 */

@Controller
public class SellerController {

    @Autowired
    SenseDataService senseDataServiceImpl;

    @GetMapping(value = "/writeToBc")
    public String writeToBc() {
        return "data/writeToBlockchain";
    }

    @RequestMapping(value = "/generateDate",method = RequestMethod.POST)
    public String logsPrint(Model model) {
        for(int i = 0; i < 3; i++){
            Mapping mapping = senseDataServiceImpl.writeToBlockchain();
            senseDataServiceImpl.insMapping(mapping);
        }
        List<String> logs = senseDataServiceImpl.extractLogs();
        model.addAttribute("logs",logs);

        return "data/writeToBlockchain";
    }
}
