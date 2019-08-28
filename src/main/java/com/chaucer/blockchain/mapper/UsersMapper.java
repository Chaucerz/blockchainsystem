package com.chaucer.blockchain.mapper;

import com.chaucer.blockchain.pojo.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Chaucer
 * @date 2019-08-22 15:35
 */

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UsersMapper extends Mapper<Users> {
}
