package com.cody.seed.modules.system.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cody.common.api.vo.Result;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.system.entity.SysUserRole;
import com.cody.seed.modules.system.service.ISysUserRoleService;
import com.cody.seed.modules.system.service.ISysUserService;
import com.cody.seed.modules.vo.request.SysUserQueryVO;
import com.cody.seed.modules.vo.request.SysUserRoleRequestVO;
import com.cody.seed.modules.vo.response.SysUserResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "SysUserController", tags = "系统-用户管理")
@RequestMapping("/sys/user")
@Validated
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @ApiOperation(value = "分页查询")
    @PostMapping("getPageList")
    public Result selectPageList(@RequestBody @Valid SysUserQueryVO sysUserQueryVO) {
        Page<SysUserResponseVO> page = new Page<>(sysUserQueryVO.getCurrent(), sysUserQueryVO.getPageSize());
        IPage<SysUserResponseVO> data = sysUserService.getList(page, sysUserQueryVO);

        return Result.ok(data.getRecords(), (int) data.getTotal());
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("addUser")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result addUser(@RequestBody @Valid SysUser user) {
        String encrypt = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encrypt);
        sysUserService.save(user);
        return Result.ok();
    }

    @ApiOperation(value = "编辑用户")
    @PutMapping("updateUser")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result updateUser(@RequestBody @Valid SysUserRoleRequestVO user) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(user, entity);

        if (StrUtil.isNotEmpty(user.getRoleIds())) {
            QueryWrapper wrapper = new QueryWrapper();
            String userId = user.getId();
            wrapper.eq("user_id", userId);
            sysUserRoleService.remove(wrapper);
            String[] ids = user.getRoleIds().split(",");
            List<SysUserRole> userRoles = new ArrayList<>(ids.length);
            for (String id : ids) {
                userRoles.add(SysUserRole.builder().userId(userId).roleId(id).build());
            }
            sysUserRoleService.saveBatch(userRoles);
        }
        sysUserService.updateById(entity);
        return Result.ok();
    }

    @ApiOperation(value = "修改密码")
    @GetMapping("updatePassword")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result updatePassword(String userName, String password) {
        sysUserService.changePassword(userName, password);
        return Result.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("removeUser")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result removeUser(@RequestBody JSONObject object) {
        sysUserService.removeByIds(Arrays.asList(object.getString("ids").split(",")));
        return Result.ok();
    }

    @GetMapping(value = "获取用户角色")
    @DeleteMapping("getUserRole")
    public Result getUserRole(@ApiParam(name = "userid", value = "userid", required = true) @RequestParam(value = "userId") String userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        List<SysUserRole> userRoles = sysUserRoleService.list(wrapper);
        List<String> roleList = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        String[] roleIds = new String[roleList.size()];

        if (roleList.size() > 0) {
            roleIds = roleList.stream().toArray(String[]::new);
        }
        return Result.ok(roleIds);
    }


}
