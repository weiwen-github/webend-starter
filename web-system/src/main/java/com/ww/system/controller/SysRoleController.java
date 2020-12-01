package com.ww.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.common.constant.ResponseConst;
import com.ww.common.controller.BaseController;
import com.ww.common.dto.request.IdDto;
import com.ww.common.dto.request.IdsDto;
import com.ww.common.dto.request.PageDto;
import com.ww.common.dto.response.PageResp;
import com.ww.common.dto.response.RespBody;
import com.ww.common.utils.CommonUtils;
import com.ww.system.entity.SysRole;
import com.ww.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色前端控制器
 *
 * @author ww
 * @since 2020-12-01
 */
@Api(tags = "aidp-SysRole")
@RestController
@RequestMapping("/api/v1/sysRole")

public class SysRoleController extends BaseController {

  private final Logger logger=LoggerFactory.getLogger(SysRoleController.class);

  @Autowired
  public ISysRoleService sysRoleService;

  /**
   * 列表
   *
   * @param pageDto
   * @return com.ww.common.dto.response.PageResp
   */
  @ApiOperation("列表")
  @PostMapping(value = "/list")
  public PageResp<SysRole> list(@RequestBody PageDto pageDto){
    Page<SysRole> pages=getPage(pageDto);
    //pages= sysRoleService.listPageWithParams(pageDto,pages,null);
    //Page<SysRoleDto> dtoPages=getPageDTOFromPageDao(pages, SysRoleDto.class);
    return getPageResultFromPage(pages);
  }

  /**
   * 获取详情
   *
   * @param idDto
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("详情")
  @PostMapping("/detail")
  public RespBody<SysRole> detail(@RequestBody IdDto idDto) {
    Long id = idDto.getId();
    if (CommonUtils.isNullOrEmpty(id)) {
      return RespBody.error("请输入ID");
    }
    SysRole entity = sysRoleService.getById(id);
    return RespBody.ok().data(entity);
  }

  /**
   * 编辑
   *
   * @param entity
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("编辑")
  @PostMapping("/update")
  public RespBody update(@RequestBody SysRole entity) {
    boolean update = sysRoleService.updateById(entity);
    if (update) {
      return RespBody.ok(ResponseConst.UPDATE_OK);
    } else {
      return RespBody.error(ResponseConst.UPDATE_ERROR);
    }
  }

  /**
   * 删除
   *
   * @param idDto
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("删除")
  @PostMapping("/remove")
  public RespBody remove(@RequestBody IdDto idDto) {
    Long id = idDto.getId();
    if (CommonUtils.isNullOrEmpty(id)) {
      return RespBody.error("请输入ID");
    }
    boolean remove = sysRoleService.removeById(id);
    if (remove) {
      return RespBody.ok(ResponseConst.REMOVE_OK);
    } else {
      return RespBody.error(ResponseConst.REMOVE_ERROR);
    }
  }

  /**
   * 批量删除
   *
   * @param idsDto
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("批量删除")
  @PostMapping("/removeAny")
  public RespBody removeAny(@RequestBody IdsDto idsDto) {
    List<Long> ids = idsDto.getIds();
    if (CommonUtils.isNullOrEmpty(ids)) {
      return RespBody.error("请输入ID");
    }
    boolean remove = sysRoleService.removeByIds(ids);
    if (remove) {
      return RespBody.ok(ResponseConst.REMOVE_OK);
    } else {
      return RespBody.error(ResponseConst.REMOVE_ERROR);
    }
  }

  /**
   * 删除所有
   *
   * @return com.ww.common.dto.response.RespBody
   */
  @ApiOperation("删除所有")
  @PostMapping("/removeAll")
  public RespBody removeAll() {
    boolean remove = sysRoleService.remove(null);
    if (remove) {
      return RespBody.ok(ResponseConst.REMOVE_OK);
    } else {
      return RespBody.error(ResponseConst.REMOVE_ERROR);
    }
  }

}