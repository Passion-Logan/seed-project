package com.cody.seed.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.vo.request.SysUserQueryVO;
import com.cody.seed.modules.vo.response.SysUserResponseVO;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 更新用户部门id
     *
     * @param deptId deptId
     * @param list   list
     * @return int
     */
    int updateDeptIdByUserIds(@Param("deptId") String deptId,
                              @Param("list") List<String> list);


    /**
     * 解冻 冻结
     *
     * @param status status
     * @param list   list
     * @return int
     */
    int frozenBatch(@Param("status") boolean status,
                    @Param("list") List<String> list);

    /**
     * 查询用户信息
     *
     * @param userName userName
     * @return SysUser
     */
    SysUser findByName(@Param("userName") String userName);

    IPage<SysUserResponseVO> getList(Page<SysUserResponseVO> page, SysUserQueryVO sysUserQueryVO);

}
