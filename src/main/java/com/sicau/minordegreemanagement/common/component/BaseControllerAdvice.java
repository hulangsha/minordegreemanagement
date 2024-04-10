package com.sicau.minordegreemanagement.common.component;

import com.sicau.minordegreemanagement.common.result.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result<?> unAuthorize(UnauthorizedException e){
        return new Result<>().fail(CommonCode.UNAUTHORIZED.getCode(), CommonCode.UNAUTHORIZED.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionResponse(Exception e){
        return new Result<>().fail(CommonCode.Fail_SERVER.getCode(), CommonCode.Fail_SERVER.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }


}
