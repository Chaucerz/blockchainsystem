package com.chaucer.blockchain.utils.blindingSignature.client;

/**
 * @author Chaucer
 * @date 2019-10-22 15:12
 */
public interface ClientRequest {

    // The message (blind) to be signed by the server
    byte[] getMessage();
}
