package com.ang.firstweb.mysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by adimn on 2019/10/10.
 */
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8001);
             ) {
            while (true) {
                Socket accept = serverSocket.accept();
                InputStreamReader inputreader = new InputStreamReader(accept.getInputStream());
                BufferedReader br = new BufferedReader(inputreader);
                System.out.println("server 收到"+br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
