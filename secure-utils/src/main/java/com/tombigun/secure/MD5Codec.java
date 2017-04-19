package com.tombigun.secure;

import com.tombigun.tools.HexStringUtils;

import java.security.MessageDigest;

/**
 * MD5 单向加密
 * Created by tombigun on 2017/4/19.
 */
public class MD5Codec {

    private static final String ALGORITHM = "MD5";

    public static byte[] encrypt(byte[] data) throws Exception
    {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        return messageDigest.digest(data);
    }

    /**
     * 返回MD5单向加密后的十六进制字符串
     * @param data
     * @return
     * @throws Exception
     */
    public static String getEncryptForHex(byte[] data) throws Exception
    {
        byte[] digestData = encrypt(data);
        return HexStringUtils.parseByteArray2HexStr(digestData);
    }
}
