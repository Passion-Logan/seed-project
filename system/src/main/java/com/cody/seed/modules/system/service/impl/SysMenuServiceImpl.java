package com.cody.seed.modules.system.service.impl;

import com.cody.common.system.service.impl.BaseServiceImpl;
import com.cody.seed.modules.system.entity.SysMenuDTO;
import com.cody.seed.modules.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:45
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDTO> implements ISysMenuService {
    @Override
    public boolean insertMenu(SysMenuDTO menuDTO) {
        return false;
    }

    @Override
    public boolean updateMenu(SysMenuDTO menuDTO) {
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
    public List<SysMenuDTO> findMenuByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<String> getPermissionsByUserId(Integer userId) {
        return null;
    }
}
