package com.ang.firstweb.mynio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by adimn on 2019/10/10.
 */
public class FileLock {
    public static void main(String[] args) {


        append();
    }

    public static void filelock(){
        File file = new File("D:", "a.txt");
        try (
                FileChannel channel = new FileOutputStream(file).getChannel();
        ) {
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            channel.lock();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reader() {
        File file = new File("D:", "a.txt");
        try (
                Reader reader = new FileReader(file);
                BufferedReader bf = new BufferedReader(reader);
        ) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void append() {
        try (RandomAccessFile raf = new RandomAccessFile("D://a.txt", "rw")) {
            raf.seek(raf.length());
            raf.write("/r/n 我们是追加".getBytes());
            raf.seek(0);
            String line ;
            while((line = raf.readLine())!=null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


