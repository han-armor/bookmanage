package com.youo.bookmanage.handler;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.youo.bookmanage.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        return Result.error()
                .message(e.getLocalizedMessage());
    }

    /**
     * 处理业务异常,我们自己定义的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        e.printStackTrace();
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode())
                .message(e.getErrMsg());
    }

    @ExceptionHandler(InterceptorException.class)
    @ResponseBody
    public Result error(InterceptorException e){
        e.printStackTrace();
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode())
                .message(e.getErrMsg());
    }

    @ExceptionHandler(SignatureVerificationException.class)
    @ResponseBody
    public Result error(SignatureVerificationException e){
        e.printStackTrace();
        return Result.error()
                .message("无效签名");
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public Result error(TokenExpiredException e){

        return Result.error()
                .message("Token过期");
    }

    @ExceptionHandler(AlgorithmMismatchException.class)
    @ResponseBody
    public Result error(AlgorithmMismatchException e){

        return Result.error()
                .message("Token算法不一致");
    }

}
