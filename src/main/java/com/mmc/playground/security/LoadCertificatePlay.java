package com.mmc.playground.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

public class LoadCertificatePlay {
    public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, SignatureException, NoSuchProviderException, InvalidKeyException, UnrecoverableEntryException {
        String keyStoreFileName = "cert.jks";
        String keyStorePass = "pass";
        String privateKeyPass = "pkPass";
        String alias = "myCert";

        KeyStore keyStore = LoadCertificatePlay.loadKeyStore(keyStoreFileName, keyStorePass);
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(privateKeyPass.toCharArray()));
        PublicKey publicKey = privateKeyEntry.getCertificate().getPublicKey();
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();

        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        System.out.println(publicKeyString);
        System.out.println(privateKeyString);
    }

    static KeyStore loadKeyStore(String fileName, String keyStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(fileName), keyStorePassword.toCharArray());
        return keyStore;
    }
}
