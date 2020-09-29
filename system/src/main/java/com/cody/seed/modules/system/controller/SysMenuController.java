package com.cody.seed.modules.system.controller;

import com.cody.common.api.vo.Result;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.system.service.ISysRoleMenuService;
import com.cody.seed.modules.util.TreeUtil;
import com.cody.seed.modules.vo.response.SysUserMenuResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<SysUserMenuResponseVO> data = new ArrayList<>();
        List<SysUserMenuResponseVO> voList = menuService.getList();

        if (voList.size() > 0) {
            data = TreeUtil.buildMenuTree(voList);
        }

        return Result.ok(data, data.size());
    }
}
