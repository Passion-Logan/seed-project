package com.cody.seed.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cody.seed.modules.system.entity.SysRole;
import com.cody.seed.modules.vo.request.SysRoleQueryVO;
import com.cody.seed.modules.vo.response.SysRoleResponseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色
     *
     * @param userId userId
     * @return SysRole
     */
    List<SysRole> getRolesByUserId(@Param("userId") Long userId);

    /**
     * @param page  page
     * @param query query
     * @return SysRoleResponseVO
     */
    IPage<SysRoleResponseVO> getList(Page<SysRoleResponseVO> page, SysRoleQueryVO query);

}