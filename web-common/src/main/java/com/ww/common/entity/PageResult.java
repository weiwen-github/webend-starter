package com.ww.common.entity;

/**
 * 分页处理对象
 */
public class PageResult {

  public long total;
  public long perPage;
  public long currentPage;
  public long lastPage;
  public long from;
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
