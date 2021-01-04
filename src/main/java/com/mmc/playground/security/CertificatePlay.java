package com.mmc.playground.security;

import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;

public class CertificatePlay {
    public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, SignatureException, NoSuchProviderException, InvalidKeyException, UnrecoverableEntryException {
        KeyStore keyStore = CertificatePlay.loadKeyStore("s4.jks", "ksPass");
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("cert", new KeyStore.PasswordProtection("pkPass".toCharArray()));
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyGenerator.generateKeyPair();

        String subjectName = "s5";
        String issuerName = "s4";
        Certificate certificate = createCertificate(subjectName, issuerName, keyPair.getPublic(), privateKey);
        writeKeyStore("cert", subjectName + ".jks", "ksPass", "pkPass", keyPair.getPrivate(), certificate);
    }

    static void writeKeyStore(String alias, String fileNameWithExtension, String keyStorePassword, String privateKeyPassword, PrivateKey privateKey, Certificate certificate) throws CertificateException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);
        keyStore.setKeyEntry(alias, privateKey, privateKeyPassword.toCharArray(), new Certificate[]{certificate});
        keyStore.store(new FileOutputStream(fileNameWithExtension), keyStorePassword.toCharArray());
    }

    static KeyStore loadKeyStore(String fileName, String keyStorePassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(fileName), keyStorePassword.toCharArray());
        return keyStore;
    }

    private static Certificate createCertificate(String subjectName, String issuerName, PublicKey publicKey, PrivateKey signer) throws CertificateException, IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        BigInteger serialNumber = new BigInteger(64, new SecureRandom());

        Date from = new Date();
        Date to = new Date(from.getTime() + 1000 * 60 * 60 * 24 * 365L);

        X509CertInfo certInfo = new X509CertInfo();

        AlgorithmId algo = new AlgorithmId(AlgorithmId.sha256WithRSAEncryption_oid);

        X500Name issuer = new X500Name("CN=" + issuerName);
        X500Name subject = new X500Name("CN=" + subjectName);
        certInfo.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
        certInfo.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(serialNumber));
        certInfo.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algo));
        certInfo.set(X509CertInfo.SUBJECT, subject);
        certInfo.set(X509CertInfo.ISSUER, issuer);
        certInfo.set(X509CertInfo.KEY, new CertificateX509Key(publicKey));
        certInfo.set(X509CertInfo.VALIDITY, new CertificateValidity(from, to));

        X509CertImpl x509Cert = new X509CertImpl(certInfo);
        x509Cert.sign(signer, "SHA256withRSA");

        return x509Cert;
    }
}
