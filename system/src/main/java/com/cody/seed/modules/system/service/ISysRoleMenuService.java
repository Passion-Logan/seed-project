package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysRoleMenu;

import java.util.List;

public interface ISysRoleMenuService extends IService<SysRoleMenu> {


    /**
     * 保存用户角色权限
     *
     * @param roleId        roleId
     * @param permissionIds permissionIds
     */
    void saveRolePermission(Long roleId, String permissionIds);

    /**
     * 获取角色菜单
     *
     * @param roleId roleId
     * @return SysRoleMenu
     */
    List<SysRoleMenu> getListByRoleId(String roleId);

}
