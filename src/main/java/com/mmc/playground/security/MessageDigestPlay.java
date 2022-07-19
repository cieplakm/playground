package com.mmc.playground.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MessageDigestPlay {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] data1 = "0123456789".getBytes();
        byte[] digest = messageDigest.digest(data1);

        System.out.println(Base64.getEncoder().encodeToString(digest));
        System.out.println(bytesToHex(digest));
    }

    static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
