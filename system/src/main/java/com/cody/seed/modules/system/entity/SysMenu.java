package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Stringify;
import com.cody.common.entity.SysBaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Administrator
 * @Description: 菜单表
 * @date: 2020年06月16日 18:12
 */
@Data
@Accessors(chain = true)
public class SysMenu extends SysBaseModel<SysMenu> implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Stringify
    @ApiModelProperty("父级id")
    private Long pid;

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
