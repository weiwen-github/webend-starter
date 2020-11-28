package com.ww.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 权限实体类
 *
 * @author ww
 * @date 2020/11/13
 */
@Data
@TableName("sys_permission")
public class SysPermission implements Serializable {
  public static final String ID = "id";
  public static final String PERMS = "perms";
  public static final String URL = "url";
  public static final String GMT_CREATE = "gmt_create";
  public static final String GMT_MODIFIED = "gmt_modified";

  public static HashMap<String, String> fieldAlias =
      new HashMap<String, String>() {
        {
          put("id", "主键");
          put("perms", "权限名称");
          put("ulr", "请求路径");
          put("gmtCreate", "创建时间");
          put("gmtModified", "修改时间");
        }
      };

  /** {name:"主键"} */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** 授权标识(多个用逗号分隔，如：user:list,user:create) */
  private String perms;

  /** 请求路径 */
  private String url;

  /** {name:"创建时间"} */
  private LocalDateTime gmtCreate;

  /** {name:"修改时间"} */
  private LocalDateTime gmtModified;
}
