package com.cody.seed.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cody.seed.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 更新用户部门id
     *
     * @param deptId
     * @param list
     * @return
     */
    int updateDeptIdByUserIds(@Param("deptId") String deptId,
                              @Param("list") List<String> list);


    /**
     * 解冻 冻结
     *
     * @param status
     * @param list
     * @return
     */
    int frozenBatch(@Param("status") boolean status,
                    @Param("list") List<String> list);

    /**
     * 查询用户信息
     *
     * @param userName
     * @return
     */
    SysUser findByName(@Param("userName") String userName);

}
