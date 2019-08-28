package com.chaucer.blockchain;

import com.chaucer.blockchain.contract.DataStorageContract;
import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import com.chaucer.blockchain.service.SenseDataService;
import com.chaucer.blockchain.service.impl.SenseDataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

        @RunWith(SpringRunner.class)
        @SpringBootTest
        public class BlockchainApplicationTests {

            @Test
            public void contextLoads() {
            }
            @Autowired
            SenseDataService senseDataService;

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

                for(int i = 0; i < 5; i++){
                    Mapping mapping = senseDataService.writeToBlockchain();
                    senseDataService.insMapping(mapping);
                }

            }

            @Test
            public void testRead(){
                senseDataService.readFromBlockchain();

            }

}
