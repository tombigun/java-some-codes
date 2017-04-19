package com.test;

import com.tombigun.secure.AESCodec;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by tombigun on 2017/4/19.
 */
public class TestAESCodec {

    @Test
    public void Tested() throws Exception {
        byte[] data = "123456".getBytes("utf-8");

        byte[] key = AESCodec.getKey();

        byte[] encrypt = AESCodec.encrypt(key, data);

        byte[] decrypt = AESCodec.decrypt(key, encrypt);

        Assert.assertArrayEquals(data, decrypt);

    }
}
