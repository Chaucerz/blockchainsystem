package com.chaucer.blockchain.service;

import com.chaucer.blockchain.pojo.Mapping;
import com.chaucer.blockchain.pojo.SenseData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface SenseDataService {


    //将区块链上已经存在的数据作为SenseData读取出来
     int readFromBlockchain();

    //将已经读取到的数据存放到数据库中
    //int ins(ArrayList<SenseData> sd);

    //生成新的SenseData对象存入数据库，同时上传到区块链。
    Mapping writeToBlockchain();

    //同时将mapping也存到数据库
    int insMapping(Mapping mapping);

    //根据数据类型查询对应的信息
    public List<SenseData> getSenseDataByDataType(String dataType);
}
