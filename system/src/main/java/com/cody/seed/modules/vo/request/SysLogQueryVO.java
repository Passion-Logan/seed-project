package com.cody.seed.modules.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@ApiModel
@Data
public class SysLogQueryVO implements Serializable {

    /**
     * 日志内容
     */
    @ApiModelProperty("日志内容")
    private String logContent;

    /**
     * 操作人用户名称
     */
    @ApiModelProperty("操作人用户名称")
    private String username;

    /**
     * 操作类型
     */
    @ApiModelProperty("操作类型")
    private String operateType;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String[] createTime;

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
