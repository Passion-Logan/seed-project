package com.cody.seed.modules.vo.response;

import com.cody.common.aspect.annotation.Stringify;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysUserResponseVO
 *
 * @author WQL
 * @Description:
 * @date: 2020/6/28 23:26
 * @since JDK 1.8
 */
@ApiModel
@Data
public class SysUserResponseVO implements Serializable {

    /**
     * 用户ID
     */
    @Stringify
    @ApiModelProperty("用户ID")
    private Long id;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birthday;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 帐号状态:1正常,0禁用
     */
    @ApiModelProperty("帐号状态:1正常,0禁用")
    private Boolean enabled;

    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    private Integer deptId;

    /**
     * 岗位id
     */
    @ApiModelProperty("岗位id")
    private Integer jobId;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 最后登录ip
     */
    @ApiModelProperty("最后登录ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    private Date loginTime;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
