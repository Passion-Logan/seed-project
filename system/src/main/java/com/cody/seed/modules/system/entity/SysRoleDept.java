package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Stringify;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 * @Description: 角色和部门关联表
 * @date: 2020年06月17日 14:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @Stringify
    private Long deptId;
    /**
     * 角色ID
     */
    @Stringify
    private Long roleId;
}