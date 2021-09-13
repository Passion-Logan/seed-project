package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Stringify;
import com.cody.common.entity.SysBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @Description: 部门表
 * @date: 2020年06月17日 15:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDept extends SysBaseModel<SysDept> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父部门id
     */
    @Stringify
    private Long pid;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 部门状态:0正常,1停用
     */
    private Boolean enabled;
    /**
     * 备注
     */
    private String remark;

}