package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.vo.request.SysUserQueryVO;
import com.cody.seed.modules.vo.response.SysUserResponseVO;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 新增用户
     *
     * @param user          user
     * @param selectedRoles selectedRoles
     * @return boolean
     */
    boolean insertUser(SysUser user, String selectedRoles);

    /**
     * 获取列表
     *
     * @param page           page
     * @param sysUserQueryVO sysUserQueryVO
     * @return IPage<SysUserResponseVO>
     */
    IPage<SysUserResponseVO> getList(Page<SysUserResponseVO> page, SysUserQueryVO sysUserQueryVO);

    /**
     * 更新用户
     *
     * @param user          user
     * @param selectedRoles selectedRoles
     * @return boolean
     */
    boolean updateUser(SysUser user, String selectedRoles);

    /**
     * 批量更新用户部门id
     *
     * @param deptId     deptId
     * @param userIdList userIdList
     * @return boolean
     */
    boolean updateDeptIdByUserIds(String deptId, List<String> userIdList);

    /**
     * 批量 更新用户状态
     *
     * @param status status
     * @param ids    ids
     * @return boolean
     */
    boolean frozenBatch(boolean status, String ids);

    /**
     * 删除用户部门关系
     *
     * @param userId userId
     * @return boolean
     */
    boolean deleteUserDept(String userId);

    /**
     * 批量删除用户部门关系
     *
     * @param userIds userIds
     * @return boolean
     */
    boolean deleteUserDeptBatch(String userIds);

    /**
     * 查询用户权限
     *
     * @return List<SysMenu>
     */
    List<SysMenu> getUserNav(String userName);

    /**
     * 查询用户信息
     *
     * @param username username
     * @param id       id
     * @return SysUser
     */
    SysUser findByUsername(String username, Long id);

    /**
     * 修改密码
     *
     * @param userName userName
     * @param password password
     * @return Boolean
     */
    Boolean changePassword(String userName, String password);

}
