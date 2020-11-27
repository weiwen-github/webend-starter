package com.ww.common.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页处理对象
 * @author ww
 */
public class PageResult {

  @ApiModelProperty(value = "总记录数")
  public long total;

  @ApiModelProperty(value = "页面记录数")
  public long perPage;

  @ApiModelProperty(value = "当前页")
  public long currentPage;

  @ApiModelProperty(value = "最后一页")
  public long lastPage;

  @ApiModelProperty(value = "从第几条数据开始")
  public long from;

  @ApiModelProperty(value = "到第几条数据结束")
  public long to;

  public PageResult(long total, long perPageSize, long currentPage) {
    this.perPage = perPageSize;
    this.total = total;
    this.currentPage = currentPage;
    boolean isComplete = total % perPageSize == 0;
    lastPage = total / perPageSize + (isComplete ? 0 : 1);

    from = (currentPage - 1) * perPageSize + 1;
    if (currentPage == lastPage && !isComplete) {
      to = (currentPage - 1) * perPageSize + total % perPageSize;
    } else {

      to = currentPage * perPageSize;
    }
  }
}
