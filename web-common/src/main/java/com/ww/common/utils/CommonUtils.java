package com.ww.common.utils;

import java.util.Collection;

/**
 * 通用工具类
 */
public class CommonUtils {

  /**
   * object转为 int.
   */
  public static Integer trans2Int(Object val) {
    if (val instanceof Integer) {
      return (Integer) val;
    }
    if (val == null) {
      return null;
    }
    return Integer.valueOf((String) val);
  }

  /**
   * Is null or empty boolean.
   */
  public static boolean isNullOrEmpty(Object obj) {
    if (obj == null) {
      return true;
    }
    if (obj instanceof String) {
      return ((String) obj).isEmpty();
    } else if (obj instanceof Collection) {
      return ((Collection) obj).isEmpty();
    }
    return false;
  }

  /**
   * Is not null or not empty boolean.
   */
  public static boolean isNotNullOrNotEmpty(Object obj) {
    return !isNullOrEmpty(obj);
  }

  /**
   * Is null or empty boolean.
   */
  public static boolean isAnyoneNullOrEmpty(Object... objs) {
    for (Object obj : objs) {
      if (isNullOrEmpty(obj)) {
        return true;
      }
    }

    return false;
  }

  /**
   * 判断整数是否大于零
   */
  public static boolean isIntThanZero(int number) {
    if (number > 0) {
      return true;
    }
    return false;
  }

  /**
   * 获取当前 时间戳
   */
  public static Long getTs() {
    return System.currentTimeMillis();
  }

  /**
   * 获取与 startTs 的时间差
   */
  public static Long costTs(Long startTs) {
    return System.currentTimeMillis() - startTs > 0 ? System.currentTimeMillis() - startTs : 0;
  }

}
