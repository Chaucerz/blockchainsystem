package com.chaucer.blockchain.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Chaucer
 * @date 2020-05-02 15:29
 */
@Getter
@Setter
@ToString
public class Ticket {

    //处罚的金额
    private int punish_val;

    //时间戳
    private String timeStamp;

    //交易记录，用支票来表现
    private String tranRecord;

    public Ticket() {

    }
}
