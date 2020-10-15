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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "SysMenuController", tags = "系统-菜单管理")
@RequestMapping("/sys/menu")
@Validated
@Slf4j
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleMenuService roleMenuService;

    /**
     * 菜单树形展示
     *
     * @return
     */
    //@Cacheable(value="menuCache")
    @ApiOperation(value = "菜单树形展示")
    @GetMapping("list")
    public Result selectPageList() {
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
    public Result addMenu(@RequestBody @Valid SysMenu menu) {
        menuService.save(menu);
        return Result.ok();
    }

    @ApiOperation(value = "编辑菜单")
    @PutMapping("updateMenu")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result updateMenu(@RequestBody @Valid SysMenu menu) {
        menuService.updateById(menu);
        return Result.ok();
    }

    /**
     * 删除节点菜单
     * 如果父节点存在子节点 则不删除
     *
     * @param object
     * @return
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("removeMenu")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Result removeMenu(@RequestBody JSONObject object) {
        List<String> ids = Arrays.asList(object.getString("ids").split(","));
        menuService.deleteBatch(ids);
        return Result.ok();
    }

    @GetMapping("queryRolePermission")
    @ApiOperation(value = "查询角色授权")
    public Result queryRolePermission(@RequestParam(name = "roleId") String roleId) {
        List<SysRoleMenu> list = roleMenuService.getListByRoleId(roleId);
        List<String> idList = new ArrayList<>();

        list.stream().forEach(item -> idList.add(item.getMenuId()));

        return Result.ok(idList);
    }

}
