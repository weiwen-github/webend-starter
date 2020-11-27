package com.ww.system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES加密工具
 *
 * @author ww
 * @date 2020/11/19
 */
public class AESUtils {
  /** 算法 */
  private static final String AES = "AES";
  /** 默认的加密算法，数据填充方式 */
  private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
  /** 密钥必须16位，可改 */
  private static final String KEY = "a70648d869329dab";
  /** 偏移量16位，可改 */
  private static final String OFFSET = "0102030405060709";

  protected static Logger logger = LoggerFactory.getLogger(AESUtils.class);

  /**
   * AES加密
   *
   * @param password
   * @return
   * @throws Exception
   */
  public static String encrypt(String password) throws Exception {
    // 初始化cipher
    Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
    // 转化成JAVA的密钥格式
    SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.US_ASCII), AES);
    // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
    IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
    byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
    // 此处使用BASE64做转码。
    String result = new BASE64Encoder().encode(encrypted);
    return result;
  }

  /**
   * AES解密
   *
   * @param encryptPwd
   * @return
   * @throws Exception
   */
  public static String decrypt(String encryptPwd) throws Exception {
    Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
    SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.US_ASCII), AES);
    // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
    IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());
    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
    byte[] buffer = new BASE64Decoder().decodeBuffer(encryptPwd);
    byte[] encrypted = cipher.doFinal(buffer);
    // 此处使用BASE64做转码。
    String result = new String(encrypted, StandardCharsets.UTF_8);
    return result;
  }
}
