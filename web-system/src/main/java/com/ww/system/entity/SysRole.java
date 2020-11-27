package com.ww.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 角色实体类
 *
 * @author ww
 * @date 2020/11/12
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {

  public static final String ROLE_ID = "role_id";
  public static final String ROLE_NAME = "role_name";
  public static final String ROLE_SIGN = "role_sign";
  public static final String REMARK = "remark";
  public static final String USER_ID_CREATE = "user_id_create";
  public static final String GMT_CREATE = "gmt_create";
  public static final String GMT_MODIFIED = "gmt_modified";
  private static final long serialVersionUID = 1L;
  public static HashMap<String, String> fieldAlias =
      new HashMap<String, String>() {
        {
          put("roleId", "角色id");
          put("roleName", "角色名称");
          put("roleSign", "角色标识");
          put("remark", "备注");
          put("userIdCreate", "创建者id");
          put("gmtCreate", "创建时间");
          put("gmtModified", "修改时间");
        }
      };
  /** {name:"角色id"} */
  @TableId(value = "role_id", type = IdType.AUTO)
  private Long roleId;

  /** {name:"角色名称"} */
  private String roleName;

  /** {name:"角色标识"} */
  private String roleSign;

  /** {name:"备注"} */
  @TableField(updateStrategy = FieldStrategy.IGNORED)
  private String remark;

  /** {name:"创建者id"} */
  private Long userIdCreate;

  /** {name:"创建时间"} */
  private LocalDateTime gmtCreate;

  /** {name:"修改时间"} */
  private LocalDateTime gmtModified;

  /** {name:"权限集合"} */
  @TableField(exist = false)
  @JSONField(serialize = false)
  private Set<Long> permsIdSet;

  @TableField(exist = false)
  @JSONField(serialize = false)
  private List<SysPermission> perms;
}
