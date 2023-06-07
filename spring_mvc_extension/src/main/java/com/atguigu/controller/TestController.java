package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */

@Controller
public class TestController {

    @RequestMapping("/test/hello")
    public String test(){
        int a = 1/0;
        return "success";
    }
}
