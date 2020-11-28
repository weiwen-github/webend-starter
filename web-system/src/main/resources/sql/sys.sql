-- 用户表----------------------------
drop table if exists sys_user;
CREATE TABLE sys_user (
  user_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '{name:"用户ID"}',
  user_name varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '{name:"用户名"}',
  password varchar(125) NOT NULL COMMENT '{name:"用户密码"}',
  user_real_name varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '{name:"用户真实姓名"}',
  create_user_id bigint(20) NOT NULL COMMENT '{name:"创建用户ID"}',
  gmt_create timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '{name:"创建时间"}',
  gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '{name:"修改时间"}',
  delete_flag int(2) NOT NULL DEFAULT '0' COMMENT '{name:"是否删除",desc:"0-否，1-是"}',
  PRIMARY KEY (user_id),
  UNIQUE KEY idx_sys_user_username (user_name) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

-- 角色表---------------------------
drop table if exists sys_role;
CREATE TABLE sys_role (
  role_id bigint(20) NOT NULL AUTO_INCREMENT,
  role_name varchar(128) DEFAULT NULL COMMENT '角色名称',
  role_sign varchar(128) DEFAULT NULL COMMENT '角色标识',
  remark varchar(128) DEFAULT NULL COMMENT '备注',
  user_id_create bigint(20) DEFAULT NULL COMMENT '创建用户id',
  gmt_create datetime DEFAULT NULL COMMENT '创建时间',
  gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `idx_sys_role_role_name` (`role_name`),
  UNIQUE KEY `idx_sys_role_role_sign` (`role_sign`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 用户角色表-----------------------------
drop table if exists sys_user_role;
CREATE TABLE sys_user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '{name:"主键ID"}',
  user_id bigint(20) NOT NULL COMMENT '{name:"用户ID",desc:"关联sys_user.user_id"}',
  role_id bigint(20) NOT NULL COMMENT '{name:"角色ID",desc:"关联sys_role.role_id"}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- 权限表----------------------------
CREATE TABLE sys_permission (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '{name:"主键"}',
  perms varchar(125) DEFAULT NULL COMMENT '{name:"授权标识(多个用逗号分隔，如：user:list,user:create)"}',
  url varchar(125) NULL COMMENT '{name:"路径"}',
  gmt_create datetime DEFAULT NULL COMMENT '{name:"创建时间"}',
  gmt_modified timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- 角色权限表----------------------------
CREATE TABLE `sys_role_perms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '{name:"主键"}',
  `role_id` bigint(20) NOT NULL COMMENT '{name:"角色ID",desc:"关联sys_role.role_id"}',
  `perms_id` bigint(20) NOT NULL COMMENT '{name:"权限ID",desc:"关联sys_permission.id"}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- 在线用户记录---------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  session_id         varchar(50)   default ''                comment '用户会话id',
  login_name        varchar(50)   default ''                comment '登录账号',
  dept_name         varchar(50)   default ''                comment '部门名称',
  ip_addr            varchar(50)   default ''                comment '登录IP地址',
  login_location    varchar(255)  default ''                comment '登录地点',
  browser           varchar(50)   default ''                comment '浏览器类型',
  os                varchar(50)   default ''                comment '操作系统',
  status            varchar(10)   default ''                comment '在线状态on_line在线off_line离线',
  start_timestamp   datetime                                comment 'session创建时间',
  last_access_time  datetime                                comment 'session最后访问时间',
  expire_time       int(5)        default 0                 comment '超时时间，单位为分钟',
  primary key (sessionId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment = '在线用户记录';
