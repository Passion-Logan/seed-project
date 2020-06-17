package com.cody.common.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: TODO
 * @date: 2020年06月17日 15:10
 */
@Data
@ApiModel(value = "分页查询参数")
public class BaseQuery {

    @ApiModelProperty(value = "页数", required = true)
    public Integer page;
    @ApiModelProperty(value = "每页数量", required = true)
    public Integer size;

    public BaseQuery() {
        this.page = 1;
        this.size = 15;
    }

}
