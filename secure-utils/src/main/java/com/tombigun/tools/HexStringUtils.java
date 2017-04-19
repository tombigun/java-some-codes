package com.tombigun.tools;

/**
 * Created by tombigun on 2017/4/19.
 */
public class HexStringUtils {

    /**
     * 2进制数字转换为16进制字符串
     * @param data
     * @return
     */
    public static String parseByteArray2HexStr(byte[] data)
    {
        if(data == null || data.length < 1)
        {
            return null;
        }

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < data.length; i++)
        {
            int h = data[i] & 0XFF;
            if(h < 16)
            {
                hex.append("0");
            }
            hex.append(Integer.toHexString(h));
        }

        return hex.toString();
    }

    /**
     * 16进制字符串转换为2进制数字
     * @param hex
     * @return
     */
    public static byte[] parseHexStr2ByteArray(String hex)
    {
        if(hex == null || "".equals(hex))
        {
            return null;
        }

        int length = hex.length() >> 1;
        byte[] data = new byte[length];
        for(int i = 0; i < length; i++)
        {
            int n = i << 1;
            int height = Integer.valueOf(hex.substring(n, n + 1), 16);
            int low = Integer.valueOf(hex.substring(n + 1, n + 2), 16);
            data[i] = (byte)(height * 16 + low);
        }

        return data;
    }

}
