package com.ww.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.system.dao.SysDeptMapper;
import com.ww.system.dao.SysOrgMapper;
import com.ww.system.entity.SysDept;
import com.ww.system.entity.SysOrg;
import com.ww.system.service.ISysDeptService;
import com.ww.system.service.ISysOrgService;
import org.springframework.stereotype.Service;

/**
 * @author ww
 * @date 2020/11/12
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept>
    implements ISysDeptService {}
