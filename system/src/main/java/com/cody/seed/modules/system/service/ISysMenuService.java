package com.cody.seed.modules.system.service;

import com.cody.common.system.service.BaseService;
import com.cody.seed.modules.system.entity.SysMenuDTO;

import java.util.List;

public interface ISysMenuService extends BaseService<SysMenuDTO> {

    /**
     * 新增菜单
     * @param menuDTO
     * @return
     */
    boolean insertMenu(SysMenuDTO menuDTO);

    /**
     * 编辑菜单
     * @param menuDTO
     * @return
     */
    boolean updateMenu(SysMenuDTO menuDTO);

    /**
     * 批量删除菜单
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Integer> ids);

    /**
     * 删除单条
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<SysMenuDTO> findMenuByUserId(Integer userId);

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<String> getPermissionsByUserId(Integer userId);

}
