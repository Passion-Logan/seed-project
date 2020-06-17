package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysRoleMenu;

public interface ISysRoleMenuService extends IService<SysRoleMenu> {


    /**
     * 保存用户角色收取南
     *
     * @param roleId
     * @param permissionIds
     */
    void saveRolePermission(String roleId, String permissionIds);
}
