package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Dict;
import com.cody.common.entity.SysBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 * @Description: 系统操作日志表
 * @date: 2020年06月17日 14:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog extends SysBaseModel<SysLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求路径
     */
    private String requestUrl;
    /**
     * 请求方法
     */
    private String method;

    /**
     * 操作人用户名称
     */
    private String username;
    /**
     * 操作人用户账户
     */
    private String userId;
    /**
     * 操作详细日志
     */
    private String logContent;

    /**
     * 日志类型（1登录日志，2操作日志）
     */
    @Dict(dicCode = "log_type")
    private Integer logType;

    /**
     * 操作类型（1查询，2添加，3修改，4删除,5导入，6导出）
     */
    @Dict(dicCode = "operate_type")
    private Integer operateType;

}
