package com.mmc.playground.security;

import sun.security.x509.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Date;


/**
 * Must have: JDK8 (not above)!
 */
public class CreateCertificatePlay {
    public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, SignatureException, NoSuchProviderException, InvalidKeyException, UnrecoverableEntryException {
        String keyStoreFileName = "cert.jks";
        String keyStorePass = "pass";
        String privateKeyPass = "pkPass";
        String alias = "myCert";
        String subjectName = "MySubject";
        String issuerName = "MyIssuerName";

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyGenerator.generateKeyPair();

        Certificate certificate = createCertificate(subjectName, issuerName, keyPair.getPublic(), keyPair.getPrivate());
        writeKeyStore(alias, keyStoreFileName, keyStorePass, privateKeyPass, keyPair.getPrivate(), certificate);
    }

    static void writeKeyStore(String alias, String fileNameWithExtension, String keyStorePassword, String privateKeyPassword, PrivateKey privateKey, Certificate certificate) throws CertificateException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);
        keyStore.setKeyEntry(alias, privateKey, privateKeyPassword.toCharArray(), new Certificate[]{certificate});
        keyStore.store(new FileOutputStream(fileNameWithExtension), keyStorePassword.toCharArray());
    }

    private static Certificate createCertificate(String subjectName, String issuerName, PublicKey publicKey, PrivateKey signer) throws CertificateException, IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        BigInteger serialNumber = new BigInteger(64, new SecureRandom());

        Date from = new Date();
        Date to = new Date(from.getTime() + 1000 * 60 * 60 * 24 * 365L);

        X509CertInfo certInfo = new X509CertInfo();

        AlgorithmId algorithmId = new AlgorithmId(AlgorithmId.sha256WithRSAEncryption_oid);

        X500Name issuer = new X500Name("CN=" + issuerName);
        X500Name subject = new X500Name("CN=" + subjectName);
        certInfo.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
        certInfo.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(serialNumber));
        certInfo.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(algorithmId));
        certInfo.set(X509CertInfo.SUBJECT, subject);
        certInfo.set(X509CertInfo.ISSUER, issuer);
        certInfo.set(X509CertInfo.KEY, new CertificateX509Key(publicKey));
        certInfo.set(X509CertInfo.VALIDITY, new CertificateValidity(from, to));

        X509CertImpl x509Cert = new X509CertImpl(certInfo);
        x509Cert.sign(signer, "SHA256withRSA");

        return x509Cert;
    }
}