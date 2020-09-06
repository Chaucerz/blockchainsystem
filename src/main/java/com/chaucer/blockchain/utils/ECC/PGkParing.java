package com.chaucer.blockchain.utils.ECC;

import java.math.BigInteger;

/**
 * 等式P = kG,{P,G}作为公钥，k为私钥，在k未知的情况下，求出P是困难的。
 * 以上等式的可证明安全性基于离散对数域。
 * @author Chaucer
 * @date 2019-10-18 16:00
 */
public class PGkParing {
   private ECPoint P;
   private ECPoint G;
   private BigInteger k;

    public PGkParing() {
    }

    public ECPoint getP() {
        return P;
    }

    public void setP(ECPoint p) {
        P = p;
    }

    public ECPoint getG() {
        return G;
    }

    public void setG(ECPoint g) {
        G = g;
    }

    public BigInteger getK() {
        return k;
    }

    public void setK(BigInteger k) {
        this.k = k;
    }
}
