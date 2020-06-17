package com.cody.seed.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:13
 */
@Getter
@Setter
@ToString
public class SysLoginLog {

    /**
     * 访问ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录IP地址
     */
    private String ip;

    /**
     * 登录地
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态 0成功 1失败
     */
    private Integer status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    private Date loginTime;

}
