package com.tombigun.secure;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES 对称加密类
 * Created by tombigun on 2017/4/19.
 */
public class DESCodec {
    private static final String ALGORITHM = "DES";

    /**
     * 生成DES对称秘钥，并对DES对称秘钥进行base64编码
     *
     */
    public static byte[] creatKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        SecretKey desKey = keyGenerator.generateKey();
        return desKey.getEncoded();
    }

    /**
     * 获取对称密钥
     *
     * @param key 密钥字符串
     */
    private static SecretKey getKey(byte[] key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return secretKeyFactory.generateSecret(desKeySpec);
    }

    public static byte[] encrypt(byte[] key, byte[] data) throws Exception {
        SecretKey md5Key = getKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, md5Key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] key, byte[] data) throws Exception {
        SecretKey mesKey = getKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, mesKey);
        return cipher.doFinal(data);
    }


}
