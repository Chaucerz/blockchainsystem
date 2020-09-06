package com.chaucer.blockchain.utils.blindingSignature.client;

/**
 * @author Chaucer
 * @date 2019-10-22 15:10
 */
public interface Client {

    // The client's globally unique ID
    byte[] getID();

    // The server's signature for the client
    byte[] getSignature();
}
