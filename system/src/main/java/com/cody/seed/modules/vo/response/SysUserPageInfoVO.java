package com.cody.seed.modules.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: SysUserPageInfoVO
 *
 * @author Administrator
 * @Description:
 * @date: 2020/6/28 23:25
 * @since JDK 1.8
 */
@ApiModel
@Data
public class SysUserPageInfoVO implements Serializable {

    /**
     * 当前页
     **/
    @ApiModelProperty("当前页")
    private Integer pageNum;
    /**
     * 每页条数
     **/
    @ApiModelProperty("每页的数量")
    private Integer pageSize;
    /**
     * 总记录数
     **/
    @ApiModelProperty("总记录数")
    private Long total;
    /**
     * 结果集
     **/
    @ApiModelProperty("结果集")
    private List<SysUserResponseVO> list;

}
