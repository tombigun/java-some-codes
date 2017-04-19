package com.test;

import com.tombigun.secure.RSAUtil;
import com.tombigun.tools.Base64Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tombigun on 2017/4/19.
 */
public class TestRSA {

    private static String rsa_private = "" +
//            "-----BEGIN RSA PRIVATE KEY-----" +
            "MIICWgIBAAKBgHiVDou7DuJ95B587DgpMd6iJ7bAtRDHoC+Es7vBY36CuIsE7mS/" +
            "04uhJesfp1blLrqcvX4YJh0alsFvXCPSSa9wSQoqAvumCx8fx+Y7PXi953HGfIU2" +
            "osCVEdb1eNFGnTEYSFt10jnFaY2Zu1aIbOZlwbzTDfjmoWWXo9WqbL1vAgMBAAEC" +
            "gYAMXbT1LYTUQ3bejY8oH0lm/Jg4DMFE7EQIO4K3MZkDg2Jrl2DfYjDOtAMQftmJ" +
            "ds15A9QHF3DpMS3evo3jepBl0lfF6PxD9ggdV33YiK5IrXMHDmaHxNxwZHB7OnDH" +
            "+xjXxr5shE/XdJKq1TYs8LolHAZ3uJooCgSQEpJH3M5ioQJBAMGyqfbgwVa3dw4b" +
            "lR5Q6Ws5DCCg/XS6lltMNRHt6MnD3oxL4oa5sMzjDRLm4XH/wpsrZ6w78LdBC8/b" +
            "0I7tLR8CQQCfXfSbHI3xeKCUlG8IBRNtge1h/2d7DWgp+DpdRsm3SPfBzJV5Hmji" +
            "M0LJwMJgAxrLtTuhBDWoSa8Yjkw3oxWxAkB9BdIYzJdeXosU6w7EA5u2HptKUD9o" +
            "Yar9AOJcUUBpQujZi32KUj7g8EiXA7lOeLPmrgx7qjDER9LU9l/WcRYnAkAoIWmy" +
            "EYuwzbZqE8Kt21FcFwam0cDGHMcpImfYCzZKYCZ7d8AAAudYZp4f3dlUghOiTl3Y" +
            "mw3D9eIQpeyNahTRAkB28bchIctphliFH++YRxGS1BaFe0zrS5XqL0I+7+ivSpi/" +
            "e9lh5iStYh+hXhFRLKy0y/Ene0wNFgnEDBbjyfXz" +
//            "-----END RSA PRIVATE KEY-----" +
            "";


    private static String rsa_public = "" +
//            "-----BEGIN PUBLIC KEY-----" +
            "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHiVDou7DuJ95B587DgpMd6iJ7bA" +
            "tRDHoC+Es7vBY36CuIsE7mS/04uhJesfp1blLrqcvX4YJh0alsFvXCPSSa9wSQoq" +
            "AvumCx8fx+Y7PXi953HGfIU2osCVEdb1eNFGnTEYSFt10jnFaY2Zu1aIbOZlwbzT" +
            "DfjmoWWXo9WqbL1vAgMBAAE=" +
//            "-----END PUBLIC KEY-----" +
            "";




    @Test
    public void Tested() throws Exception {
        byte[] data = "123456".getBytes("utf-8");

        byte[] bytes = RSAUtil.encryptByPublicKey(Base64Utils.decode(rsa_public), data);
        byte[] bytes1 = RSAUtil.decryptByPrivateKey(Base64Utils.decode(rsa_private), bytes);

        String s = RSAUtil.signByPrivateKey(Base64Utils.decode(rsa_private), data);
        boolean b = RSAUtil.verifySignByPublicKey(Base64Utils.decode(rsa_public), data, s);


        Assert.assertArrayEquals(data, bytes1);
        Assert.assertTrue(b);
    }
}
