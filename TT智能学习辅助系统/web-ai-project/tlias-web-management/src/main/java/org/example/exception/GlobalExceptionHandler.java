package org.example.exception;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理类
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("服务器发生异常：{}",e.getMessage());
        return Result.error("服务器发生异常");
    }

    @ExceptionHandler
    public Result handleException(DuplicateKeyException e){
        log.error("程序出错了~",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }

    //处理业务逻辑异常
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.warn("业务逻辑异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
