package com.example.d1.zsan.utils;

/**
 * Created by A on 2017/2/26.
 */

public class Check {
    public static boolean check(String str){
        String pattern ="\\w{6,15}";
        boolean matches = str.matches(pattern);
        return matches;
    }
}
