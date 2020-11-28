package com.ww.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ww.system.dao.SysOrgMapper;
import com.ww.system.entity.SysOrg;
import com.ww.system.service.ISysOrgService;
import org.springframework.stereotype.Service;

/**
 * @author ww
 * @date 2020/11/12
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg>
    implements ISysOrgService {}
