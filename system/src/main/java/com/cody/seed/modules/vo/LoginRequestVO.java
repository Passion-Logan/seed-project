package com.cody.seed.modules.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 登录bean
 * @date: 2020年06月17日 18:38
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginRequestVO implements Serializable {

    /**
     * 登录账号
     */
    @ApiModelProperty("登录账号")
    private String username;

    /**
     * 登录密码
     */
    @ApiModelProperty("登录密码")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String imgCode;

    /**
     * 验证码唯一标识
     */
    @ApiModelProperty("uuid")
    private String uuid;

}
