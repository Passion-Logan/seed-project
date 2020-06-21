package com.cody.seed.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.entity.SysUser;

import java.util.List;

public interface ISysUserService extends IService<SysUser> {

    /**
     * 新增用户
     *
     * @param user
     * @param selectedRoles
     * @return
     */
    boolean insertUser(SysUser user, String selectedRoles);

    /**
     * 更新用户
     *
     * @param user
     * @param selectedRoles
     * @return
     */
    boolean updateUser(SysUser user, String selectedRoles);

    /**
     * 批量更新用户部门id
     *
     * @param deptId
     * @param userIdList
     * @return
     */
    boolean updateDeptIdByUserIds(String deptId, List<String> userIdList);

    /**
     * 批量 更新用户状态
     *
     * @param status
     * @param ids
     * @return
     */
    boolean frozenBatch(boolean status, String ids);

    /**
     * 删除用户部门关系
     *
     * @param userId
     * @return
     */
    boolean deleteUserDept(String userId);

    /**
     * 批量删除用户部门关系
     *
     * @param userIds
     * @return
     */
    boolean deleteUserDeptBatch(String userIds);

    /**
     * 查询用户权限
     *
     * @return
     */
    List<SysMenu> getUserNav(String userName);

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 修改密码
     *
     * @param userName
     * @param password
     * @return
     */
    boolean changePassword(String userName, String password);

}
