package com.test;

import com.tombigun.secure.DESCodec;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tombigun on 2017/4/19.
 */
public class TestDESCodec {

    @Test
    public void Tested() throws Exception {
        byte[] data = "123456".getBytes("utf-8");

        byte[] key = DESCodec.creatKey();

        byte[] encrypt = DESCodec.encrypt(key, data);

        byte[] decrypt = DESCodec.decrypt(key, encrypt);

        Assert.assertArrayEquals(data, decrypt);

    }
}
