package com.mmc.playground.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileEncryptionPlay {

    static byte[] readData(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        int size = is.available();
        byte[] data = new byte[size];

        is.read(data);
        is.close();

        return data;
    }

    static void writeData(byte[] data, String fileName) throws IOException {
        OutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(data);
        outputStream.close();
    }

    public static void main(String[] args) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] data = readData("image.png");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedData = cipher.doFinal(data);

        writeData(encryptedData, "encrypted.png");

        byte[] encryptedFileData = readData("encrypted.png");

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedData = cipher.doFinal(encryptedFileData);

        writeData(decryptedData, "decrypted.png");
    }
}
