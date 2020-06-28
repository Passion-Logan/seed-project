package com.cody.seed.modules.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SysUserQueryVO
 *
 * @author WQL
 * @Description:
 * @date: 2020/6/28 23:27
 * @since JDK 1.8
 */
@ApiModel
@Data
public class SysUserQueryVO implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String id;

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
     *
     */
    @ApiModelProperty("")
    private String sex;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

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
     *
     */
    @ApiModelProperty("")
    private String remark;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    @Min(value = 1, message = "当前页不小于1")
    private Integer current;
    /**
     * 分页大小
     */
    @ApiModelProperty("分页大小")
    @Min(value = 1, message = "每页条数不能小于1")
    private Integer pageSize;

}
