package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.common.enmus.MenulTypeEnum;
import com.cody.common.exception.GlobleException;
import com.cody.common.util.BeanUtil;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.mapper.SysMenuMapper;
import com.cody.seed.modules.system.service.ISysMenuService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:45
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public boolean insertMenu(SysMenu menuDTO) {
        //校验菜单数据
        checkMenuData(menuDTO);
        return this.save(BeanUtil.convert(menuDTO, SysMenu.class));
    }

    /**
     * 校验菜单新增编辑时 参数
     * 由于前端是动态form表单
     * validation注解+后台控制参数校验
     *
     * @param menuDTO
     */
    private void checkMenuData(SysMenu menuDTO) {

        //一级菜单 pid 默认为0
        if (MenulTypeEnum.ROOT_MENU.getValue().equals(menuDTO.getType())) {
            //一级菜单pid=0
            menuDTO.setPid("0");
            if (StringUtils.isBlank(menuDTO.getRedirect())) {
                throw new GlobleException("跳转路径不能为空");
            }
            if (StringUtils.isBlank(menuDTO.getIcon())) {
                throw new GlobleException("菜单图标不能为空");
            }
        } else {
            if (menuDTO.getPid() == null) {
                throw new GlobleException("上级菜单不能为空");
            }
        }

        //类型为菜单时 请求地址不能为空
        if (!MenulTypeEnum.BUTTON.getValue().equals(menuDTO.getType())) {
            if (StringUtils.isBlank(menuDTO.getPath())) {
                throw new GlobleException("菜单路径不能为空");
            }
            if (StringUtils.isBlank(menuDTO.getComponent())) {
                throw new GlobleException("前端路由不能为空");
            }
            if (StringUtils.isBlank(menuDTO.getComponentName())) {
                throw new GlobleException("路由名称不能为空");
            }
        }
        //按钮时 权限标识不能为空
        if (MenulTypeEnum.BUTTON.getValue().equals(menuDTO.getType())) {
            if (StringUtils.isBlank(menuDTO.getPermission())) {
                throw new GlobleException("权限标识不能为空");
            }
            //判断是否存在相同按钮标识
            int num = sysMenuMapper.checkPermission(menuDTO.getPermission(), menuDTO.getId());
            if (num > 1) {
                logger.error("权限标识重复 permission = " + menuDTO.getPermission());
                throw new GlobleException("权限标识重复");
            }
        }
    }

    /**
     * 编辑菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean updateMenu(SysMenu menu) {
        //校验菜单数据
        checkMenuData(menu);
        return this.updateMenu(menu);
    }

    /**
     * 批量删除菜单
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(List<String> ids) {

        List<String> idList = new ArrayList<>();
        for (String id : ids) {
            //查看是否为子菜单
            List<SysMenu> list = sysMenuMapper.getListByPid(id);
            if (CollectionUtils.isEmpty(list)) {
                //不存在子节点了 则删除
                idList.add(id);
            }
        }
        return this.deleteBatch(idList);
    }

    /**
     * 删除单条
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        //查看是否为子菜单
        List<SysMenu> list = sysMenuMapper.getListByPid(id);
        if (!CollectionUtils.isEmpty(list)) {
            throw new GlobleException("存在子菜单 无法删除");
        }
        //不存在子节点了 则删除
        return this.deleteById(id);
    }

    /**
     * 查询用户菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> findMenuByUserId(String userId) {
        List<SysMenu> list = sysMenuMapper.findMenuByUserId(userId);
        //由于用户对应多个角色 菜单去重
        List<SysMenu> distinct = list.stream().distinct().collect(Collectors.toList());
        return distinct;
    }

    /**
     * 查询用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> getPermissionsByUserId(String userId) {
        return sysMenuMapper.getPermissionsByUserId(userId);
    }
}
