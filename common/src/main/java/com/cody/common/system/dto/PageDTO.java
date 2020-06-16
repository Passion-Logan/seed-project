package com.cody.common.system.dto;

/**
 * @Description: TODO
 * @date: 2020年06月16日 18:25
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class PageDTO<T> implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 结果集
     */
    private List<T> list;

}
