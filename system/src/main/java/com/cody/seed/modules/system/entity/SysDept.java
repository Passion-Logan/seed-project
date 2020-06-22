package com.cody.seed.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @Description: 部门表
 * @date: 2020年06月17日 15:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDept {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 父部门id
     */
    private String pid;
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
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
}