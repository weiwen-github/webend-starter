package com.ww.business.controller;

import com.ww.system.utils.AESUtils;
import com.ww.system.utils.MD5Utils;

/**
 *
 * @author ww
 * @date 2020/11/19
 */
public class AESController {

  public static void main(String[] args) {

    String str1 = "admin123456";
    String str = "";
    System.out.println("加密前：" + str1);

    System.out.println("LLLLL:" + MD5Utils.encrypt(str1));

    try {
      str = AESUtils.encrypt(str1);
      System.out.println("加密:" + str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String result = "";
    try {
      result = AESUtils.decrypt(str);
      System.out.println("解密:" + result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
