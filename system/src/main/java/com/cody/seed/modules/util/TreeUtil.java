package com.cody.seed.modules.util;

import com.cody.seed.modules.vo.response.SysUserMenuResponseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形数据生成工具类
 */
public class TreeUtil {

    public static List<SysUserMenuResponseVO> buildMenuTree(List<SysUserMenuResponseVO> allMenu) {
        // 根节点
        List<SysUserMenuResponseVO> rootMenu = new ArrayList<>(16);
        for (SysUserMenuResponseVO nav : allMenu) {
            //父节点是0的，为根节点
            if ("0".equals(nav.getPid())) {
                rootMenu.add(nav);
            }
        }
        for (SysUserMenuResponseVO nav : rootMenu) {
            nav.setChildren(getMenuChild(nav.getId(), rootMenu));
        }

        return rootMenu;
    }

    private static List<SysUserMenuResponseVO> getMenuChild(String id, List<SysUserMenuResponseVO> allMenu) {
        List<SysUserMenuResponseVO> childList = new ArrayList<>(16);
        // 子菜单
        for (SysUserMenuResponseVO nav : allMenu) {
            if (nav.getPid().equals(id)) {
                childList.add(nav);
            }
        }
        for (SysUserMenuResponseVO nav : childList) {
            nav.setChildren(getMenuChild(nav.getId(), childList));
        }
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
