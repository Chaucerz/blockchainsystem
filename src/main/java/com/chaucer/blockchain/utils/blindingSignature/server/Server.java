package com.chaucer.blockchain.utils.blindingSignature.server;

import com.chaucer.blockchain.utils.blindingSignature.client.Client;
import com.chaucer.blockchain.utils.blindingSignature.client.ClientRequest;
import org.bouncycastle.crypto.params.RSAKeyParameters;

/**
 * @author Chaucer
 * @date 2019-10-22 15:20
 */
public interface Server {

    RSAKeyParameters getPublic();

    byte[] sign(ClientRequest clientRequest);

    boolean verify(Client client);
}
