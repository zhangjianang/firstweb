package com.ang.firstweb.mysocket;

import java.io.*;
import java.net.Socket;

/**
 * Created by adimn on 2019/10/10.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(inputStreamReader);
        ) {

            while (true) {

                String line = reader.readLine();
                if ("exit".equals(line)) {
                    System.exit(1);
                }
                Socket socket = new Socket("127.0.0.1", 8001);
                OutputStream outputStream = socket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                writer.write(line);
                writer.flush();

                socket.close();

            }
        }
    }
}
