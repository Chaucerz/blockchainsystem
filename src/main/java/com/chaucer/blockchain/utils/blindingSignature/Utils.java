package com.chaucer.blockchain.utils.blindingSignature;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Chaucer
 * @date 2019-10-22 15:34
 */
public class Utils {
    public static byte[] getRandomBytes(int count) {
        byte[] bytes = new byte[count];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }

    // generate 2048-bit sized RSA key pair
    public static AsymmetricCipherKeyPair generateKeyPair() {
        RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
        generator.init(new RSAKeyGenerationParameters(
                new BigInteger("101011", 16),
                new SecureRandom(),
                2048,
                80));
        return generator.generateKeyPair();
    }
}
