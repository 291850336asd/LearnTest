package com.meng.blockchain.security;

import org.eclipse.jetty.util.security.Credential;

import java.security.MessageDigest;
import java.util.UUID;

public class CryptoUtil {
    private CryptoUtil() {
    }

    public static String SHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            System.out.println("getSHA256 is error" + e.getMessage());
        }
        return encodeStr;
    }

    public static String MD5(String str) {
        String resultStr = Credential.MD5.digest(str);
        return resultStr.substring(4, resultStr.length());
    }

    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
