package com.chaucer.blockchain.utils.pseIdGen;

import java.math.BigInteger;
import java.util.Random;

public class ElGamal {
    static BigInteger p, g; // 大素数和本原元
    static BigInteger C, C1;// 密文
    static BigInteger x; 	// 私钥
    static BigInteger sk;   //用于对称加密的密钥

    /** 取一个大的随机素数P,和P的生成元a */
    public static void getRandomP(int alpha) {
        Random r = new Random();
        BigInteger q = null;
        while (true) {
            q = BigInteger.probablePrime(alpha, r);
            if (q.bitLength() != alpha)
                continue;
            if (q.isProbablePrime(10)) // 如果q为素数则再进一步计算生成元
            {
                p = q.multiply(new BigInteger("2")).add(BigInteger.ONE);
                if (p.isProbablePrime(10)) // 如果P为素数则OK~，否则继续
                    break;
            }
        }
        while (true) {
            g = BigInteger.probablePrime(p.bitLength() - 1, r);
            if (!g.modPow(BigInteger.ONE, p).equals(BigInteger.ONE)
                    && !g.modPow(q, p).equals(BigInteger.ONE)) {
                break;
            }
        }

    }

    /** 取随机数a */
    public static BigInteger getRandoma(BigInteger p) {
        BigInteger a;
        Random r = new Random();
        a = new BigInteger(p.bitLength() - 1, r);
        return a;
    }

    /** 计算密钥的值 */
    public static BigInteger calculatey(BigInteger x, BigInteger g, BigInteger p) {
        BigInteger y;
        y = g.modPow(x, p);
        return y;
    }

    /** 加密 */
    public static void encrypt(String m, BigInteger y, BigInteger p,
                               BigInteger g) {
        BigInteger message = new BigInteger(m.getBytes());// 把字串转成一个BigInteger对象
        Random r = new Random();
        BigInteger k;

        //产生随机数：gcd(k, p-1) = 1 , 0<=k<p-1
        while (true) {
            k = new BigInteger(p.bitLength() - 1, r);// 产生一0<=k<p-1的随机数
            if (k.gcd(p.subtract(BigInteger.ONE)).equals(BigInteger.ONE)) {// 如果随机数与p-1互质
                // 则选取成功,返回随机数k
                break;
            }
        }
        // 计算密文C,C1
        C = g.modPow(k, p);
        C1 = message.multiply(y.modPow(k, p)).mod(p);
        // 保存密文到对象中

    }
    public static String getSk(){
        return sk.toString();
    }
    public static String encrypt(String s){
        getRandomP(new BigInteger(s.getBytes()).bitLength());
        BigInteger message = new BigInteger(s.getBytes());
        //x是一个随机数作为私钥
        x = getRandoma(p);
        BigInteger y = calculatey(x, g, p);
        sk = y;
        Random r = new Random();
        BigInteger k;

        //产生随机数：gcd(k, p-1) = 1 , 0<=k<p-1
        while (true) {
            k = new BigInteger(p.bitLength() - 1, r);// 产生一0<=k<p-1的随机数
            if (k.gcd(p.subtract(BigInteger.ONE)).equals(BigInteger.ONE)) {// 如果随机数与p-1互质
                // 则选取成功,返回随机数k
                break;
            }
        }
        C = g.modPow(k, p);
        C1 = message.multiply(y.modPow(k, p)).mod(p);
        return C + "," + C1;
    }

    public static String decrypt(){
        BigInteger scy = C1.multiply(C.modPow(x.negate(), p)).mod(p);
        String str = new String(scy.toByteArray());// 把返回的结果还原成一个字串
        return str;
    }

    /** 解密 */
    public static String decrypt(BigInteger C, BigInteger C1, BigInteger a,
                                 BigInteger p) {
        BigInteger scy = C1.multiply(C.modPow(a.negate(), p)).mod(p);
        String str = new String(scy.toByteArray());// 把返回的结果还原成一个字串
        return str;
    }

    public static void main(String[] args) {
        String s = "25zg032qiu2351351";
        encrypt(s);
        System.out.println("加密后的密文为:" + C + "," + C1);

        System.out.println(decrypt());

    }

}
