package com.ang.firstweb.mynio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by ang on 2019/9/29.
 */
public class NIOTry {
    public static void main(String[] args) {
        File file = new File("D://a.txt");
        try (RandomAccessFile rw = new RandomAccessFile(file, "rw");
             FileChannel channel = rw.getChannel()) {
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            channel.position(file.length());
            channel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
