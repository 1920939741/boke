package com.gyg.common.exception;

import com.gyg.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理工具类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)     //判断返回消息是否正常
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常---------->>>" + e);
        return Result.fail(e.getMessage());
    }

    /**
     * shiro运行异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)     //判断返回消息是否正常,没有权限异常
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e){
        log.error("shiro异常---------->>>" + e);
        return Result.fail(401,e.getMessage(),null);
    }

    /**
     * 实体校验异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)     //判断返回消息是否正常,没有权限异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体检验异常异常---------->>>" + e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    /**
     * 处理断言异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)     //判断返回消息是否正常,没有权限异常
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("断言异常异常---------->>>" + e);
        return Result.fail(e.getMessage());
    }

}
