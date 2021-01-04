package com.mmc.playground.security;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class KeyAgreementPlay {
    Base64.Encoder encoder = Base64.getEncoder();
    String name;
    PublicKey publicKey;
    PrivateKey pairPrivate;
    SecretKey secretKey;

    public KeyAgreementPlay(String c2) throws NoSuchAlgorithmException {
        name = c2;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DiffieHellman");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
        pairPrivate = keyPair.getPrivate();

        System.out.println(name + " public :" + encoder.encodeToString(publicKey.getEncoded()));
        System.out.println(name + " private :" + encoder.encodeToString(pairPrivate.getEncoded()));
    }
/*
* KeyAgreement Algorithms

The following algorithm names can be specified when requesting an instance of KeyAgreement.
Algorithm Name 	Description
DiffieHellman 	Diffie-Hellman Key Agreement as defined in PKCS #3: Diffie-Hellman Key-Agreement Standard, RSA Laboratories, version 1.4, November 1993.
ECDH 	Elliptic Curve Diffie-Hellman as defined in ANSI X9.63 and as described in RFC 3278: "Use of Elliptic Curve Cryptography (ECC) Algorithms in Cryptographic Message Syntax (CMS)."
ECMQV 	Elliptic Curve Menezes-Qu-Vanstone.
XDH 	Diffie-Hellman key agreement with elliptic curves as defined in RFC 7748
X25519 	Diffie-Hellman key agreement with Curve25519 as defined in RFC 7748
X448 	Diffie-Hellman key agreement with Curve448 as defined in RFC 7748
* */


/*
* KeyPairGenerator Algorithms

The algorithm names that can be specified when generating an instance of KeyPairGenerator.

(Except as noted, these classes create keys for which Key.getAlgorithm() returns the standard algorithm name.)
Algorithm Name 	Description
DiffieHellman 	Generates keypairs for the Diffie-Hellman KeyAgreement algorithm.

Note: key.getAlgorithm() will return "DH" instead of "DiffieHellman".
DSA 	Generates keypairs for the Digital Signature Algorithm.
RSA 	Generates keypairs for the RSA algorithm (Signature/Cipher).
RSASSA-PSS 	Generates keypairs for the RSASSA-PSS signature algorithm.
EC 	Generates keypairs for the Elliptic Curve algorithm.
XDH 	Generates keypairs for Diffie-Hellman key agreement with elliptic curves as defined in RFC 7748
X25519 	Generates keypairs for Diffie-Hellman key agreement with Curve25519 as defined in RFC 7748
X448 	Generates keypairs for Diffie-Hellman key agreement with Curve448 as defined in RFC 7748*/

    void agreement(PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DiffieHellman");
        keyAgreement.init(pairPrivate);
        keyAgreement.doPhase(publicKey, true);
        byte[] secret = keyAgreement.generateSecret();
        secretKey = new SecretKeySpec(secret, "DiffieHellman");
        System.out.println(name + " secret :" + encoder.encodeToString(secret));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyAgreementPlay c1 = new KeyAgreementPlay("c1");
        KeyAgreementPlay c2 = new KeyAgreementPlay("c2");

        c1.agreement(c2.publicKey);
        c2.agreement(c1.publicKey);
    }
}
