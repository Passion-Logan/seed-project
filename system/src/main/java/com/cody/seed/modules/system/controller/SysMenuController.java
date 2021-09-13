package com.cody.seed.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.cody.common.api.vo.Result;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.entity.SysRoleMenu;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.system.service.ISysRoleMenuService;
import com.cody.seed.modules.util.TreeUtil;
import com.cody.seed.modules.vo.response.SysUserMenuResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统-菜单管理
 *
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@RestController
@Api(value = "SysMenuController", tags = "系统-菜单管理")
@RequestMapping("/sys/menu")
@Validated
@Slf4j
public class SysMenuController {

    @Resource
    private ISysMenuService menuService;
    @Resource
    private ISysRoleMenuService roleMenuService;

    @ApiOperation(value = "查询角色")
    @GetMapping(value = "/queryTreeList")
    public Result<Map<String, Object>> queryTreeList() {
        Map<String, Object> resMap = new HashMap<>(1);
        resMap.put("treeList", menuService.queryTreeList());
        return Result.ok(resMap);
    }

    /**
     * 菜单树形展示
     *
     * @return List<SysUserMenuResponseVO>
     */
    @ApiOperation(value = "菜单树形展示")
    @GetMapping("list")
    public Result<List<SysUserMenuResponseVO>> selectPageList() {
        // todo:添加缓存
        List<SysUserMenuResponseVO> data = new ArrayList<>();
        List<SysUserMenuResponseVO> voList = menuService.getList();

        if (voList.size() > 0) {
            data = TreeUtil.buildMenuTree(voList);
        }

        return Result.ok(data, data.size());
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping("addMenu")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> addMenu(@RequestBody @Valid SysMenu menu) {
        menuService.save(menu);
        return Result.ok();
    }

    @ApiOperation(value = "编辑菜单")
    @PutMapping("updateMenu")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> updateMenu(@RequestBody @Valid SysMenu menu) {
        menuService.updateById(menu);
        return Result.ok();
    }

    /**
     * 删除节点菜单
     * 如果父节点存在子节点 则不删除
     *
     * @param id id
     * @return Void
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("deleteMenu")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> deleteMenu(@ApiParam(name = "id", value = "id", required = true) @RequestParam String id) {
        menuService.deleteById(id);
        return Result.ok();
    }

    /**
     * 批量删除节点菜单
     * 如果父节点存在子节点 则不删除
     *
     * @param object object
     * @return Void
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("removeMenu")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result<Void> removeMenu(@RequestBody JSONObject object) {
        List<String> ids = Arrays.asList(object.getString("ids").split(","));
        menuService.deleteBatch(ids);
        return Result.ok();
    }

    @GetMapping("queryRolePermission")
    @ApiOperation(value = "查询角色授权")
    public Result<List<String>> queryRolePermission(@RequestParam(name = "roleId") String roleId) {
        List<SysRoleMenu> list = roleMenuService.getListByRoleId(roleId);
        List<String> idList = list.stream().map(item -> item.getMenuId().toString()).collect(Collectors.toList());
        return Result.ok(idList);
    }

}
