package top.one.handle.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.one.domain.Res;
import top.one.enums.AppHttpCodeEnum;
import top.one.exception.SystemException;

/**
 * @author: XiaoWan
 * @Date: 2022/12/4
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Res systemExceptionHandler(SystemException e){
        log.error("出现了异常{}",e.getMsg());
        return Res.errorResult(AppHttpCodeEnum.REQUIRE_USERNAME,e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    public Res exceptionHandler(Exception e){
        e.printStackTrace();
        log.error("出现了异常{}",e.getMessage());
        return Res.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,e.getMessage());
    }
}
