package com.cody.seed.modules.system.aspect;

import com.cody.common.aspect.annotation.NoRepeatSubmit;
import com.cody.seed.modules.system.execption.CustomExecption;
import com.google.common.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 防止重复提交切面
 * @date: 2020年06月17日 18:35
 */
@Aspect
public class NoRepeatSubmitAdvice {

    private Logger logger = LoggerFactory.getLogger(NoRepeatSubmitAdvice.class);

    @Autowired
    private Cache<String, Integer> cache;

    @Pointcut("@annotation(nrs)")
    public void noRepeatSubmit(NoRepeatSubmit nrs) {

    }

    @Around("noRepeatSubmit(nrs)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        HttpServletRequest request = attributes.getRequest();
        String key = sessionId + "-" + request.getServletPath();
        // 如果缓存中有这个url视为重复提交
        if (cache.getIfPresent(key) == null) {
            Object o = pjp.proceed();
            cache.put(key, 0);
            return o;
        } else {
            logger.info(request.getServletPath() + "重复提交");
            throw new CustomExecption("请勿重复操作");
        }

    }

}
