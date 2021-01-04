package com.mmc.playground.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MessageDigestPlay {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] data1 = "01231456789".getBytes();
        byte[] digest = messageDigest.digest(data1);

        System.out.println(Base64.getEncoder().encodeToString(digest));
    }
}
