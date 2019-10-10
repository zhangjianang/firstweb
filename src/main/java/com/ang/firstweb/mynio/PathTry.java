package com.ang.firstweb.mynio;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by adimn on 2019/10/10.
 */
public class PathTry {
    public static void main(String[] args) {
        watchTry();
    }

    public static void watchTry(){
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get("d:/").register(watchService, ENTRY_CREATE);

            while (true){
                WatchKey key = watchService.take();
                for(WatchEvent<?> event: key.pollEvents()){
                    System.out.println(event.context()+"发生了"+event.kind()+"事件");
                }
                boolean valid = key.reset();
                if(!valid){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pathsTry(){
        Path path = Paths.get("d:","a.txt");
        System.out.println(path.getNameCount());
        System.out.println(path.getParent());

        Path absolute = path.toAbsolutePath();
        System.out.println(absolute);

        System.out.println(absolute.getRoot());

        System.out.println(absolute.getNameCount());
    }

}
