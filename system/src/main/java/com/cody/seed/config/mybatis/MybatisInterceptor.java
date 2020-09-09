package com.cody.seed.config.mybatis;

import cn.hutool.core.util.StrUtil;
import com.cody.common.util.oConvertUtils;
import com.cody.seed.modules.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * @Description: mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * @date: 2020年06月16日 17:11
 */
@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.debug("------sqlId------" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.debug("------sqlCommandType------" + sqlCommandType);

        if (parameter == null) {
            return invocation.proceed();
        }
        if (SqlCommandType.INSERT == sqlCommandType) {
//            LoginUser sysUser = this.getLoginUser();
            String username = this.getLoginUser();
            Field[] fields = oConvertUtils.getAllFields(parameter);
            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("createBy".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createBy = field.get(parameter);
                        field.setAccessible(false);
                        if (StringUtils.isBlank(ObjectUtils.toString(local_createBy, ""))) {
                            if (StrUtil.isNotBlank(username)) {
                                // 登录人账号
                                field.setAccessible(true);
                                field.set(parameter, username);
                                field.setAccessible(false);
                            }
                        }
                    }
                    // 注入创建时间
                    if ("createTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createDate = field.get(parameter);
                        field.setAccessible(false);
                        if (StringUtils.isBlank(ObjectUtils.toString(local_createDate, ""))) {
                            field.setAccessible(true);
                            field.set(parameter, new Date());
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
//            LoginUser sysUser = this.getLoginUser();
            String username = this.getLoginUser();
            Field[] fields = null;
            if (parameter instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap<?> p = (MapperMethod.ParamMap<?>) parameter;
                //update-begin-author:scott date:20190729 for:批量更新报错issues/IZA3Q--
                if (p.containsKey("et")) {
                    parameter = p.get("et");
                } else {
                    parameter = p.get("param1");
                }
                //update-end-author:scott date:20190729 for:批量更新报错issues/IZA3Q-

                //update-begin-author:scott date:20190729 for:更新指定字段时报错 issues/#516-
                if (parameter == null) {
                    return invocation.proceed();
                }
                //update-end-author:scott date:20190729 for:更新指定字段时报错 issues/#516-

                fields = oConvertUtils.getAllFields(parameter);
            } else {
                fields = oConvertUtils.getAllFields(parameter);
            }

            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("updateBy".equals(field.getName())) {
                        //获取登录用户信息
                        if (StrUtil.isNotBlank(username)) {
                            // 登录账号
                            field.setAccessible(true);
                            field.set(parameter, username);
                            field.setAccessible(false);
                        }
                    }
                    if ("updateTime".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, new Date());
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
    }

    private String getLoginUser() {
//        LoginUser sysUser = null;
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    SecurityContextHolder.getContext().getAuthentication() != null ?
//                            (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication() : null;
//
//            if (authenticationToken.getDetails() != null) {
//                sysUser = (LoginUser) authenticationToken.getDetails();
//            }
//        } catch (Exception e) {
//            //e.printStackTrace();
//            sysUser = null;
//        }
//        return sysUser;
//        return SecurityUtils.getUsername(SecurityUtils.getAuthentication());
        return SecurityUtils.getUsername();
    }

}
