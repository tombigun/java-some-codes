package com.tombigun.secure;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by tombigun on 2017/4/27.
 */
public class RSAcreateKey {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSAUtil.KEY_ALGORITHM_RSA);

        keyPairGen.initialize(RSAUtil.KEY_SIZE);

        KeyPair keyPair = keyPairGen.generateKeyPair();


        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();


        byte[] pri_bytes = Base64.encodeBase64(privateKey.getEncoded());
        byte[] pub_bytes = Base64.encodeBase64(publicKey.getEncoded());

        System.out.println("私密：\n" + new String(pri_bytes));
        System.out.println("公密：\n" + new String(pub_bytes));
    }
}
