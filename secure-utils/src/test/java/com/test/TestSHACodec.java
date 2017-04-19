package com.test;

import com.tombigun.secure.SHACodec;
import com.tombigun.tools.HexStringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tombigun on 2017/4/19.
 */
public class TestSHACodec {

    @Test
    public void testEncrypt() throws Exception {

        byte[] data = "123456".getBytes("utf-8");
        String expectResult = "7c4a8d09ca3762af61e59520943dc26494f8941b";

        byte[] bytes = SHACodec.encrypt(data);

        System.out.println(HexStringUtils.parseByteArray2HexStr(bytes));


        String hex = SHACodec.getEncryptForHex(data);
        System.out.println(hex);

        Assert.assertEquals(expectResult, hex);
    }

}
