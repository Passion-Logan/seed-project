package com.cody.seed.modules.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@ApiModel
@Data
public class SysLoginLogQueryVO implements Serializable {

    /**
     * 登录账号
     */
    @ApiModelProperty("登录账号")
    private String loginName;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String[] loginTime;

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
