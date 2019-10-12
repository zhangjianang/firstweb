package com.ang.firstweb.mynio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by adimn on 2019/10/12.
 */
public class ZeroCopy {
    public static void main(String[] args) {
        String fname = "C:\\Users\\adimn\\Desktop\\bx.txt";
        String fname2 = "C:\\Users\\adimn\\Desktop\\bx1.txt";
        String toname = "D:\\aax.txt";
        String toname2 = "D:\\bbx.txt";
        String toname3 = "D:\\ccx.txt";
//        long start = System.currentTimeMillis();
//        othercopy(fname2,toname3);
//        System.out.println(System.currentTimeMillis() - start);
//
        long start1 = System.currentTimeMillis();
        traditioncopy(fname,toname2);
        System.out.println(System.currentTimeMillis() - start1);

//        long start2 = System.currentTimeMillis();
//        zerocopy(fname,toname);
//        System.out.println(System.currentTimeMillis() - start2);
    }

    public static void zerocopy(String fname, String toname) {
        try (
                FileChannel from = new RandomAccessFile(fname, "r").getChannel();
                FileChannel to = new RandomAccessFile(toname, "rw").getChannel();
        ) {

            from.transferTo(0, from.size(), to);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void traditioncopy(String fname, String toname) {
        try (
                FileChannel from = new RandomAccessFile(fname, "r").getChannel();
                FileChannel to = new RandomAccessFile(toname, "rw").getChannel();
        ) {

            ByteBuffer allocate = ByteBuffer.allocate(1024);

            while (from.read(allocate)>0) {
                allocate.flip();
                to.write(allocate);
                allocate.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void othercopy(String fname,String toname){
        try (
                FileChannel from = new RandomAccessFile(fname, "r").getChannel();
                FileChannel to = new RandomAccessFile(toname, "rw").getChannel();
        ) {

            MappedByteBuffer map = from.map(FileChannel.MapMode.READ_ONLY, 0, from.size());
            to.write(map);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
