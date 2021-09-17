package com.cody.seed.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cody.common.aspect.annotation.Stringify;
import com.cody.common.entity.SysLogicDeleteModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @Description: 系统用户表
 * @date: 2020年06月16日 18:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends SysLogicDeleteModel<SysUser> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    @NotBlank(message = "登录账号为空")
    private String userName;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 用户邮箱
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    /**
     * 手机号码
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String phone;

    /**
     * 生日
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 帐号状态:1正常,0禁用
     */
    private Boolean enabled;

    /**
     * 部门id
     */
    @Stringify
    private Long deptId;

    /**
     * 岗位id
     */
    @Stringify
    private Long jobId;

    /**
     * 最后登录ip
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loginTime;

    /**
     * 备注
     */
    private String remark;

}
