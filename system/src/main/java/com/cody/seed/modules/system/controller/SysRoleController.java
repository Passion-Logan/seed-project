package com.cody.seed.modules.system.controller;

import com.cody.common.api.vo.Result;
import com.cody.seed.modules.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "SysRoleController", tags = "系统-角色管理")
@RequestMapping("/sys/role")
@Validated
@Slf4j
public class SysRoleController {

    @Autowired
    private ISysMenuService menuService;

    @ApiOperation(value = "查询菜单权限树")
    @GetMapping(value = "/queryTreeList")
    public Result queryTreeList() {
        Map<String, Object> resMap = new HashMap<>(1);
        resMap.put("treeList", menuService.queryTreeList());
        return Result.ok(resMap);
    }

}
