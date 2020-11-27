package com.ww.business.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author ww
 * @date 2020/11/12
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "请求参数")
public class ReqDto {
  @ApiModelProperty(value = "时间参数")
  private LocalDate dateTime;
}
