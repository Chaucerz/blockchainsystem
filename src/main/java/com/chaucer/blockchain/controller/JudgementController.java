package com.chaucer.blockchain.controller;

import com.chaucer.blockchain.pojo.Ticket;
import com.chaucer.blockchain.service.TransactionService;
import com.chaucer.blockchain.utils.ECC.ECPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chaucer
 * @date 2019-10-07 12:50
 */
@Controller
public class JudgementController {

    @Autowired
    private TransactionService transactionServiceImpl;
    @Autowired
    private DataController dataController;


    @RequestMapping(value = "/judgement", method = RequestMethod.POST)
    public String jumpToJudgement(){
        return "data/judgement";
    }



    @RequestMapping(value = "/buyerJudgement",method = RequestMethod.POST)
    @ResponseBody
    public List<String> buyerJudgement(){
        List<String> buyerJudgement = new ArrayList<>();
        ECPoint kBT = transactionServiceImpl.generateSessionKey();
        buyerJudgement.add("kBT:"+ kBT);
        //获取交易记录
        List<String> tranRecord = dataController.thirdAuth();
        buyerJudgement.add(tranRecord.get(0));
        return buyerJudgement;
    }

    @RequestMapping(value = "/arbitratorJudgement",method = RequestMethod.POST)
    @ResponseBody
    public List<String> arbitratorJudgement(){
        List<String> arbitratorJudgement = new ArrayList<>();
        arbitratorJudgement.add("仲裁方查询区块链对应的交易记录：");
        List<String> tranRecord = dataController.thirdAuth();
        arbitratorJudgement.add(tranRecord.get(0));
        return arbitratorJudgement;
    }

    @RequestMapping(value = "/generateTicket",method = RequestMethod.POST)
    @ResponseBody
    public List<String> generateTicket() {
        List<String> generateTicket = new ArrayList<>();
        Ticket ticket = new Ticket();
        int val = (int)(Math.random() * 50 + 50);
        ticket.setPunish_val(val);
        //生成时间戳
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
        String timeStamp = simpleDateFormat.format(date);
        ticket.setTimeStamp(timeStamp);
        //交易记录
        List<String> cheque = dataController.thirdAuth();
        ticket.setTranRecord(cheque.get(0));

        generateTicket.add(ticket.toString());
        return generateTicket;
    }

}
