package com.chaucer.blockchain.utils.ECC;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Chaucer
 * @date 2019-10-18 14:50
 */
public class ECC {

    public PGkParing eccGenerate() {
        PGkParing pGkParing = null;
        try {
            pGkParing = new PGkParing();
            Random random = new Random();
            int n = random.nextInt(120) % (120 - 85 + 1) + 85;
            System.out.println(n);
            Random r1 = new Random(n);
            BigInteger a = new BigInteger(60, r1);
            pGkParing.setK(a);
            System.out.println("Alice:" + a);
            EllipticCurve e = new EllipticCurve(new BigInteger("1"),
                    new BigInteger("6"), new BigInteger("11"));
            System.out.println("EllipticCurve: " + e + " created succesfully!");

            //生成基点G
            ECPoint G = new ECPoint(e, new BigInteger("2"), new BigInteger("7"));
            pGkParing.setG(G);
            ECPoint P;
            //计算A=aG
            P = G.multiply(a);
            pGkParing.setP(P);
            System.out.println("A=a*G: "+a + " * " + G + " = " + P);
        } catch (InsecureCurveException ice) {
            ice.getErrorString();
        } catch (NotOnMotherException nome) {
            nome.getErrorString();
        }
        return pGkParing;
    }

    public static void main(String[] args) {

    }
}
