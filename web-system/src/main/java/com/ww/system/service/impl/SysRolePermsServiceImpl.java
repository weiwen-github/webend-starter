package com.ww.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.system.dao.SysRolePermsMapper;
import com.ww.system.entity.SysRolePerms;
import com.ww.system.service.ISysRolePermsService;
import org.springframework.stereotype.Service;

/**
 * @author weiwen
 * @date 2020/11/12
 */
@Service
public class SysRolePermsServiceImpl extends ServiceImpl<SysRolePermsMapper, SysRolePerms>
    implements ISysRolePermsService {}
