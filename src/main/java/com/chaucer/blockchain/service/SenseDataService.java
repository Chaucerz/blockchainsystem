package com.chaucer.blockchain.service;

import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SenseDataService {

    int readFromBlockchain();

    //生成新的SenseData对象存入数据库，同时上传到区块链。
    Mapping writeToBlockchain();

    //同时将mapping也存到数据库
    int insMapping(Mapping mapping);

    //根据数据类型查询对应的信息
    List<SenseData> getSenseDataByDataType(String dataType);

    //提取生成数据时的日志信息
    List<String> extractLogs();


}
