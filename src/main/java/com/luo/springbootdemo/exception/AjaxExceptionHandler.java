package com.luo.springbootdemo.exception;

import com.luo.springbootdemo.util.JSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AjaxExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public JSONResult defaultErrorHandler(HttpServletRequest req,
                                          HttpServletRequest resp, Exception e) throws Exception {


        e.printStackTrace();
        return JSONResult.errorException(e.getMessage());
    }
}
