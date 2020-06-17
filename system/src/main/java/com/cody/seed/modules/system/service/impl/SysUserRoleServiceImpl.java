package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysUserRole;
import com.cody.seed.modules.system.mapper.SysUserRoleMapper;
import com.cody.seed.modules.system.service.ISysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

}
