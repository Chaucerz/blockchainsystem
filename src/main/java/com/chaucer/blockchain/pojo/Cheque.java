package com.chaucer.blockchain.pojo;

import com.chaucer.blockchain.utils.ECC.PGkParing;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * @author Chaucer
 * @date 2019-10-18 16:27
 */
@Getter
@Setter
@ToString
public class Cheque {

    //支票的序列号
    private String SN;
    //支票的面额
    private int val;
    //P = kG
    private PGkParing pGkParing;
    //买家的账户
    private String acc_no_buyer;
    //买家的秘密值k
    private BigInteger k_buyer;
    //卖家的秘密值k
    private BigInteger k_seller;
    //卖家的账户
    private String acc_no_seller;

    public Cheque() {

    }
}
