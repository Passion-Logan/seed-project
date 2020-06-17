package com.cody.common.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型枚举
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

    WECHAT(1, "微信"),

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
