package com.cody.seed.modules.vo.request;

import com.cody.common.aspect.annotation.Stringify;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SysUserRoleRequestVO {

    @Stringify
    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private String email;

    private Integer sex;

    private Boolean enabled;

    private String roleIds;

}
