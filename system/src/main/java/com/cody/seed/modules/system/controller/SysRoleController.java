package com.cody.seed.modules.system.controller;

import com.cody.common.api.vo.Result;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.vo.response.SysUserMenuResponseVO;
import com.cody.seed.modules.vo.response.TreeData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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
        List<TreeData> treeList = new ArrayList<>();
        List<SysUserMenuResponseVO> list = menuService.getList();
//        List<SysUserMenuResponseVO> directory = list.stream().filter(item -> item.getType() == 1).collect(Collectors.toList());

        for (SysUserMenuResponseVO vo : list) {
            treeList.add(TreeData.builder()
                    .pid(vo.getPid())
                    .key(vo.getId())
                    .sort(vo.getSort())
                    .title(vo.getMenu())
                    .value(vo.getId())
                    .build());
        }

        //全部树节点数据
        resMap.put("treeList", buildMenuTree(treeList));
        return Result.ok(resMap);
    }

    private List<TreeData> buildMenuTree(List<TreeData> list) {
        Map<String, List<TreeData>> map = list.stream()
                .sorted(Comparator.comparing(TreeData::getSort))
                .collect(Collectors.groupingBy(TreeData::getPid));

        List<TreeData> directory = map.get("0");

        directory.forEach(menu -> {
            List<TreeData> children = map.get(menu.getValue());
            if (children != null) {
                menu.setChildren(children);
            } else {
                menu.setChildren(new ArrayList<>(0));
            }
        });

        return directory;
    }

}
