package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.execption.CustomExecption;
import com.cody.seed.modules.util.BeanUtil;
import com.cody.common.util.MD5;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.system.entity.SysUserRole;
import com.cody.seed.modules.system.mapper.SysUserMapper;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.system.service.ISysUserRoleService;
import com.cody.seed.modules.system.service.ISysUserService;
import com.cody.seed.modules.vo.request.SysUserQueryVO;
import com.cody.seed.modules.vo.response.SysUserResponseVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserRoleService sysuserRoleservice;

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 新增用户
     *
     * @param user
     * @param selectedRoles
     * @return
     */
    @Override
    public boolean insertUser(SysUser user, String selectedRoles) {
        //加密用户密码
        String encrypt = new BCryptPasswordEncoder().encode(MD5.md5(user.getPassword()));
        user.setPassword(encrypt);
        //添加用户
        SysUser entity = BeanUtil.convert(user, SysUser.class);
        sysUserMapper.insert(entity);

        if (!StringUtils.isBlank(selectedRoles)) {
            String[] roleId = selectedRoles.split(",");
            //添加用户角色
            List<SysUserRole> list = new ArrayList<>();
            insertUserRole(roleId, user, list);
            sysuserRoleservice.saveBatch(list);
        }
        return true;
    }

    @Override
    public IPage<SysUserResponseVO> getList(Page<SysUserResponseVO> page, SysUserQueryVO sysUserQueryVO) {
        return sysUserMapper.getList(page, sysUserQueryVO);
    }

    /**
     * 更新用户
     *
     * @param user
     * @param selectedRoles
     * @return
     */
    @Override
    public boolean updateUser(SysUser user, String selectedRoles) {
        //修改用户
        SysUser entity = BeanUtil.convert(user, SysUser.class);
        sysUserMapper.updateById(entity);

        if (!StringUtils.isBlank(selectedRoles)) {
            //先删除用户角色信息
            SysUserRole userRoleDTO = new SysUserRole();
            userRoleDTO.setUserId(user.getId());
            QueryWrapper wrapper = new QueryWrapper<SysUserRole>();
            wrapper.eq("user_id", user.getId());
            sysuserRoleservice.remove(wrapper);

            String[] roleId = selectedRoles.split(",");
            //再添加用户角色
            List<SysUserRole> list = new ArrayList<>();
            insertUserRole(roleId, user, list);
            sysuserRoleservice.saveBatch(list);
        }

        return true;
    }

    /**
     * 批量更新用户部门id
     *
     * @param deptId
     * @param userIdList
     * @return
     */
    @Override
    public boolean updateDeptIdByUserIds(String deptId, List<String> userIdList) {
        return sysUserMapper.updateDeptIdByUserIds(deptId, userIdList) > 0;
    }

    /**
     * 批量 更新用户状态
     *
     * @param status
     * @param ids
     * @return
     */
    @Override
    public boolean frozenBatch(boolean status, String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        return sysUserMapper.frozenBatch(status, idList) > 0;
    }

    /**
     * 删除用户部门关系
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUserDept(String userId) {
        List<String> list = new ArrayList<>();
        list.add(userId);
        return sysUserMapper.updateDeptIdByUserIds(null, list) > 0;
    }

    /**
     * 批量删除用户部门关系
     *
     * @param userIds
     * @return
     */
    @Override
    public boolean deleteUserDeptBatch(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        return sysUserMapper.updateDeptIdByUserIds(null, list) > 0;
    }

    /**
     * 查询用户权限
     *
     * @param userName
     * @return
     */
    @Override
    public List<SysMenu> getUserNav(String userName) {
        //获取当前用户信息
        SysUser userDO = sysUserMapper.findByName(userName);
        return sysMenuService.findMenuByUserId(userDO.getId());
    }

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public SysUser findByUsername(String username) {
        SysUser user = sysUserMapper.findByName(username);
        return user;
    }

    /**
     * 修改密码
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean changePassword(String userName, String password) {
        //查询用户信息
        SysUser userDO = sysUserMapper.findByName(userName);
        if (userDO == null) {
            throw new CustomExecption("不存在此用户");
        }
        //加密用户密码
        String encrypt = new BCryptPasswordEncoder().encode(MD5.md5(password));
        SysUser modify = new SysUser();
        modify.setId(userDO.getId());
        modify.setPassword(encrypt);

        return sysUserMapper.updateById(modify) > 0;
    }

    private void insertUserRole(String[] roleId, SysUser user, List<SysUserRole> list) {
        for (String id : roleId) {
            SysUserRole role = new SysUserRole();
            role.setRoleId(id);
            role.setUserId(user.getId());
            list.add(role);
        }
    }

}
