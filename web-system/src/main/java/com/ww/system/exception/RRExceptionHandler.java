package com.ww.system.exception;

import com.ww.common.dto.response.RespBody;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author ww
 * @date 2020/11/14
 */
@RestControllerAdvice
public class RRExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 自定义异常
   */
  @ExceptionHandler(RRException.class)
  public RespBody handleRRException(RRException e) {
    RespBody result = new RespBody();
    result.setCode(e.getCode());
    result.setMsg(e.getMessage());
    return result;
  }

  @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
  public RespBody handleAuthorizationException(AuthorizationException e) {
    logger.error(e.getMessage(), e);
    return RespBody.error("没有权限，请联系管理员授权");
  }

}
