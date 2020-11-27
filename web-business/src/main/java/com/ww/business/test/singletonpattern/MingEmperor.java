package com.ww.business.test.singletonpattern;

import java.util.Random;

/**
 * 明朝皇帝实现类
 *
 * @author ww
 * @date 2020/11/17
 */
public class MingEmperor implements Iemperor {
  /** 增加volatile修饰，防止虚拟机指令重排序 */
  private static volatile MingEmperor emperor;
  /** 皇帝身份id */
  private final String id;
  /** 防止破坏单例 */
  private MingEmperor(String id) {
    this.id = id;
  }

  public static synchronized MingEmperor getEmperor() {
    if (emperor == null) {
      // 采用同步代码块，缩小锁定范围，比直接同步方法效率要略高
      synchronized (MingEmperor.class) {
        // 这里再判断为空，是防止别的线程已经完成了实例化，这里重复实例化了，就违反了单例。
        if (emperor == null) {
          emperor = new MingEmperor(new Random().nextInt(10) + "");
        }
      }
    }
    return emperor;
  }

  @Override
  public void sayCommand(String str) {
    System.out.println(str + "----------我是皇帝，这是我的id=" + id);
  }
}
