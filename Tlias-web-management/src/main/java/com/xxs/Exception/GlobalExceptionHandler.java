package com.xxs.Exception;

import com.xxs.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("全局异常处理器，拦截到异常",e);
        return Result.error("对不起，服务器异常，请稍后重试");
    }    

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String s = message.substring(i);
        String[] arr = s.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}