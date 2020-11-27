package com.ww.framework.shiro.manager;

import com.ww.system.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author liuhulu
 */
public class AsyncManager {

  private static final Logger logger = LoggerFactory.getLogger(AsyncManager.class);
  private static final AsyncManager me = new AsyncManager();
  /** 操作延迟10毫秒 */
  private final int OPERATE_DELAY_TIME = 10;

  /** 异步操作任务调度线程池 */
  private final ScheduledExecutorService executor =
      SpringContextUtils.getBean(ScheduledExecutorService.class);

  /** 单例模式 */
  private AsyncManager() {}

  public static AsyncManager me() {
    return me;
  }

  /**
   * 执行任务
   *
   * @param task 任务
   */
  public void execute(TimerTask task) {
    executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
  }

  /** 停止任务线程池 */
  public void shutdown() {
    if (executor != null && !executor.isShutdown()) {
      executor.shutdown();
      try {
        if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {
          executor.shutdownNow();
          if (!executor.awaitTermination(120, TimeUnit.SECONDS)) {
            logger.info("Pool did not terminate");
          }
        }
      } catch (InterruptedException ie) {
        executor.shutdownNow();
        Thread.currentThread().interrupt();
      }
    }
  }
}
