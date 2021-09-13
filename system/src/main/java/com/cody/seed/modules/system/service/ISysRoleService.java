package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysRole;
import com.cody.seed.modules.vo.request.SysRoleQueryVO;
import com.cody.seed.modules.vo.response.SysRoleResponseVO;

import java.util.List;

/**
 * @author wql
 * @date 2021/9/13
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询用户角色
     *
     * @param userId userId
     * @return SysRole
     */
    List<SysRole> getRolesByUserId(Long userId);

    IPage<SysRoleResponseVO> getList(Page<SysRoleResponseVO> page, SysRoleQueryVO query);

}
