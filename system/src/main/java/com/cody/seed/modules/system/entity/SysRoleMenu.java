package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Stringify;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 角色和菜单关联表
 * @date: 2020年06月17日 14:57
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Stringify
    private Long menuId;
    /**
     * 角色ID
     */
    @Stringify
    private Long roleId;
}