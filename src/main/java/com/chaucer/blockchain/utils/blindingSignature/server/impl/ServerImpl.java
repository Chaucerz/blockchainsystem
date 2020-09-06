package com.chaucer.blockchain.utils.blindingSignature.server.impl;

import com.chaucer.blockchain.utils.blindingSignature.server.Server;
import com.chaucer.blockchain.utils.blindingSignature.client.Client;
import com.chaucer.blockchain.utils.blindingSignature.client.ClientRequest;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;

/**
 * @author Chaucer
 * @date 2019-10-22 15:38
 */
public class ServerImpl implements Server {
    private final AsymmetricCipherKeyPair keys;

    public ServerImpl(AsymmetricCipherKeyPair keys) {
        this.keys = keys;
    }

    @Override
    public RSAKeyParameters getPublic() {
        return (RSAKeyParameters) keys.getPublic();
    }

    @Override
    public byte[] sign(ClientRequest clientRequest) {
        // Sign the client request using server's private key.
        byte[] message = clientRequest.getMessage();

        RSAEngine engine = new RSAEngine();
        engine.init(true, keys.getPrivate());

        return engine.processBlock(message, 0, message.length);
    }

    @Override
    public boolean verify(Client client) {
        // Verify that the client has a valid signature using server's public key.
        byte[] id = client.getID();
        byte[] signature = client.getSignature();

        PSSSigner signer = new PSSSigner(new RSAEngine(), new SHA1Digest(), 20);
        signer.init(false, keys.getPublic());

        signer.update(id, 0, id.length);

        return signer.verifySignature(signature);
    }
}
