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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@Api(value = "SysRoleController", tags = "系统-角色管理")
@RequestMapping("/sys/role")
@Validated
@Slf4j
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysRoleMenuService roleMenuService;

    @ApiOperation(value = "查询所有角色集合")
    @GetMapping("getAllList")
    public Result getAllList() {
        SysRoleQueryVO vo = new SysRoleQueryVO();
        Page<SysRoleResponseVO> page = new Page<>(1, 999);
        IPage<SysRoleResponseVO> data = roleService.getList(page, vo);

        return Result.ok(data.getRecords());
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("getPageList")
    public Result selectPageList(@RequestBody @Valid SysRoleQueryVO queryVO) {
        Page<SysRoleResponseVO> page = new Page<>(queryVO.getCurrent(), queryVO.getPageSize());
        IPage<SysRoleResponseVO> data = roleService.getList(page, queryVO);

        return Result.ok(data.getRecords(), (int) data.getTotal());
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("addRole")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result addRole(@RequestBody @Valid SysRole role) {
        roleService.save(role);
        return Result.ok();
    }

    @ApiOperation(value = "编辑角色")
    @PutMapping("updateRole")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result updateRole(@RequestBody @Valid SysRole user) {
        roleService.updateById(user);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("removeRole")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result removeRole(@RequestBody JSONObject object) {
        roleService.removeByIds(Arrays.asList(object.getString("ids").split(",")));
        return Result.ok();
    }

    @ApiOperation(value = "保存角色授权")
    @PutMapping("saveRolePermission")
    public Result saveRolePermission(@RequestBody JSONObject json) {
        Long roleId = json.getLong("roleId");
        String permissionIds = json.getString("permissionIds");
        roleMenuService.saveRolePermission(roleId, permissionIds);
        return Result.ok();
    }

}
