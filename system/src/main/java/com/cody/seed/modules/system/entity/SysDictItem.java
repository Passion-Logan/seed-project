package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Dict;
import com.cody.common.aspect.annotation.Stringify;
import com.cody.common.entity.SysBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 * @Description: TODO
 * @date: 2020年06月17日 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDictItem extends SysBaseModel<SysDict> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    @Stringify
    private Long dictId;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态（1启用 0不启用）
     */
    @Dict(dicCode = "dict_item_status")
    private Integer status;

}
