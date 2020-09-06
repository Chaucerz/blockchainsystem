package com.chaucer.blockchain;


import com.chaucer.blockchain.controller.DataController;
import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import com.chaucer.blockchain.service.SenseDataService;

import com.chaucer.blockchain.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigInteger;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockchainApplicationTests {
    @Test
    public void contextLoads() {
    }


    @Autowired
    SenseDataService senseDataService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    DataController dataController;

    @Value("password")
    private String password;
    @Value("path")
    private String path;
    @Value("directory")
    private String directory;
    @Value("DataStorageAddress")
    private String dataStorageAddress;

    public static BigInteger GAS_PRICE = BigInteger.valueOf(20_0000L);
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_30000L);

    @Test
    public void testWrite(){

        long l1 = System.currentTimeMillis();
        //for (int i = 0; i < 3; i++){
            Mapping mapping = senseDataService.writeToBlockchain();
            senseDataService.insMapping(mapping);
        senseDataService.readFromBlockchain();
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
        //}


    }

    @Test
    public void testRead(){
        senseDataService.readFromBlockchain();

    }

    @Test
    public void testTrans() {
        transactionService.buyerGenerateTransaction("temptype004");
        transactionService.sellerGenerateTransaction("temptype004");
        transactionService.generateCheque(100);
        List<String> strings = transactionService.extractLogs();
        System.out.println("日志打印开始：--------------------------");
        for(int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }
    }

    @Test
    public void testSearch(){
        List<SenseData> dataType = senseDataService.getSenseDataByDataType("temptype004");
        for (int i = 0 ; i < dataType.size(); i++) {
            System.out.println(dataType.get(i));
        }
    }

    @Test
    public void testGetMapping() {
        List<Mapping> mapping = transactionService.getMapping("temptype004");
        for (int i = 0;i < mapping.size(); i++) {
            System.out.println(mapping.get(i).toString());

        }
    }

    @Test
    public void dataController() {
        dataController.firstTransaction("temptype004");
    }

}
