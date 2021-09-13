package com.cody.seed.modules.system.entity;

import com.cody.common.aspect.annotation.Stringify;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 * @Description: 用户和角色关联表
 * @date: 2020年06月16日 18:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Stringify
    private Long roleId;
    /**
     * 用户ID
     */
    @Stringify
    private Long userId;
}