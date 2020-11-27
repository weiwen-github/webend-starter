package com.ww.common.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author weiwen
 * @date 2020/11/27
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "")
public class IdsDto {
  /** ID集合 */
  @NotNull(message = "ids不能为空")
  @ApiModelProperty(value = "ID集合")
  private List<Long> ids;
}
