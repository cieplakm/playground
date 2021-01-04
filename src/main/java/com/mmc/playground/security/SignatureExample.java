package com.mmc.playground.security;


import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.eac.operator.jcajce.JcaEACSignerBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.BufferingContentSigner;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.kerberos.KerberosKey;
import javax.xml.bind.DatatypeConverter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

public class SignatureExample {

    public static void main(String[] args) throws Exception {
        //Security.addProvider(new BouncyCastleProvider());
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        KeyPair keyPair2 = keyPairGenerator.generateKeyPair();

        KeyStore keyStore = KeyStore.getInstance("JCEKS");


        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        keyAgreement.init(keyPair.getPrivate());
        Key key = keyAgreement.doPhase(keyPair2.getPublic(), true);

        byte[] secret = keyAgreement.generateSecret();

        KeyGenerator.getInstance("AES").generateKey();


        SecretKey aes = new SecretKeySpec(secret, "AES");



        byte[] encoded = aes.getEncoded();

        Certificate[] c = new Certificate[]{cert()};

        keyStore.load(null , "pass".toCharArray());

        //keyStore.setKeyEntry("key1", (Key)keyPair.getPrivate(), "pwd".toCharArray(), c );

        keyStore.setKeyEntry("kkk", aes, "pwd".toCharArray(), c);


        System.out.println( Base64.getEncoder().encodeToString(encoded));



        try (FileOutputStream keyStoreOutputStream = new FileOutputStream("keystore.ks")) {
            keyStore.store(keyStoreOutputStream, "pass1".toCharArray());
        }

//        Signature signature = Signature.getInstance("SHA256WithDSA");
//
//        signature.initSign(keyPair.getPrivate(), secureRandom);
//
//        byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
//        signature.update(data);
//
//        byte[] digitalSignature = signature.sign();
//
//
//        Signature signature2 = Signature.getInstance("SHA256WithDSA");
//        signature2.initVerify(keyPair.getPublic());
//
//        byte[] data2 = "abcdefghijklmnopqrstuvxyz1".getBytes("UTF-8");
//
//        signature2.update(data2);
//
//        boolean verified = signature2.verify(digitalSignature);
//
//        System.out.println("verified = " + verified);
    }

    static Certificate cert() throws NoSuchAlgorithmException, OperatorCreationException, CertificateException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();

        X500Name name = new X500Name("CN=1sdfsfd");
        BigInteger random = new BigInteger("321321");
        Date date = new Date();
        Date date1 = new Date();
        SubjectPublicKeyInfo subpubinfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        X509v3CertificateBuilder builder = new X509v3CertificateBuilder(name, random, date, date1, name, subpubinfo);


        ContentSigner signer = new JcaContentSignerBuilder("SHA256withDSA").setProvider("SUN").build(keyPair.getPrivate());
        X509Certificate certificate = new JcaX509CertificateConverter().getCertificate(builder.build(signer));
        return certificate;
    }

}
