package com.ww.common.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableList;
import com.ww.common.dto.request.PageDto;
import com.ww.common.dto.response.PageResp;
import com.ww.common.entity.PageResult;
import com.ww.common.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ww
 * @date 2020/11/12
 */
public class BaseController {
  private static final char UNDERLINE = '_';
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 通过PageDTO生成page对象
   *
   * @param PageDTO
   * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
   */
  public <T> Page<T> getPage(PageDto PageDTO) {
    Integer pageNumber = PageDTO.getPageNumber();
    Integer pageSize = PageDTO.getPageSize();
    String sort = transformField(PageDTO.getSortField());
    String order = PageDTO.getSortOrder();

    return getPage(pageNumber, pageSize, sort, order);
  }

  /**
   * page中抽取获取返回信息
   *
   * @param page
   * @return com.xw.common.respon.NewPageRet
   */
  public PageResp getPageResFromPage(Page page) {
    return new PageResp<>()
        .setTotal(page.getTotal())
        .setPagination(new PageResult(page.getTotal(), page.getSize(), page.getCurrent()))
        .setCode(0)
        .setData(page.getRecords());
  }

  /**
   * 驼峰命名转换
   *
   * @param sortField
   * @return java.lang.String
   */
  private String transformField(String sortField) {
    if (CommonUtils.isNullOrEmpty(sortField)) {
      return sortField;
    }
    int len = sortField.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = sortField.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.append(UNDERLINE);
      }
      // 统一都转小写
      sb.append(Character.toLowerCase(c));
    }
    return sb.toString();
  }

  /**
   * 获取分页对象
   *
   * @param current
   * @param size
   * @param sort
   * @param order
   * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
   */
  public <T> Page<T> getPage(int current, int size, String sort, String order) {
    OrderItem orderItem = new OrderItem();
    orderItem.setColumn(sort);
    Page<T> page = new Page<T>(current, size);
    orderItem.setAsc(!"desc".equals(order));
    page.setOrders(ImmutableList.of(orderItem));
    return page;
  }
}
