package com.mmc.playground.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

public class SignaturePlay {

    public static void main(String[] args) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, UnrecoverableEntryException, InvalidKeyException, SignatureException {
        KeyStore keyStore = CertificatePlay.loadKeyStore("cert.jks", "pass");
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("myCert", new KeyStore.PasswordProtection("pkPass".toCharArray()));
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();

        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey, new SecureRandom());
        signature.update("Message".getBytes());

        byte[] sign = signature.sign();

        System.out.println(Base64.getEncoder().encodeToString(sign));

        signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(keyStore.getCertificate("myCert"));
        signature.update("Message".getBytes());

        System.out.println(signature.verify(sign));
    }
}
