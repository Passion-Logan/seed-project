package com.cody.common.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求响应 基础类
 *
 * @author wql
 * @desc BasicVo
 * @date 2021/9/14
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/14
 */
@Data
public class BasicVo implements Serializable {

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = 0;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

}
