package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by KingOfTetris
 * @date 2023/5/30
 */

@Controller
public class HelloController {


    //服务器中解析/ 就是http://localhost:8080/SpringMVC/
    @RequestMapping("/")
    public String protal(){
        //将逻辑视图返回
        return "index";
    }


    //保持浏览器的请求和RequstMapping的路径一致就行
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
