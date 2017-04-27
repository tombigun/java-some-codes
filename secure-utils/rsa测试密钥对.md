### 测试RSA密钥对

##### 原始的私钥:

```
-----BEGIN RSA PRIVATE KEY-----
MIICXAIBAAKBgQCRoMm+OVJtBiufiAU9EQ4DNDrl0uRP3ixhnY/vYj72pTyxfRak
inGRjJs4F0G2KHOV7naCc+3J5foDrI9Q3O4MiZza6QI7SiDSZLQd02eP2utDFRai
dB+kxOJjEvE+04QkZJNw3QSro+olM63gR94KOnvR69M1QFc2l26UiPjTUwIDAQAB
AoGAUHzrxCVudy+X4L6PFqdcdUzi2K9s3O/Q28r4CLjmT/AX0YlvgFW8W1O5XpEM
Ep/c8pswCP4GCTcArhMryuew8YXIOxVtn2OJPWMg04ociRpjESYo2gnelf0vk4UL
gt2VCr/hNpoHXJZC8dyurmGR/hAadTd9QfMgdxPfueqbZYECQQDzq+2WPEOaiYUr
yootBG8vb36LnicIev7CLoEVoDa5+DbuRhkM/4QIcvr1BbcZAPHmOAehAvvHFlmB
hrOfbrOhAkEAmP78PPx3bitm+Un8vMpuod7UWG+lc/p6QAJtIXA+Eudda1yiPS94
gT+OjLwux103wC6pwPNpHsWeXa2Lb2ricwJAeoaHLzYaYEiqN/zeQgiqx9VuclIS
GoeONqIfsnofSp/vLxz8V7bb80utuSsoiGAbQsIFxAa2B8PLOgA7rf2CQQJAIVwn
/9xE5l7MWzsIwtmY+e6ueoINn5ZUJbThutLQliqgshJGxfSd4ADNwl4eDRaEW4FR
PmNU3oLUnzSCHM8/hQJBAKgbBxJEBVoDKoUBpfEqNZjSnzMo/fJVsiCGtG+AZaA0
ebfCBJw7WYLHRc+uj81P6Mj7tWiS6OT0N5lFVzmH/OQ=
-----END RSA PRIVATE KEY-----
```

##### 转换成PKCS8格式的私钥

```
-----BEGIN PRIVATE KEY-----
MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJGgyb45Um0GK5+I
BT0RDgM0OuXS5E/eLGGdj+9iPvalPLF9FqSKcZGMmzgXQbYoc5XudoJz7cnl+gOs
j1Dc7gyJnNrpAjtKINJktB3TZ4/a60MVFqJ0H6TE4mMS8T7ThCRkk3DdBKuj6iUz
reBH3go6e9Hr0zVAVzaXbpSI+NNTAgMBAAECgYBQfOvEJW53L5fgvo8Wp1x1TOLY
r2zc79DbyvgIuOZP8BfRiW+AVbxbU7lekQwSn9zymzAI/gYJNwCuEyvK57Dxhcg7
FW2fY4k9YyDTihyJGmMRJijaCd6V/S+ThQuC3ZUKv+E2mgdclkLx3K6uYZH+EBp1
N31B8yB3E9+56ptlgQJBAPOr7ZY8Q5qJhSvKii0Eby9vfoueJwh6/sIugRWgNrn4
Nu5GGQz/hAhy+vUFtxkA8eY4B6EC+8cWWYGGs59us6ECQQCY/vw8/HduK2b5Sfy8
ym6h3tRYb6Vz+npAAm0hcD4S511rXKI9L3iBP46MvC7HXTfALqnA82kexZ5drYtv
auJzAkB6hocvNhpgSKo3/N5CCKrH1W5yUhIah442oh+yeh9Kn+8vHPxXttvzS625
KyiIYBtCwgXEBrYHw8s6ADut/YJBAkAhXCf/3ETmXsxbOwjC2Zj57q56gg2fllQl
tOG60tCWKqCyEkbF9J3gAM3CXh4NFoRbgVE+Y1TegtSfNIIczz+FAkEAqBsHEkQF
WgMqhQGl8So1mNKfMyj98lWyIIa0b4BloDR5t8IEnDtZgsdFz66PzU/oyPu1aJLo
5PQ3mUVXOYf85A==
-----END PRIVATE KEY-----
```

#### 公钥

```
-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRoMm+OVJtBiufiAU9EQ4DNDrl
0uRP3ixhnY/vYj72pTyxfRakinGRjJs4F0G2KHOV7naCc+3J5foDrI9Q3O4MiZza
6QI7SiDSZLQd02eP2utDFRaidB+kxOJjEvE+04QkZJNw3QSro+olM63gR94KOnvR
69M1QFc2l26UiPjTUwIDAQAB
-----END PUBLIC KEY-----
```


####附资料：
>1. RSA密钥在线生成器   http://www.bm8.com.cn/webtool/rsa/
>2. 在线RSA私钥加密解密 http://tool.chacuo.net/cryptrsaprikey
>3. rsa私钥的格式: 原格式、pksc8格式 http://www.cnblogs.com/davidwang456/p/3924834.html
>4. AES+RSA结合应用java示例  https://github.com/wustrive2008/aes-rsa-java
>5. 加解密工具类 http://git.oschina.net/bayern.com/SecureUtils