package com.cody.seed.modules.system.entity;

import com.cody.common.entity.SysBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 * @Description: 角色信息表
 * @date: 2020年06月17日 14:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends SysBaseModel<SysRole> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限字符串
     */
    private String permission;
    /**
     * 角色级别
     */
    private Integer level;
    /**
     * 数据权限
     */
    private String dataScope;
    /**
     * 备注
     */
    private String remark;
}