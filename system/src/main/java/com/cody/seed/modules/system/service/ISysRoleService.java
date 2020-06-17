package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysRole;

import java.util.List;

public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(String userId);

}
