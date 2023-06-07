package com.atguigu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */

@ControllerAdvice
public class ExceptionController {
    //设置要处理的异常
    //value是某种异常的类型
    @ExceptionHandler(ArithmeticException.class)
    public String handleException(Throwable ex, Model model){
        //共享异常
        model.addAttribute("ex",ex);
        return "error";
    }
}
