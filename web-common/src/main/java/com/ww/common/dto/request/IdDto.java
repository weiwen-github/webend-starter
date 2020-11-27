package com.ww.common.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author ww
 * @date 2020/11/27
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "")
public class IdDto {
  /** 主键ID */
  @NotNull(message = "id不能为空")
  @ApiModelProperty(value = "主键ID")
  private Long id;
}
