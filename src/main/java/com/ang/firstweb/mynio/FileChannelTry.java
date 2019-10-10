package com.ang.firstweb.mynio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by adimn on 2019/10/9.
 */
public class FileChannelTry {
    public static void main(String[] args) {
        append();
//        smallappend();
    }

    public static void copy(){
        File file = new File("D:\\a.txt");

        try (
                FileChannel inchannel = new FileInputStream(file).getChannel();
                FileChannel outchannel = new FileOutputStream("D:\\b.txt").getChannel();
        ) {
            MappedByteBuffer mapbuffer = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            outchannel.write(mapbuffer);

            Charset charset = Charset.forName("GBK");

            mapbuffer.clear();
//            mapbuffer.flip();

            CharsetDecoder charsetDecoder = charset.newDecoder();
            CharBuffer charBuffer = charsetDecoder.decode(mapbuffer);
            System.out.println(charBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void append(){
        File file = new File("D:\\b.txt");
        try (
                RandomAccessFile raf = new RandomAccessFile(file,"rw");
                FileChannel channel = raf.getChannel()
        ){

  // TODO: 2019/10/10 添加多个buffer到同一个channel中？
            MappedByteBuffer mapbuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());

            channel.position(file.length());
            channel.write(mapbuffer);

            byte[] hui = "---\r\n".getBytes();
            ByteBuffer appbuffer = ByteBuffer.allocate(hui.length);
            appbuffer.put(hui);
            channel.write(appbuffer);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void smallappend(){
        File file = new File("D:\\b.txt");

        try(
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                FileChannel channel = raf.getChannel();
                ){
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            while(channel.read(allocate)!=-1){
                allocate.flip();

                Charset gbk = Charset.forName("GBK");
                CharsetDecoder charsetDecoder = gbk.newDecoder();
                CharBuffer decode = charsetDecoder.decode(allocate);
                System.out.println(decode);
                allocate.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
