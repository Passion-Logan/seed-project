package com.cody.seed.modules.vo.response;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@ApiModel("用户菜单")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserMenuResponseVO implements Serializable {

    @ApiModelProperty("菜单ID")
    private String id;

    @ApiModelProperty("显示名称")
    private String menu;

    @ApiModelProperty("菜单类型")
    private Integer type;

    @ApiModelProperty("菜单名称")
    private String componentName;

    @ApiModelProperty("请求地址")
    private String path;

    @ApiModelProperty("重定向")
    private String redirect;

    @ApiModelProperty("父级id")
    private String pid;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单状态:显示,隐藏")
    private Boolean hideInMenu;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否外链")
    private Boolean isFrame;

    @ApiModelProperty("子菜单")
    private List<SysUserMenuResponseVO> children;

}
