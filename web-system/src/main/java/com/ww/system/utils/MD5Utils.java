package com.ww.system.utils;

import com.ww.system.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具
 * @author ww
 * @date 2020/11/19
 */
public class MD5Utils {
  /** 密钥 */
  private static final String KEY = "wwwwwwww";

  /**
   * MD5加密
   * @param password
   * @return java.lang.String
   */
  public static String encrypt(String password) {
    return DigestUtils.md5Hex(password + KEY);
  }

  /**
   * 使用md5生成加密后的密码
   *
   * @param user
   * @return java.lang.String
   */
  public static String userEncrypt(SysUser user) {
    return DigestUtils.md5Hex(user.getPassword() + user.getUserName());
  }

}
