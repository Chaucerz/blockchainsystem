package com.chaucer.blockchain.controller;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import com.chaucer.blockchain.mapper.SenseDataMapper;
import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import com.chaucer.blockchain.service.SenseDataService;
import com.chaucer.blockchain.service.TransactionService;
import com.chaucer.blockchain.utils.AES.AESutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chaucer
 * @date 2019-08-25 14:46
 */
@Controller
public class DataController {

    @Autowired
    SenseDataMapper senseDataMapper;

    @Autowired
    SenseDataService senseDataServiceImpl;

    @Autowired
    TransactionService transactionServiceImpl;

    List<String> logs;

    int dataSize;

    boolean isBuyerSecondTrans;

    boolean isSellerSecondTrans;

    //查询所有的设备标签，并返回所有的数据信息
    @GetMapping("/data")
    public String list(Model model) {
        senseDataServiceImpl.readFromBlockchain();
        List<SenseData> senseData = senseDataMapper.selectAll();
        //放在请求域中
        model.addAttribute("data",senseData);

        //thymeleaf会默认拼串
        return "data/list";
    }

    @GetMapping("/search")
    public String searchType(Model model) {
        //查出所有数据类型，在页面显示
        List<SenseData> senseData = senseDataMapper.selectAll();
        List<String> dataType = new ArrayList<>();
        for (int i = 0; i < senseData.size(); i++) {
            if (!dataType.contains(senseData.get(i).getDataType())){
                dataType.add(senseData.get(i).getDataType());
            }
        }
        model.addAttribute("types",dataType);
        //来到查询页面
        return "data/search";
    }


    @RequestMapping(value = "/searchByType", method = RequestMethod.POST)
    public String specificSenseData(HttpServletRequest request, Model model) {

        String dataType = request.getParameter("dataType");
        System.out.println(dataType);
        List<SenseData> senseDataByDataType = senseDataServiceImpl.getSenseDataByDataType(dataType);
        model.addAttribute("dataByType",senseDataByDataType);
        //转发到一个地址
        return "data/searchByType";
    }

    @RequestMapping(value = "/firstTransaction", method = RequestMethod.POST)
    @ResponseBody
    public List<String> firstTransaction(@RequestBody String dataType) {

        String label = dataType.substring(1, dataType.length() - 1);

        transactionServiceImpl.buyerGenerateTransaction(label);
        transactionServiceImpl.sellerGenerateTransaction(label);

        List<SenseData> senseDataByDataType = senseDataServiceImpl.getSenseDataByDataType(label);
        int size = senseDataByDataType.size();
        dataSize = size;
        transactionServiceImpl.generateCheque(10 * size);

        logs = transactionServiceImpl.extractLogs();

        logs.add("买家对数据的真实性发起举证请求：---------------------------");
        List<Mapping> mapping = transactionServiceImpl.getMapping(label);
        for (int i = 0; i < mapping.size(); i++) {
            String blindFactor = transactionServiceImpl.SNgenerator();
            logs.add(Base64.encode(AESutil.encrypt(mapping.get(i).toString(),blindFactor)) + "，blindingFactor：" + blindFactor);
        }

        logs.add("使用盲化因子去盲：----------------------------------------");
        for (int i = 0; i < mapping.size(); i++) {
            logs.add(mapping.get(i).toString());
        }
        List<String> firstTran = new ArrayList<>();

        if(isBuyerSecondTrans == false) {
            firstTran.add(logs.get(0));
            isBuyerSecondTrans = true;
        } else {
            firstTran.clear();
            firstTran.add(logs.get(3));
            for (int i = 0; i < size + 1; i++) {
                firstTran.add(logs.get(3 + i + 1));
            }
        }

        return firstTran;
    }


    @RequestMapping(value = "/sellerAuth", method = RequestMethod.POST)
    @ResponseBody
    public List<String> sellerAuth() {

        List<String> sellerAuth = new ArrayList<>();
        if(isSellerSecondTrans == false) {
            sellerAuth.add(logs.get(1));
            isSellerSecondTrans = true;
        } else {
            sellerAuth.clear();
            for (int i = 4 + dataSize + 1; i < logs.size() / 2; i++) {
                sellerAuth.add(logs.get(i));
            }
        }
        return sellerAuth;
    }


    @RequestMapping(value = "/thirdAuth", method = RequestMethod.POST)
    @ResponseBody
    public List<String> thirdAuth() {
        List<String> thirdAuth = new ArrayList<>();
        thirdAuth.add(logs.get(2));
        return thirdAuth;
    }



}
