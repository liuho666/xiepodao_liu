package com.liu.main;

import javax.swing.*;
import java.util.HashMap;

public class Test {
    public static String cuoche = "00";
    public static void main(String[] args)
    {
        HashMap map = new HashMap();

        map.put("a","aaaa");
        map.put("b","bbbb");
        map.put("c","cccc");
        map.put("d","dddd");

        if (map.get("2")==null){
            System.out.println(map.size());
        }

    }
}
