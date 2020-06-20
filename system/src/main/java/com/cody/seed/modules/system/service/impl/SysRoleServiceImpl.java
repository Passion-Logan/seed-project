package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.common.util.BeanUtil;
import com.cody.seed.modules.system.entity.SysRole;
import com.cody.seed.modules.system.mapper.SysRoleMapper;
import com.cody.seed.modules.system.service.ISysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRolesByUserId(String userId) {

        List<SysRole> list = roleMapper.getRolesByUserId(userId);
        return BeanUtil.convert(list, SysRole.class);
    }
}