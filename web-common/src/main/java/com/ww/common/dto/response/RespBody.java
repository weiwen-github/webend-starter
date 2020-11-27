package com.ww.common.dto.response;

import com.ww.common.constant.ResponseConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标准返回类
 *
 * @author weiwen
 * @date 2020/11/12
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "标准返回")
public class RespBody<T> {

  @ApiModelProperty(value = "状态码")
  private Integer code;

  @ApiModelProperty(value = "返回信息")
  private String msg;

  @ApiModelProperty(value = "返回数据")
  private T data;

  @ApiModelProperty(value = "日志追踪")
  private String traceMsg;

  public RespBody() {}

  public static RespBody error(int code) {
    RespBody<Object> resultObject = new RespBody<>();
    resultObject.setCode(code);
    return resultObject;
  }

  public static RespBody error(String msg) {
    RespBody<Object> resultObject = new RespBody<>();
    resultObject.setCode(ResponseConst.CODE_ERROR);
    resultObject.setMsg(msg);
    return resultObject;
  }

  public static RespBody error() {
    return RespBody.error(ResponseConst.DESC_EXCEPTION_ERROR);
  }

  public static RespBody error(int code, String msg) {
    RespBody<Object> resultObject = new RespBody<>();
    resultObject.setCode(code);
    resultObject.setMsg(msg);
    return resultObject;
  }

  public static RespBody error(int code, String msg, String traceMsg) {
    RespBody<Object> resultObject = new RespBody<>();
    resultObject.setCode(code);
    resultObject.setMsg(msg);
    resultObject.setTraceMsg(traceMsg);
    return resultObject;
  }

  public static RespBody ok(String msg) {
    RespBody<Object> resultObject = new RespBody<>();
    resultObject.setCode(ResponseConst.CODE_OK);
    resultObject.setMsg(msg);
    return resultObject;
  }

  public static RespBody ok() {
    return RespBody.ok(ResponseConst.DESC_OK);
  }

  public RespBody data(T data) {
    return this.setData(data);
  }

  public Integer code() {
    return this.getCode();
  }
}
