package com.tombigun.secure;

import com.tombigun.tools.Base64Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
     * 公钥
     */
    private static final String RSA_PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    private static final String RSA_PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA密钥长度
     * 默认1024位，
     * 密钥长度必须是64的倍数，
     * 范围在512至65536位之间。
     */
    private static final int KEY_SIZE = 1024;

    static {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] key, byte[] data)
            throws Exception {

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int blockSize = cipher.getBlockSize();
        if (blockSize > 0) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            while (data.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(data, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        }
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] key, byte[] data)
            throws Exception {

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] key, byte[] data)
            throws Exception {

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        int blockSize = cipher.getBlockSize();
        if (blockSize > 0) {
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0, remainSize = 0;
            while ((remainSize = data.length - i * blockSize) > 0) {
                int inputLen = remainSize > blockSize ? blockSize : remainSize;
                cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);
                i++;
            }
            return raw;
        }
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] key, byte[] data)
            throws Exception {

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        int blockSize = cipher.getBlockSize();
        if (blockSize > 0) {
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
                    : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0, remainSize = 0;
            while ((remainSize = data.length - i * blockSize) > 0) {
                int inputLen = remainSize > blockSize ? blockSize : remainSize;
                cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);
                i++;
            }
            return raw;
        }
        return cipher.doFinal(data);
    }

    /**
     * 使用私钥 对数据进行签名
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String signByPrivateKey(byte[] key, byte[] data) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);

        return Base64.toBase64String(signature.sign());
    }

    /**
     * 使用公钥校验签名
     *
     * @param data
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verifySignByPublicKey(byte[] publicKey, byte[] data, String sign) throws Exception {
        X509EncodedKeySpec x509EncoderKeySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);


        PublicKey rsaPublicKey = keyFactory.generatePublic(x509EncoderKeySpec);
        Signature signature = Signature.getInstance(SIGN_ALGORITHM);
        signature.initVerify(rsaPublicKey);
        signature.update(data);
        return signature.verify(Base64.decode(sign));
    }

    /**
     * 从文件中加载密钥
     *
     * @param keyFile 私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadKey(InputStream keyFile) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(keyFile));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
//                    sb.append('\r');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                }

            if (keyFile != null)
                try {
                    keyFile.close();
                } catch (IOException e) {
                }
        }

        return sb.toString();
    }
}
