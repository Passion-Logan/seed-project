package com.cody.seed.modules.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 用户和角色关联表
 * @date: 2020年06月16日 18:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 用户ID
     */
    private Integer userId;
}