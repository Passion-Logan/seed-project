package com.cody.seed.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: 菜单表
 * @date: 2020年06月16日 18:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单ID")
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty("菜单名称")
    private String menu;

    @ApiModelProperty("是否外链")
    private Boolean isFrame;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("组件名称")
    private String componentName;

    @ApiModelProperty("请求地址")
    private String path;

    @ApiModelProperty("重定向")
    private String redirect;

    @ApiModelProperty("父级id")
    private String pid;

    @ApiModelProperty("类型:1目录,2菜单,3按钮")
    private Integer type;

    @ApiModelProperty("隐藏")
    private Boolean visible;

    @ApiModelProperty("权限标识")
    private String permission;

    @ApiModelProperty("缓存")
    private Boolean cache;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;

    private String createBy;

    private Date createTime;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SysMenu menuDO = (SysMenu) o;
        return menu.equals(menuDO.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenu());

    }

}
