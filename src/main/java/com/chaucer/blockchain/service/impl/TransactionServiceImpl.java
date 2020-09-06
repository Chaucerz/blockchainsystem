package com.chaucer.blockchain.service.impl;

import com.chaucer.blockchain.contract.TransactionContract;
import com.chaucer.blockchain.mapper.MappingMapper;
import com.chaucer.blockchain.pojo.Cheque;
import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import com.chaucer.blockchain.service.TransactionService;
import com.chaucer.blockchain.utils.ECC.*;
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
import java.util.*;

/**
 * @author Chaucer
 * @date 2019-10-14 14:38
 */
@Component
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    MappingMapper mappingMapper;

    public TransactionContract transactionContract;

    public static BigInteger GAS_PRICE = BigInteger.valueOf(20_0000L);
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_30000L);

    @Value("password")
    private String password;
    @Value("path")
    private String path;
    @Value("directory")
    private String directory;
    @Value("TransactionAddress")
    private String transactionAddress;

    private List<String> logs = new ArrayList<>();

    //交易双方日志
    private List<String> buyer = new ArrayList<>();
    private List<String> seller = new ArrayList<>();

    private BigInteger k;

    private Cheque cheque = new Cheque();

    private String accNoBuyer;

    private String accNoSeller;

    @Override
    public int buyerGenerateTransaction(String label) {
        int transactionCount = 0;
        try{
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
            transactionContract = TransactionContract.load("0x65571d0322462aFC1638f705F67a76ddbBbCF785",web3j,credentials,contractGasProvider);
            BigInteger total = transactionContract.returnTotal().send();
            transactionCount = new Integer(total.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
        String timeStamp = simpleDateFormat.format(date);

        transactionContract.addTransaction(label, timeStamp).sendAsync();
        accNoBuyer = accGenerator();
        //生成SN
        String serialNum = SNgenerator();
        log.info(serialNum);
        cheque.setSN(serialNum);

        //椭圆曲线上的难题
        ECC ecc = new ECC();
        PGkParing pGkParing = ecc.eccGenerate();
        cheque.setPGkParing(pGkParing);

        //秘密值k
        k = pGkParing.getK();
        cheque.setK_buyer(k);
        logs.add("买家向区块链发送一次交易：" + "< SN:" + serialNum + ", label:" + label + ", 买家的PGpairing:(" + pGkParing.getP() + "," + pGkParing.getG() + ") >");
        buyer.add("买家向区块链发送一次交易：" + "< SN:" + serialNum + ", label:" + label + ", 买家的PGpairing:(" + pGkParing.getP() + "," + pGkParing.getG() + ") >");
        return transactionCount;

    }

    @Override
    public void sellerGenerateTransaction(String label) {
        ECC ecc = new ECC();
        PGkParing pGkParing = ecc.eccGenerate();
        k = pGkParing.getK();
        cheque.setK_seller(k);
        logs.add("卖家确认开始交易：" + "< label:" + label + "，卖家的PGpairing:(" + pGkParing.getP() + "," + pGkParing.getG()+ ")");
        seller.add("卖家确认开始交易：" + "< label:" + label + "，卖家的PGpairing:(" + pGkParing.getP() + "," + pGkParing.getG()+ ")");
    }

    @Override
    public void generateCheque(int val) {
        accNoSeller = accGenerator();
        cheque.setAcc_no_buyer(accNoBuyer);
        cheque.setAcc_no_seller(accNoSeller);
        cheque.setVal(val);
        logs.add("生成的支票为：" + cheque.toString());
        buyer.add("生成的支票为：" + cheque.toString());
    }

    @Override
    public List<Mapping> getMapping(String label) {
        Example example = new Example(Mapping.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataType",label);
        List<Mapping> mappings = mappingMapper.selectByExample(example);
        return mappings;
    }



    @Override
    public List<String> extractLogs() {
        return logs;
    }

    @Override
    public List<String> extractBuyerLogs() {
        return buyer;
    }

    @Override
    public List<String>  extractSellerLogs() {
        return seller;
    }


    @Override
    public String SNgenerator() {
        int hashcodeV = UUID.randomUUID().toString().hashCode();
        if (hashcodeV < 0) {
            hashcodeV = - hashcodeV;
        }
        String SN = String.format("%010d",hashcodeV);
        System.out.println(SN);
        return SN;
    }

    public String accGenerator() {
        int hashcodeV = UUID.randomUUID().toString().hashCode();
        if (hashcodeV < 0) {
            hashcodeV = - hashcodeV;
        }
        return String.format("%012d",hashcodeV);
    }

    @Override
    public ECPoint generateSessionKey(){

        EllipticCurve e = null;
        ECPoint G = null;
        Random r1 = new Random(100);
        BigInteger a = new BigInteger(60, r1);
        Random r2 = new Random(20);
        BigInteger b = new BigInteger(50, r2);
        try {
            e = new EllipticCurve(new BigInteger("1"),
                    new BigInteger("6"), new BigInteger("11"));
            System.out.println("EllipticCurve: " + e + " created succesfully!");
        } catch (InsecureCurveException ex) {
            ex.printStackTrace();
        }
        try {
            G = new ECPoint(e, new BigInteger("2"), new BigInteger("7"));
        } catch (NotOnMotherException ex) {
            ex.printStackTrace();
        }
        ECPoint A, B;
        A = G.multiply(a);
        System.out.println("A=a*G: "+a + " * " + G + " = " + A);
        B = G.multiply(b);// 计算B=b*G
        System.out.println("B=b*G: "+b + " * " + G + " = " + B);
        ECPoint Q1, Q2;
        Q1 = A.multiply(b);// Bob收到Alice传递的A，计算Q =b*A
        Q2 = B.multiply(a);// Alice收到Bob传递的B，计算Q`=a*B
        System.out.println("Q1:"+Q1);
        System.out.println("Q2:"+Q2);
        return Q1;
    }


}
