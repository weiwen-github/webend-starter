package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 机构实体类
 *
 * @author weiwen
 * @date 2020/11/28
 */
@Data
@TableName("sys_org")
public class SysOrg {
  /** 机构ID */
  @TableId(value = "org_id", type = IdType.AUTO)
  private Long orgId;

  /** 机构名称 */
  private String orgName;

  /** 创建用户id */
  private Long createUserId;

  /** {name:" 是否删除",desc："0--有效、1--已删除"} */
  @TableLogic private Integer deleteFlag;

  /** {name:"添加时间"} */
  private LocalDateTime gmtCreate;

  /** {name:"修改时间"} */
  private LocalDateTime gmtModified;
}
