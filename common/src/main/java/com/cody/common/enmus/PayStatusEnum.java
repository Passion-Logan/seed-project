package com.cody.common.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    UNIFIED_ORDER(0,"下单"),

    PAYING(1,"支付中"),

    FAILURE(2,"支付失败"),

    SUCCESS(3,"支付成功");

    /**
     * 值
     */
    private Integer value;

    /**
     * 名称
     */
    private String name;

    /**
     * 根据value 查询
     * @param value
     * @return
     */
    public static String getNameByValue(Integer value) {

        PayStatusEnum[] payStatusEnums = PayStatusEnum.values();
        for (PayStatusEnum statusEnum : payStatusEnums) {
            if (statusEnum.getValue().equals(value)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

}
