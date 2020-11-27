package com.ww.common.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author ww
 * @date 2020/11/18
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "基础分页")
public class PageDto {
  @NotNull
  @ApiModelProperty(value = "页码", example = "1")
  Integer pageNumber;

  @NotNull
  @ApiModelProperty(value = "页面记录数", example = "10")
  Integer pageSize;

  @ApiModelProperty(value = "排序字段")
  String sortField;

  @ApiModelProperty(value = "升序|降序", allowableValues = "asc,desc")
  String sortOrder;
}
