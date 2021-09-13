package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Stringify;
import com.cody.common.entity.SysLogicDeleteModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
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
    private Date loginTime;

    /**
     * 备注
     */
    private String remark;

}
