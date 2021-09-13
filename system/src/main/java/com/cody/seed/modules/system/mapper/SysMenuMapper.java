package com.cody.seed.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.vo.response.SysUserMenuResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取菜单列表
     *
     * @return
     */
    List<SysUserMenuResponseVO> getList();

    /**
     * 根据pid批量查询
     *
     * @return
     */
    List<SysMenu> getListByPid(@Param("pid") String pid);

    /**
     * 根据用户id查询
     *
     * @param userId userId
     * @return
     */
    List<SysMenu> findMenuByUserId(@Param("userId") Long userId);

    /**
     * 查询用户按钮权限
     *
     * @param userId
     * @return
     */
    List<String> getPermissionsByUserId(@Param("userId") String userId);

    /**
     * 查询权限标识是否重复
     *
     * @param permission
     * @param id
     * @return
     */
    int checkPermission(@Param("permission") String permission, @Param("id") String id);

}
