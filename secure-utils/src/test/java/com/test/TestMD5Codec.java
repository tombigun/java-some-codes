package com.test;

import com.tombigun.secure.MD5Codec;
import com.tombigun.tools.HexStringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tombigun on 2017/4/19.
 */
public class TestMD5Codec {


    @Test
    public void testEncrypt() throws Exception {

        byte[] data = "123456".getBytes("utf-8");
        String expectResult = "E10ADC3949BA59ABBE56E057F20F883E";

        byte[] bytes = MD5Codec.encrypt(data);

        System.out.println(HexStringUtils.parseByteArray2HexStr(bytes));


        String hex = MD5Codec.getEncryptForHex(data);
        System.out.println(hex);

        Assert.assertEquals(expectResult, hex.toUpperCase());
    }


}
