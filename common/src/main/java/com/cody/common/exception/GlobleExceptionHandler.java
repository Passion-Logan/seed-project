package com.cody.common.exception;

import com.cody.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Description: 异常处理器
 * @date: 2020年06月16日 16:53
 */
@RestControllerAdvice
@Slf4j
public class GlobleExceptionHandler {

    @ExceptionHandler(GlobleException.class)
    public Result<?> handleRRException(GlobleException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error("操作失败，"+e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        StringBuffer sb = new StringBuffer();
        sb.append("不支持");
        sb.append(e.getMethod());
        sb.append("请求方法，");
        sb.append("支持以下");
        String [] methods = e.getSupportedMethods();
        if(methods!=null){
            for(String str:methods){
                sb.append(str);
                sb.append("、");
            }
        }
        log.error(sb.toString(), e);
        return Result.error(405,sb.toString());
    }

}
