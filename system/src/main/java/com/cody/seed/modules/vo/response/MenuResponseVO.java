package com.cody.seed.modules.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@ApiModel("用户菜单")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class MenuResponseVO implements Serializable {

    private String key;
    private String name;
    private String path;
    private String redirect;
    private String icon;
    private String target;
    private Boolean hideInMenu;
    private Integer sort;
    private Boolean isFrame;
    private List<MenuResponseVO> children;

}
