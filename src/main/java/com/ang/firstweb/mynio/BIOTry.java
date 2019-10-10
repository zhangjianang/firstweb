package com.ang.firstweb.mynio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by adimn on 2019/10/10.
 */
public class BIOTry {
    public static void main(String[] args) {
        intry();
    }

    public static void intry(){
        try(InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);){
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println(line);
                if("exit".equals(line)){
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
