package com.cody.seed.config;

import cn.hutool.core.util.StrUtil;
import com.cody.seed.modules.system.entity.SysUser;
import com.cody.seed.modules.system.service.ISysUserService;
import com.cody.seed.modules.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: DefaultAuthenticationInterceptor
 *
 * @author Administrator
 * @Description:
 * @date: 2020/9/18 22:45
 * @since JDK 1.8
 */
public class DefaultAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ISysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return getUser(request, handler);
    }

    private boolean getUser(HttpServletRequest request, Object handler) {
        String token = JwtTokenUtils.getToken(request);

        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        if (request.getRequestURI().contains("swagger") || request.getRequestURI().contains("error")) {
            return true;
        }
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (request.getRequestURI().contains("captcha") || request.getRequestURI().contains("login")) {
            return true;
        }

        if (StrUtil.isNotEmpty(token)) {
            String username = JwtTokenUtils.getUsernameFromToken(token);
            SysUser user = userService.findByUsername(username, null);

            request.setAttribute("token", token);
            request.setAttribute("username", username);
            request.setAttribute("nickName", user.getNickName());
            request.setAttribute("userId", user.getId());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
