package com.ww.common.dto.response;

import cn.hutool.db.PageResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 *
 * @author ww
 * @date 2020/11/18
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "标准分页返回")
public class PageResp {
  @ApiModelProperty(value = "状态码")
  private Integer code;

  @ApiModelProperty(value = "记录总数")
  Long total;

  @ApiModelProperty(value = "返回数据")
  private List<T> data;

  @ApiModelProperty(value = "分页信息")
  PageResult pagination;
}
