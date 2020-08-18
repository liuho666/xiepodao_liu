package com.liu.util;

import java.util.regex.Pattern;

/* 数据转换类工具包 */

public class ChangeUtils {

    // 10进制转换16进制
    public static String dncodeHex(Integer num) {
        String hex = Integer.toHexString(num);
        return hex;
    }

    // 判断字符串是不是全部为数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
