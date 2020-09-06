package com.chaucer.blockchain.mapper;

import com.chaucer.blockchain.pojo.Transaction;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Chaucer
 * @date 2019-10-14 16:47
 */
@org.apache.ibatis.annotations.Mapper
@Repository
public interface TransactionMapper extends Mapper<Transaction> {

}
