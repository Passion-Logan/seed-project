package com.cody.common.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型枚举
 *
 * @author Administrator
 * @date 2021/9/13
 * @lastUpdateUser Administrator
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

    /**
     * 微信
     */
    WECHAT(1, "微信"),

    /**
     * 支付宝
     */
    ALIPAY(2, "支付宝");

    /**
     * 值
     */
    private Integer value;

    /**
     * 名称
     */
    private String name;

}
