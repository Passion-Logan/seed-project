package com.cody.seed.modules.security;

import com.cody.seed.modules.system.entity.SysUserDTO;
import com.cody.seed.modules.system.service.ISysMenuService;
import com.cody.seed.modules.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 用户登录认证信息查询
 * 一般而言，定制 UserDetailsService 就可以满足大部分需求了，在 UserDetailsService 满足不了
 * 我们的需求的时候考虑定制 AuthenticationProvider。
 * 如果直接定制UserDetailsService ，而不自定义 AuthenticationProvider，可以直接在配置文件 WebSecurityConfig 中这样配置。
 *
 * @Override
 * public void configure(AuthenticationManagerBuilder auth) throws Exception {
 *     // 指定自定义的获取信息获取服务
 *     auth.userDetailsService(userDetailsService)
 * }
 * @date: 2020年06月16日 17:59
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUserDTO user = sysUserService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        List<String> permissions = sysMenuService.getPermissionsByUserId(user.getId());
        log.info("用户权限标识 permissions = {}",permissions);
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(username, user.getPassword(),user.getEnabled(), grantedAuthorities);
    }
}
