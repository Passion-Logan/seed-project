package com.cody.seed.modules.system.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
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
    private String deptId;
    /**
     * 角色ID
     */
    private String roleId;
}