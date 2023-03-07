package com.tangya.jwt.config;


import com.tangya.jwt.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PermissionHandler {


    /**
     * 自定义全局异常类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?>  authorizationException(Exception e){
        return Result.error(1008,"系统内部错误，请联系管理员",e.getMessage());
    }



}
