package com.ang.firstweb.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adimn on 2019/7/9.
 */
public class StringRever {

        public static void main( String[] args )
        {
            String input = "$$My$$name$is$$$$Xiaogang";

            System.out.println(conver(input));
        }

    public static String conver(String input){
        char[] chars = input.toCharArray();
        List words = new ArrayList<String>();
        StringBuilder res = new StringBuilder();
        for(int i =0;i<chars.length;i++){
            if(chars[i] == '$'){
                res.append(chars[i]);
            }else{
                StringBuilder temp = new StringBuilder();
                temp.append(chars[i]);
                i++;
                while ( i<chars.length &&chars[i]!='$'){
                    temp.append(chars[i]);
                    i++;
                }
                words.add(temp.toString());
                if(i<chars.length && chars[i] == '$'){
                    i--;
                }
            }
        }
        for(int j=words.size()-1;j>=0;j--){
            res.append(words.get(j));
        }
        return res.toString();

    }

}
