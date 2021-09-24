package com.cody.seed.modules.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel
@Data
public class SysUserPwdVO implements Serializable {

    /**
     * 旧密码
     */
    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPwd;

    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPwd;

}
