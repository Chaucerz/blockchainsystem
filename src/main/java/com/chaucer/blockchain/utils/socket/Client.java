package com.chaucer.blockchain.utils.socket;

/**
 * @author Chaucer
 * @date 2019-11-19 17:24
 */

import com.chaucer.blockchain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * 该类作为买家，和卖家连接之后可进行交易
 */
@Component
public class Client {

    @Autowired
    private TransactionService transactionServiceImpl;

    public List<String> getBuyerLogs() {
         transactionServiceImpl.buyerGenerateTransaction("temptype004");
        List<String> buyerLogs = transactionServiceImpl.extractBuyerLogs();
        return buyerLogs;
    }

    public  void testClient() {
        System.out.println("正在向买家请求连接。。。");
        Scanner keybordscanner = null;
        Scanner inScanner = null;
        PrintWriter pwtoserver = null;
        Socket socket = null;

        try {
            socket = new Socket("localhost", 6666);
            inScanner = new Scanner(socket.getInputStream());
            System.out.println(inScanner.nextLine());
            pwtoserver =new  PrintWriter(socket.getOutputStream());
            System.out.println("卖家：");
            keybordscanner = new Scanner(System.in);
            int count = 0;
            List<String> buyerLogs = getBuyerLogs();
            while (keybordscanner.hasNextLine()) {
                //买家的消息 keyboardData
                String  keyboardData = keybordscanner.nextLine();
                System.out.println("买家：" + keyboardData);
                System.out.println(buyerLogs.get(count));
                //写到服务端的控制台
                pwtoserver.println(keyboardData);
                pwtoserver.println(buyerLogs.get(count));
                pwtoserver.flush();
                //等待接受卖家消息 indata
                String indata = inScanner.nextLine();
                System.out.println("卖家：" + indata);
                System.out.println("买家：");
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            keybordscanner.close();
            pwtoserver.close();
            inScanner.close();
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client().testClient();
    }
}
