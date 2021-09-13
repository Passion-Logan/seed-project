package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysRole;
import com.cody.seed.modules.system.mapper.SysRoleMapper;
import com.cody.seed.modules.system.service.ISysRoleService;
import com.cody.seed.modules.util.BeanUtil;
import com.cody.seed.modules.vo.request.SysRoleQueryVO;
import com.cody.seed.modules.vo.response.SysRoleResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    /**
     * 查询用户角色
     *
     * @param userId userId
     * @return SysRole
     */
    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        List<SysRole> list = roleMapper.getRolesByUserId(userId);
        return BeanUtil.convert(list, SysRole.class);
    }

    @Override
    public IPage<SysRoleResponseVO> getList(Page<SysRoleResponseVO> page, SysRoleQueryVO query) {
        return roleMapper.getList(page, query);
    }
}
