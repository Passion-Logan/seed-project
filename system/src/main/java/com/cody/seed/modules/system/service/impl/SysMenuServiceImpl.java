package com.cody.seed.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cody.seed.modules.system.entity.SysMenu;
import com.cody.seed.modules.system.mapper.SysMenuMapper;
import com.cody.seed.modules.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:45
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public boolean insertMenu(SysMenu menu) {
        return false;
    }

    @Override
    public boolean updateMenu(SysMenu menu) {
        return false;
    }

    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public List<SysMenu> findMenuByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<String> getPermissionsByUserId(Integer userId) {
        return null;
    }
}
