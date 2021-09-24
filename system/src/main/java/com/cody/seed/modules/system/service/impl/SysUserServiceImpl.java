package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.common.util.MD5;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.system.entity.SysUserRole;
import com.cody.seed.modules.system.execption.CustomExecption;
import com.cody.seed.modules.system.mapper.SysUserMapper;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.system.service.ISysUserRoleService;
import com.cody.seed.modules.system.service.ISysUserService;
import com.cody.seed.modules.util.BeanUtil;
import com.cody.seed.modules.vo.request.SysUserPwdVO;
import com.cody.seed.modules.vo.request.SysUserQueryVO;
import com.cody.seed.modules.vo.response.SysUserResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @Description: TODO
 * @date: 2020年06月16日 18:21
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysMenuService sysMenuService;

    /**
     * 新增用户
     *
     * @param user          user
     * @param selectedRoles selectedRoles
     * @return boolean
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
            sysUserRoleService.saveBatch(list);
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
     * @param user          user
     * @param selectedRoles selectedRoles
     * @return boolean
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

            sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getId()));

            String[] roleId = selectedRoles.split(",");
            //再添加用户角色
            List<SysUserRole> list = new ArrayList<>();
            insertUserRole(roleId, user, list);
            sysUserRoleService.saveBatch(list);
        }

        return true;
    }

    /**
     * 批量更新用户部门id
     *
     * @param deptId     deptId
     * @param userIdList userIdList
     * @return boolean
     */
    @Override
    public boolean updateDeptIdByUserIds(String deptId, List<String> userIdList) {
        return sysUserMapper.updateDeptIdByUserIds(deptId, userIdList) > 0;
    }

    /**
     * 批量 更新用户状态
     *
     * @param status status
     * @param ids    ids
     * @return boolean
     */
    @Override
    public boolean frozenBatch(boolean status, String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        return sysUserMapper.frozenBatch(status, idList) > 0;
    }

    /**
     * 删除用户部门关系
     *
     * @param userId userId
     * @return boolean
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
     * @param userIds userIds
     * @return boolean
     */
    @Override
    public boolean deleteUserDeptBatch(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        return sysUserMapper.updateDeptIdByUserIds(null, list) > 0;
    }

    /**
     * 查询用户权限
     *
     * @param userName userName
     * @return List<SysMenu>
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
     * @param username username
     * @param id       id
     * @return SysUser
     */
    @Override
    public SysUser findByUsername(String username, Long id) {
        return sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .ne(Objects.nonNull(id), SysUser::getId, id)
                .eq(SysUser::getUserName, username)
        );
    }

    /**
     * 修改密码
     *
     * @param userName userName
     * @param password password
     * @return Boolean
     */
    @Override
    public Boolean changePassword(String userName, String password) {
        //查询用户信息
        SysUser userDO = sysUserMapper.findByName(userName);
        if (userDO == null) {
            throw new CustomExecption("不存在此用户");
        }
        //加密用户密码
        String encrypt = new BCryptPasswordEncoder().encode(password);
        userDO.setPassword(encrypt);
        return sysUserMapper.updateById(userDO) > 0;
    }

    @Override
    public Boolean verifyPassword(SysUserPwdVO vo, Long userId) {
        SysUser byId = this.getById(userId);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        if (!encode.matches(vo.getOldPwd(), byId.getPassword())) {
            throw new CustomExecption("旧密码错误");
        }
        byId.setPassword(encode.encode(vo.getNewPwd()));
        return this.updateById(byId);
    }

    private void insertUserRole(String[] roleId, SysUser user, List<SysUserRole> list) {
        for (String id : roleId) {
            SysUserRole role = new SysUserRole();
            role.setRoleId(Long.parseLong(id));
            role.setUserId(user.getId());
            list.add(role);
        }
    }

}
