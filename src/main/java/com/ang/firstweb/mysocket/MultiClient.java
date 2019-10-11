package com.ang.firstweb.mysocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by adimn on 2019/10/11.
 */
public class MultiClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8002);

            PrintStream printStream = new PrintStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inline;
            new ClientThread(socket).start();

            while ((inline = reader.readLine()) != null) {
                printStream.println(inline);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ClientThread extends Thread {
    private final BufferedReader reader;
    private Socket socket;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
         reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("收到:" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
