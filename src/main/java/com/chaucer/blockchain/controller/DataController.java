package com.chaucer.blockchain.controller;

import com.chaucer.blockchain.mapper.SenseDataMapper;
import com.chaucer.blockchain.pojo.SenseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Chaucer
 * @date 2019-08-25 14:46
 */
@Controller
public class DataController {

    @Autowired
    SenseDataMapper senseDataMapper;


    //查询所有的设备标签，并返回所有的数据信息
    @GetMapping("/data")
    public String list(Model model) {
        List<SenseData> senseData = senseDataMapper.selectAll();
        //放在请求域中
        model.addAttribute("data",senseData);
        //thymeleaf会默认拼串
        return "data/list";
    }
}
