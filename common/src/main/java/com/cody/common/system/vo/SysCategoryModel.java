package com.cody.common.system.vo;

import lombok.Data;

/**
 * @author Administrator
 * @Description: TODO
 * @date: 2020年06月17日 13:42
 */
@Data
public class SysCategoryModel {

    /**
     * 主键
     */
    private String id;
    /**
     * 父级节点
     */
    private String pid;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型编码
     */
    private String code;

}
