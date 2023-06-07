package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by KingOfTetris
 * @date 2023/5/31
 */

//要注意的是如果你加上了SpringMVC，那么@Controller，@Service,@Repository就不能串用了，而是严格对应三层架构
@Controller //在写其他东西之前，别忘了先在IoC里面注入对象。对象都没有什么操作都没用。
public class ProtalController {

//    @RequestMapping("/")
//    public String protal(){
//        return "index";
//    }
}
