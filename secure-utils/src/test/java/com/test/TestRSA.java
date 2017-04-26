package com.test;

import com.tombigun.secure.RSAUtil;
import com.tombigun.tools.Base64Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tombigun on 2017/4/19.
 */
public class TestRSA {

    private static String rsa_PKCS8_private = "" +
//            "-----BEGIN PRIVATE KEY-----" +
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJGgyb45Um0GK5+I" +
            "BT0RDgM0OuXS5E/eLGGdj+9iPvalPLF9FqSKcZGMmzgXQbYoc5XudoJz7cnl+gOs" +
            "j1Dc7gyJnNrpAjtKINJktB3TZ4/a60MVFqJ0H6TE4mMS8T7ThCRkk3DdBKuj6iUz" +
            "reBH3go6e9Hr0zVAVzaXbpSI+NNTAgMBAAECgYBQfOvEJW53L5fgvo8Wp1x1TOLY" +
            "r2zc79DbyvgIuOZP8BfRiW+AVbxbU7lekQwSn9zymzAI/gYJNwCuEyvK57Dxhcg7" +
            "FW2fY4k9YyDTihyJGmMRJijaCd6V/S+ThQuC3ZUKv+E2mgdclkLx3K6uYZH+EBp1" +
            "N31B8yB3E9+56ptlgQJBAPOr7ZY8Q5qJhSvKii0Eby9vfoueJwh6/sIugRWgNrn4" +
            "Nu5GGQz/hAhy+vUFtxkA8eY4B6EC+8cWWYGGs59us6ECQQCY/vw8/HduK2b5Sfy8" +
            "ym6h3tRYb6Vz+npAAm0hcD4S511rXKI9L3iBP46MvC7HXTfALqnA82kexZ5drYtv" +
            "auJzAkB6hocvNhpgSKo3/N5CCKrH1W5yUhIah442oh+yeh9Kn+8vHPxXttvzS625" +
            "KyiIYBtCwgXEBrYHw8s6ADut/YJBAkAhXCf/3ETmXsxbOwjC2Zj57q56gg2fllQl" +
            "tOG60tCWKqCyEkbF9J3gAM3CXh4NFoRbgVE+Y1TegtSfNIIczz+FAkEAqBsHEkQF" +
            "WgMqhQGl8So1mNKfMyj98lWyIIa0b4BloDR5t8IEnDtZgsdFz66PzU/oyPu1aJLo" +
            "5PQ3mUVXOYf85A==" +
//            "-----END PRIVATE KEY-----" +
            "";

    private static String rsa_public = "" +
//            "-----BEGIN PUBLIC KEY-----" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRoMm+OVJtBiufiAU9EQ4DNDrl" +
            "0uRP3ixhnY/vYj72pTyxfRakinGRjJs4F0G2KHOV7naCc+3J5foDrI9Q3O4MiZza" +
            "6QI7SiDSZLQd02eP2utDFRaidB+kxOJjEvE+04QkZJNw3QSro+olM63gR94KOnvR" +
            "69M1QFc2l26UiPjTUwIDAQAB" +
//            "-----END PUBLIC KEY-----" +
            "";


    @Test
    public void Tested() throws Exception {
        byte[] data = "1234567890你好12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890text12".getBytes("utf-8");

        byte[] bytes = RSAUtil.encryptByPublicKey(data, RSAUtil.getPublicKey(rsa_public));
        byte[] bytes1 = RSAUtil.decryptByPrivateKey(bytes, RSAUtil.getPrivateKey(rsa_PKCS8_private));

        System.out.println(new String(bytes));

        String s = RSAUtil.signByPrivateKey(data, RSAUtil.getPrivateKey(rsa_PKCS8_private));
        boolean b = RSAUtil.verifySignByPublicKey(data, s, RSAUtil.getPublicKey(rsa_public));

        Assert.assertArrayEquals(data, bytes1);
        Assert.assertTrue(b);

        System.out.println(new String(bytes1, "utf-8"));
    }
}
