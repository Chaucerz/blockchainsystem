package com.chaucer.blockchain.utils.blindingSignature.test;

import com.chaucer.blockchain.utils.blindingSignature.Utils;
import com.chaucer.blockchain.utils.blindingSignature.client.Client;
import com.chaucer.blockchain.utils.blindingSignature.client.ClientRequest;
import com.chaucer.blockchain.utils.blindingSignature.server.*;
import com.chaucer.blockchain.utils.blindingSignature.server.impl.ServerImpl;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.util.encoders.Base64;

/**
 * @author Chaucer
 * @date 2019-10-22 15:42
 */
public class TestBlindingSignature {

//    public static void main(String[] args) throws CryptoException {
//        Server server =  new ServerImpl(Utils.generateKeyPair());
//        // Create Client using Server's public key.
//        com.chaucer.blockchain.utils.blindingSignature.server.
//                ProtoClientRequest clientRequest = new com.chaucer.blockchain.utils.blindingSignature.server.
//                ProtoClientRequest(server.getPublic());
//
//        // Generate a client request.
//        ClientRequest clientnRequest = clientRequest.generateClientRequest();
//
//        printClientRequest(clientnRequest);
//
//        // Ask the server to sign the client request.
//
//        // Note: In practice the server will be on a remote server and this will
//        // be an asynchronous operation. The server will verify Alice's
//        // credentials before signing the request.
//        // Needless to say, the connection to the server would have to be over a
//        // secure channel.
//
//        byte[] signature = server.sign(clientnRequest);
//
//        printServerSignature(signature);
//
//        // Create a new client using the bank's signature.
//        Client client = clientRequest.createClient(signature);
//
//        printClient(client);
//
//        // The signature on the client is different from the one the server
//        // returned earlier. However the server should still be able to validate the client
//        boolean valid = server.verify(client);
//        if (valid) {
//            System.out.println("client validated");
//        } else {
//            System.out.println("you are either not registered, or an intruder");
//        }
//    }
//
//    private static void printClientRequest(ClientRequest clientRequest) {
//        System.out.println("message signed by server:");
//        System.out.println("");
//        System.out.println(Base64.toBase64String(clientRequest.getMessage()));
//        System.out.println("");
//    }
//
//    private static void printServerSignature(byte[] signature) {
//        System.out.println("server's signature:");
//        System.out.println("");
//        System.out.println(Base64.toBase64String(signature));
//        System.out.println("");
//    }
//
//    private static void printClient(Client client) {
//        System.out.println("client:");
//        System.out.println("");
//        System.out.println(Base64.toBase64String(client.getID()));
//        System.out.println("");
//        System.out.println(Base64.toBase64String(client.getSignature()));
//        System.out.println("");
//    }
}
