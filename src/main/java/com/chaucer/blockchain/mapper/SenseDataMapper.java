package com.chaucer.blockchain.mapper;


import com.chaucer.blockchain.pojo.SenseData;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface SenseDataMapper extends Mapper<SenseData> {
}
