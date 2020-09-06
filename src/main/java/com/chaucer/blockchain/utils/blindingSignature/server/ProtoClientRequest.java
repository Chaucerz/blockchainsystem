package com.chaucer.blockchain.utils.blindingSignature.server;

import com.chaucer.blockchain.utils.blindingSignature.Utils;
import com.chaucer.blockchain.utils.blindingSignature.client.Client;
import com.chaucer.blockchain.utils.blindingSignature.client.ClientRequest;
import com.chaucer.blockchain.utils.blindingSignature.client.impl.ClientImpl;
import com.chaucer.blockchain.utils.blindingSignature.client.impl.ClientRequestImpl;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.RSABlindingEngine;
import org.bouncycastle.crypto.generators.RSABlindingFactorGenerator;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;

import java.math.BigInteger;

/**
 * @author Chaucer
 * @date 2019-10-22 15:32
 */
public class ProtoClientRequest {
    private final byte[] clientID;
    private final RSABlindingParameters blindingParams;

    public ProtoClientRequest(RSAKeyParameters pub) {
        // Create a 128-bit globally unique ID for the client.
        clientID = Utils.getRandomBytes(16);

        // Generate a blinding factor using the server's public key.
        RSABlindingFactorGenerator blindingFactorGenerator
                = new RSABlindingFactorGenerator();
        blindingFactorGenerator.init(pub);

        BigInteger blindingFactor
                = blindingFactorGenerator.generateBlindingFactor();

        blindingParams = new RSABlindingParameters(pub, blindingFactor);
    }

    public ClientRequest generateClientRequest() throws CryptoException {
        // "Blind" the client and generate a client request to be signed by the
        // server.
        PSSSigner signer = new PSSSigner(new RSABlindingEngine(),
                new SHA1Digest(), 20);
        signer.init(true, blindingParams);

        signer.update(clientID, 0, clientID.length);

        byte[] sig = signer.generateSignature();

        return new ClientRequestImpl(sig);
    }

    public Client createClient(byte[] signature) {
        // "Unblind" the server's signature (so to speak) and create a new client
        // using the ID and the unblinded signature.
        RSABlindingEngine blindingEngine = new RSABlindingEngine();
        blindingEngine.init(false, blindingParams);

        byte[] s = blindingEngine.processBlock(signature, 0, signature.length);

        return new ClientImpl(clientID, s);
    }
}
