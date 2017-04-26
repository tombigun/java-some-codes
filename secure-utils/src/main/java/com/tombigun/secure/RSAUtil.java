package com.tombigun.secure;


import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/**
 * RSA安全编码组件
 * Created by tombigun on 2017/4/11.
 */
public class RSAUtil {

    /**
     * 非对称加密密钥算法
     */
    public static final String KEY_ALGORITHM_RSA = "RSA";

    //rsa，签名算法可以是 md5withrsa 、 sha1withrsa 、 sha256withrsa 、 sha384withrsa 、 sha512withrsa
    public static final String SIGN_ALGORITHM = "sha1withrsa";

    /**
     * RSA密钥长度
     * 默认1024位，
     * 密钥长度必须是64的倍数，
     * 范围在512至65536位之间。
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        return (RSAPublicKey) publicKey;
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key.getBytes()));

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        return (RSAPrivateKey) privateKey;
    }


    /**
     * 私钥解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
        // 对数据解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_RSA);

        cipher.init(Cipher.DECRYPT_MODE, privateKey);


        int key_len = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8;
        int max_len = key_len;
        byte[] mi_data = Base64.decodeBase64(data);

        int start = 0;
        int data_len = mi_data.length;
        int cc = data_len / max_len;
        cc = (data_len % max_len == 0) ? cc : cc + 1;

        ByteArrayOutputStream baos = new ByteArrayOutputStream(64);
        try {
            for (int i = 0; i < cc; i++) {

                int end = (start + max_len > data_len) ? data_len : start + max_len;

                byte[] bytes = Arrays.copyOfRange(mi_data, start, end);

                byte[] doFinal = cipher.doFinal(bytes);

                baos.write(doFinal);

                start += max_len;
            }

            return baos.toByteArray();

        } finally {
            baos.close();
        }

    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param publicKey  公钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, RSAPublicKey publicKey) throws Exception {

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        /** 执行加密操作 */
        // 模长
        int key_len = ((RSAPublicKey) publicKey).getModulus().bitLength() / 8;
        // 加密数据长度 <= 模长-11
        int max_len = key_len - 11;

        int start = 0;
        int data_len = data.length;
        int cc = data_len / max_len;
        cc = (data_len % max_len == 0) ? cc : cc + 1;

        ByteArrayOutputStream baos = new ByteArrayOutputStream(64);

        try {
            for (int i = 0; i < cc; i++) {

                int end = (start + max_len > data_len) ? data_len : start + max_len;

                byte[] bytes = Arrays.copyOfRange(data, start, end);

                byte[] doFinal = cipher.doFinal(bytes);

                baos.write(doFinal);

                start += max_len;
            }

            return Base64.encodeBase64(baos.toByteArray());

        } finally {
            baos.close();
        }
    }

    /**
     * 使用私钥 对数据进行签名
     *
     * @param data
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String signByPrivateKey(byte[] data, RSAPrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);

        byte[] signed = signature.sign();

        return new String(Base64.encodeBase64(signed));
    }


    /**
     * 使用公钥校验签名
     *
     * @param data
     * @param sign
     * @param publicKey  公钥
     * @return
     * @throws Exception
     */
    public static boolean verifySignByPublicKey(byte[] data, String sign, RSAPublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(Base64.decodeBase64(sign));

    }

}
