package com.cody.seed.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cody.seed.modules.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(@Param("userId") String userId);

}