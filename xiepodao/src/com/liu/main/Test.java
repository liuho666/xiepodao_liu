package com.liu.main;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Test{
    public static String cuoche = "00";
    public static void main(String[] args) throws IOException
    {
        //创建FileInputStream对象，构造方法中绑定要读取的数据
        //FileInputStream fis = new FileInputStream("F:\\bin\\test.bin");
        FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\login.jpg");
        byte[] bytes = new byte[fis.available()];
        int len = fis.read(bytes);
        System.out.println(fis.available());
        System.out.println(bytes.length);
        System.out.println(len);
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytesToHex(bytes));
        System.out.println(byteToHex(bytes[0]));
        fis.close();
        int yushu = len % 8;
        for (int i=0;i<len-yushu;i=i+8){
            byte[] buffer = new byte[8];
            buffer[0] = bytes[i];
            buffer[1] = bytes[i+1];
            buffer[2] = bytes[i+2];
            buffer[3] = bytes[i+3];
            buffer[4] = bytes[i+4];
            buffer[5] = bytes[i+5];
            buffer[6] = bytes[i+6];
            buffer[7] = bytes[i+7];
            System.out.println(Arrays.toString(buffer));
        }
        String strHex = Integer.toHexString(len);
        System.out.println(strHex);

        System.out.println(Integer.valueOf(strHex));
        System.out.println(intToByteArray(Integer.valueOf(strHex)));
        System.out.println((byte)1);
    }
    //int 转换为byte
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    //字节数组转成16进制
    public static String byteToHex(byte b){
        String hex = Integer.toHexString(b & 0xFF);
        if(hex.length() < 2){
            hex = "0" + hex;
        }
        return hex;
    }

}
