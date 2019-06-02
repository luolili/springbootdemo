package com.luo.springbootdemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * handle global exception:page transition
 * ControllerAdvice is just for Controller layer
 */
@ControllerAdvice
public class MyExceptionHandler {

    public static final String VIEW_NAME = "error";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
        e.printStackTrace();
        ModelAndView mv = new ModelAndView();//breakpoint here
        mv.addObject("exception", e);
        mv.addObject("url", req.getRequestURL());
        mv.setViewName(VIEW_NAME);

        return mv;

    }

    // decide if it is an ajax request
    public boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-requested-With").toString());
    }
}
