package com.cody.seed.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:27
 */
@Getter
@Setter
@ToString
public class SysUser {

    /**
     * 用户ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

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
    private String birthday;

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
    private Integer deptId;

    /**
     * 岗位id
     */
    private Integer jobId;

    /**
     * 创建者
     */

    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

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
