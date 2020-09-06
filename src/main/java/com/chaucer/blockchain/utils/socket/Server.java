package com.chaucer.blockchain.utils.socket;

/**
 * @author Chaucer
 * @date 2019-11-19 17:23
 */

import com.chaucer.blockchain.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * 该类作为卖家，等待买家的连接
 */
@Slf4j
@Component
public class Server {

    @Autowired
    private TransactionService transactionServiceImpl;


    public List<String> getSellerLogs() {
        transactionServiceImpl.sellerGenerateTransaction("temptype004");
        List<String> sellerLogs = transactionServiceImpl.extractSellerLogs();
        return sellerLogs;
    }

    public void testServer() {

        //创建一个服务器
        System.out.println("等待买家连接……");
        PrintWriter pwToClient = null;
        Scanner keyboardScanner = null;
        Scanner inScanner = null;

        //该类是socket通信的服务器基类
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(6666);
            //该方法监听一个客户端连接，并在连接成功之前处于阻塞状态
            Socket socket = ss.accept();
            System.out.println("买家已成功连接……");
            //字符输出流
            pwToClient = new PrintWriter(socket.getOutputStream());
            pwToClient.println("已经成功连接到卖家！" + "\t" + "请发言：");
            pwToClient.flush();
            keyboardScanner = new Scanner(System.in);
            inScanner = new Scanner(socket.getInputStream());

            int count = 0;
            List<String> sellerLogs = getSellerLogs();
            while (inScanner.hasNextLine() && count < sellerLogs.size()) {
                String indata = inScanner.nextLine();
                System.out.println("买家：" + indata);
                System.out.println("卖家：" );
                String keyboardData = keyboardScanner.nextLine();
                System.out.println("卖家："+ keyboardData);
                System.out.println(sellerLogs.get(count));
                pwToClient.println(keyboardData);
                pwToClient.println(sellerLogs.get(count));
                pwToClient.flush();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pwToClient.close();
            keyboardScanner.close();
            inScanner.close();
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       new Server().testServer();
    }
}
