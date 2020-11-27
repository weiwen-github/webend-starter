package com.ww.admin.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author ww
 * 仅用于生成加密后的密文
 */
public class JasyptConfig {

    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密解密用的密码/key
        encryptor.setPassword("AFCWEIWEN");
        //需要加密的字符串
        String plainText = "ww123456";
        //加密后的密文
        String encryptedText = encryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

}
