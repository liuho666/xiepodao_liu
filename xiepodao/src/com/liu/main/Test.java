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
        map.remove("a");
        if (map.get("a")==null){
            System.out.println(map.size());
        }

        String a = "01";
        String b ="02";
        if (a.equals("00")){
            System.out.println("200没进车");
        }
        if (!a.equals("00")&&!b.equals(a)){
            System.out.println("200进车，但是从下半段走了");
        }

    }
}
