package com.chaucer.blockchain.utils.blindingSignature.client.impl;

import com.chaucer.blockchain.utils.blindingSignature.client.ClientRequest;

/**
 * @author Chaucer
 * @date 2019-10-22 15:18
 */
public class ClientRequestImpl implements ClientRequest {
    private final byte[] message;

    public ClientRequestImpl(byte[] message) {
        this.message = message;
    }

    public byte[] getMessage() {
        return message;
    }
}
