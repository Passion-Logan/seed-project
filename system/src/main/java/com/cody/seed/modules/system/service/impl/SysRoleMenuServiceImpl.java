package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysRoleMenu;
import com.cody.seed.modules.system.mapper.SysRoleMenuMapper;
import com.cody.seed.modules.system.service.ISysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 保存用户角色收取南
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
//		sysRoleMenuMapper.delete(roleMenuDO);

//		sysRoleMenuMapper.delete()

        //批量添加角色菜单
        String[] menuIds = permissionIds.split(",");
        List<SysRoleMenu> list = new ArrayList<>();
        for (String menuId : menuIds) {
            SysRoleMenu sysRoleMenuDO = new SysRoleMenu();
            sysRoleMenuDO.setRoleId(roleId);
            sysRoleMenuDO.setMenuId(menuId);
            list.add(sysRoleMenuDO);
        }

//		sysRoleMenuMapper.insertInBatch(list);
    }
}