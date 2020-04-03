package com.luo.util;

import java.security.MessageDigest;
import java.util.UUID;

public class CommonUtil {

    public static String genUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    public static String md5(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte item : bytes) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100)).substring(1, 3);
            }
            return sb.toString().toUpperCase();

        } catch (Exception e) {
            return null;
        }
    }


}
