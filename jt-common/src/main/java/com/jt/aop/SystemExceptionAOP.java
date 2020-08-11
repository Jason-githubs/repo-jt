package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   //定义异常处理的通知.    只拦截Controller层抛出的异常,并且返回值JSON串
public class SystemExceptionAOP {

    @ExceptionHandler(RuntimeException.class)
    public Object fail(Exception e){
        //打印异常信息
        e.printStackTrace();
        return SysResult.fail();
    }
}
