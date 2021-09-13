package com.cody.common.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 实体公共类-逻辑删除
 *
 * @author wql
 * @desc SysLogicDeleteModel
 * @date 2021/9/13
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysLogicDeleteModel<T extends Model<T>> extends SysBaseModel<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "1")
    private Boolean deleted;

}
