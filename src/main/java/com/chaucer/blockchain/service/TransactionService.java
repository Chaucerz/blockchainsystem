package com.chaucer.blockchain.service;

import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.utils.ECC.ECPoint;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chaucer
 * @date 2019-10-14 14:29
 */
@Service
public interface TransactionService {

    //发起一个交易并广播到区块链
    int buyerGenerateTransaction(String label);

    void sellerGenerateTransaction(String label);

    void generateCheque(int val);

    List<String> extractLogs();

    List<Mapping> getMapping(String label);

    String SNgenerator();

    List<String> extractBuyerLogs();

    List<String>  extractSellerLogs();

    //生成基于ECDH的会话密钥
    ECPoint generateSessionKey();
}
