package com.cody.seed.modules.vo.request;

import com.cody.seed.modules.system.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SysUserRoleRequestVO extends SysUser {

    String roleIds;

}
