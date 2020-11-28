package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门实体类
 * @author weiwen
 * @date 2020/11/28
 */
@Data
@TableName("sys_dept")
public class SysDept implements Serializable {

  /** 部门ID */
  @TableId(value = "dept_id", type = IdType.AUTO)
  private Long deptId;

  /** 上级部门ID */
  private Long parentId;

  /** 所属机构(单位)ID */
  private Long orgId;

  /** 部门名称 */
  private String deptName;

  /** 创建用户id */
  private Long createUserId;

  /** {name:" 是否删除",desc："0--有效、1--已删除"} */
  @TableLogic
  private Integer deleteFlag;

  /** {name:"添加时间"} */
  private LocalDateTime gmtCreate;

  /** {name:"修改时间"} */
  private LocalDateTime gmtModified;

}
