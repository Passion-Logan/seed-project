package com.cody.seed.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cody.common.api.vo.Result;
import com.cody.seed.modules.system.entity.SysRole;
import com.cody.seed.modules.system.service.ISysRoleMenuService;
import com.cody.seed.modules.system.service.ISysRoleService;
import com.cody.seed.modules.vo.request.SysRoleQueryVO;
import com.cody.seed.modules.vo.response.SysRoleResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 系统-角色管理
 *
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@RestController
@Api(value = "SysRoleController", tags = "系统-角色管理")
@RequestMapping("/sys/role")
@Validated
@Slf4j
public class SysRoleController {

    @Resource
    private ISysRoleService roleService;

    @Resource
    private ISysRoleMenuService roleMenuService;

    @ApiOperation(value = "查询所有角色集合")
    @GetMapping("getAllList")
    public Result<List<SysRoleResponseVO>> getAllList() {
        SysRoleQueryVO vo = new SysRoleQueryVO();
        Page<SysRoleResponseVO> page = new Page<>(1, 999);
        IPage<SysRoleResponseVO> data = roleService.getList(page, vo);

        return Result.ok(data.getRecords());
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("getPageList")
    public Result<List<SysRoleResponseVO>> selectPageList(@RequestBody @Valid SysRoleQueryVO queryVO) {
        Page<SysRoleResponseVO> page = new Page<>(queryVO.getCurrent(), queryVO.getPageSize());
        IPage<SysRoleResponseVO> data = roleService.getList(page, queryVO);

        return Result.ok(data.getRecords(), (int) data.getTotal());
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("addRole")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> addRole(@RequestBody @Valid SysRole role) {
        roleService.save(role);
        return Result.ok();
    }

    @ApiOperation(value = "编辑角色")
    @PutMapping("updateRole")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> updateRole(@RequestBody @Valid SysRole user) {
        roleService.updateById(user);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("removeRole")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> removeRole(@RequestBody JSONObject object) {
        roleService.removeByIds(Arrays.asList(object.getString("ids").split(",")));
        return Result.ok();
    }

    @ApiOperation(value = "保存角色授权")
    @PutMapping("saveRolePermission")
    public Result<Void> saveRolePermission(@RequestBody JSONObject json) {
        Long roleId = json.getLong("roleId");
        String permissionIds = json.getString("permissionIds");
        roleMenuService.saveRolePermission(roleId, permissionIds);
        return Result.ok();
    }

}
