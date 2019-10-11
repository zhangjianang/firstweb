package com.ang.firstweb.mysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by adimn on 2019/10/11.
 */
public class MultiServer {

    public static List<Socket> usocket;

    public static void main(String[] args) throws IOException {

        usocket = Collections.synchronizedList(new ArrayList<Socket>());

        ServerSocket serverSocket = new ServerSocket(8002);

        while (true) {
//            这里会阻塞哈
            Socket accept = serverSocket.accept();
            System.out.println("有人来了么？");
            usocket.add(accept);

            new ServerThread(accept).start();

        }

    }
}

class ServerThread extends Thread {
    private final int port;
    private final BufferedReader reader;

    public ServerThread(Socket socket) throws IOException {
        port = socket.getPort();
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("服务器收到：" + line);
                tellOther(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tellOther(String line) throws IOException {
        for (Socket per : MultiServer.usocket) {
            if(per.getPort()== port){
                continue;
            }
            PrintStream printStream = new PrintStream(per.getOutputStream());
            printStream.println(line);
        }
    }
}
