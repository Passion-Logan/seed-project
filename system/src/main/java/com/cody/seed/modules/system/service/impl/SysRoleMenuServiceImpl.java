package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysRoleMenu;
import com.cody.seed.modules.system.mapper.SysRoleMenuMapper;
import com.cody.seed.modules.system.service.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 保存用户角色权限
     *
     * @param roleId
     * @param permissionIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermission(String roleId, String permissionIds) {
        //先删除角色菜单
        SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(roleId);

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("role_id", roleId);
        this.remove(wrapper);

        //批量添加角色菜单
        String[] menuIds = permissionIds.split(",");
        List<SysRoleMenu> list = new ArrayList<>();
        for (String menuId : menuIds) {
            SysRoleMenu sysRoleMenuDO = new SysRoleMenu();
            sysRoleMenuDO.setRoleId(roleId);
            sysRoleMenuDO.setMenuId(menuId);
            list.add(sysRoleMenuDO);
        }

        this.saveBatch(list);
    }

    @Override
    public List<SysRoleMenu> getListByRoleId(String roleId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("role_id", roleId);

        return roleMenuMapper.selectList(wrapper);
    }
}
