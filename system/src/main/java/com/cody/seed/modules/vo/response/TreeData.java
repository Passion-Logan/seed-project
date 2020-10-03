package com.cody.seed.modules.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeData {

    private String key;

    /**
     * 树形节点value
     */
    private String value;

    /**
     * 树形节点名称
     */
    private String title;

    /**
     * 父节点
     */
    private String pid;

    /**
     * 排序
     */
    private Integer sort;

    private List<TreeData> children;

}
