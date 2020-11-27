package com.ww.system.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author ww
 * @date 2020/11/26
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户Dto")
public class SysUserDto {

  /** {name:"用户ID,主键"} */
  @ApiModelProperty(value = "用户ID,主键")
  private Long userId;

  /** {name:"用户名"} */
  @ApiModelProperty(value = "用户名")
  private String userName;

  /** {name:"密码"} */
  @ApiModelProperty(value = "密码")
  private String password;

  /** {name:"用户真实姓名"} */
  @ApiModelProperty(value = "用户真实姓名")
  private String userRealName;

  /** 创建用户id */
  @ApiModelProperty(value = "创建用户id")
  private Long createUserId;

  /** {name:" 是否删除",desc："0--有效、1--已删除"} */
  @ApiModelProperty(value = "是否删除")
  private Integer deleteFlag;

  /** {name:"添加时间"} */
  @ApiModelProperty(value = "添加时间")
  private LocalDateTime gmtCreate;

  /** {name:"修改时间"} */
  @ApiModelProperty(value = "修改时间")
  private LocalDateTime gmtModified;

  @ApiModelProperty(value = "用户角色ID集合")
  private Set<Long> roleIdSet;

  @ApiModelProperty(value = "用户角色标识集合")
  private List<String> roleSignList;
}
