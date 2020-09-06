package com.chaucer.blockchain.service.impl;

import com.chaucer.blockchain.contract.DataStorageContract;
import com.chaucer.blockchain.mapper.MappingMapper;
import com.chaucer.blockchain.mapper.SenseDataMapper;
import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import com.chaucer.blockchain.service.SenseDataService;
import com.chaucer.blockchain.utils.AES.AESutil;
import com.chaucer.blockchain.utils.pseIdGen.ElGamal;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import tk.mybatis.mapper.entity.Example;


import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class SenseDataServiceImpl implements SenseDataService {

    @Autowired
    SenseDataMapper senseDataMapper;

    @Autowired
    MappingMapper mappingMapper;

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

    public DataStorageContract dataStorageContract = null;

    Mapping mapping = new Mapping();

    private List<String> list = new ArrayList<>();

    public int readFromBlockchain(){
        SenseData senseData = new SenseData();

        BigInteger total = null;
        try {
            Web3j web3j = Web3j.build(new HttpService("http://localhost:8546"));
            Credentials credentials = WalletUtils.loadCredentials("123456", "C:\\\\Program Files\\\\Geth\\\\data\\\\keystore\\\\UTC--2019-06-22T03-27-01.614782300Z--8a3cf7817722c9b64b315c457002c66380ccb7e5");
            ContractGasProvider contractGasProvider = new ContractGasProvider() {
                @Override
                public BigInteger getGasPrice(String s) {
                    return GAS_PRICE;
                }

                @Override
                public BigInteger getGasPrice() {
                    return GAS_PRICE;
                }

                @Override
                public BigInteger getGasLimit(String s) {
                    return GAS_LIMIT;
                }

                @Override
                public BigInteger getGasLimit() {
                    return GAS_LIMIT;
                }
            };
            dataStorageContract = DataStorageContract.load("0x59D47eC5a4f02d9faB923138C3296292DeB479D9",web3j,credentials,contractGasProvider);
            total = dataStorageContract.returnTotal().send();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = new Integer(total.toString());
        Random random = new Random();
        try {
            for(int i = 0; i < n; i++){
                senseData.setSenseData(dataStorageContract.getData(BigInteger.valueOf(i)).send());
                int tempType = random.nextInt(10) + 1;
                senseData.setDataType("temptype00" + tempType);
                senseData.setPseId(dataStorageContract.getPseId(BigInteger.valueOf(i)).send());
                senseData.setTimeStp(dataStorageContract.getTimeStamp(BigInteger.valueOf(i)).send());
                senseData.setDataType(dataStorageContract.getDataType(BigInteger.valueOf(i)).send());
                senseDataMapper.insert(senseData);
                System.out.println(senseData.toString());
            }
            System.out.println("从区块链上读取了" + n + "条数据");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }


    @Override
    public Mapping writeToBlockchain() {
        try {
            Web3j web3j = Web3j.build(new HttpService("http://localhost:8546"));
            Credentials credentials = WalletUtils.loadCredentials("123456", "C:\\\\Program Files\\\\Geth\\\\data\\\\keystore\\\\UTC--2019-06-22T03-27-01.614782300Z--8a3cf7817722c9b64b315c457002c66380ccb7e5");
            ContractGasProvider contractGasProvider = new ContractGasProvider() {
                @Override
                public BigInteger getGasPrice(String s) {
                    return GAS_PRICE;
                }

                @Override
                public BigInteger getGasPrice() {
                    return GAS_PRICE;
                }

                @Override
                public BigInteger getGasLimit(String s) {
                    return GAS_LIMIT;
                }

                @Override
                public BigInteger getGasLimit() {
                    return GAS_LIMIT;
                }
            };
            dataStorageContract = DataStorageContract.load("0x59D47eC5a4f02d9faB923138C3296292DeB479D9",web3j,credentials,contractGasProvider);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Random random = new Random();

        //生成加密之后的伪身份
        int n = random.nextInt(20) % (20 - 1 + 1) + 1;
        String realId = "IoT device" + n;
        String pseId = ElGamal.encrypt(realId);
        mapping.setRealId(realId);
        mapping.setPseId(pseId);
        list.add("开始执行伪身份生成算法：…………");
        list.add("生成的伪身份为：" + pseId);
        //log.info("生成的伪身份为：" + pseId);

        //生成温度数据
        int temperature  = random.nextInt(40) % (40 - 30 + 1) + 30;
        String senseData = "The current temperature is " + temperature + "degrees";
        String password = ElGamal.getSk();
        //System.out.println(password);
        list.add("生成用于加密终端设备数据的密钥：" + password);
        //log.info("生成用于加密终端设备数据的密钥：" + password);

        byte[] encrypt = AESutil.encrypt(senseData, password);
        String encryptSenseData = Base64.encode(encrypt);
        //System.out.println(encryptSenseData);
        list.add("加密之后的数据：" + encryptSenseData);
        //log.info("加密之后的数据：" + encryptSenseData);

        byte[] decrypt = AESutil.decrypt(encrypt, password);
        String decryptData = new String(decrypt);
        //System.out.println(decryptData);

        int tempType = random.nextInt(10) + 1;
        String dataType = "temptype00" + tempType;
        mapping.setDataType(dataType);

        //生成时间戳
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
        String timeStamp = simpleDateFormat.format(date);
        mapping.setTimeStp(timeStamp);
        list.add("记录当前时间戳：" + timeStamp);
        //log.info("记录当前时间戳：" + timeStamp);

        dataStorageContract.addData(encryptSenseData,pseId,timeStamp,dataType).sendAsync();
        return mapping;
    }


    @Override
    public List<String> extractLogs() {
        return list;
    }

    @Override
    public int insMapping(Mapping mapping) {
        int index = mappingMapper.insert(mapping);
        if(index > 0){
            System.out.println("成功将Mapping存入DHT");
        } else {
            System.out.println("将Mapping存入DHT失败");
        }
        return index;
    }

    @Override
    public List<SenseData> getSenseDataByDataType(String dataType){
        Example example = new Example(SenseData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataType", dataType);
        List<SenseData> list = senseDataMapper.selectByExample(example);
        return list;
    }


}
