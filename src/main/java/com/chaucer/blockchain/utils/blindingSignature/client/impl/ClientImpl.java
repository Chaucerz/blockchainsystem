package com.chaucer.blockchain.utils.blindingSignature.client.impl;

import com.chaucer.blockchain.utils.blindingSignature.client.Client;

/**
 * @author Chaucer
 * @date 2019-10-22 15:16
 */
public class ClientImpl implements Client {

    private final byte[] id;
    private final byte[] signature;

    public ClientImpl(byte[] id, byte[] signature) {
        this.id = id;
        this.signature = signature;
    }


    @Override
    public byte[] getID() {
        return id;
    }

    @Override
    public byte[] getSignature() {
        return signature;
    }
}
