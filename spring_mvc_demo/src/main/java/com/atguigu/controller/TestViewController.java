package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author by KingOfTetris
 * @date 2023/5/31
 */

@Controller
public class TestViewController {

    @RequestMapping("/test/view/thymeleaf")
    public ModelAndView testThymeleafView(){
        ModelAndView modelAndView = new ModelAndView();

        //当你的viewName没有任何前缀时，就会被配置在spring核心配置文件中的viewResolver解析为相应的视图
        //当然在这里就是ThymeleafView
        //其实它也是一种转发视图，可以被Thymeleaf解析
        modelAndView.setViewName("success");
        return modelAndView;
    }


    @RequestMapping("/test/view/forward")
    public ModelAndView testInternalResourceView(){
        ModelAndView modelAndView = new ModelAndView();
        //下面这句就相当于request.getRequestDispatcher("/test/mav").forward(request,response)
        //纯转发视图
        //两者的区别在于，你通过forward转发是得到的视图，是无法解析Thymeleaf语法的
        //那么表现在页面上就会缺少数据。
        modelAndView.setViewName("forward:/test/mav");
        return modelAndView;
    }

    @RequestMapping("/test/view/redirect")
    public String testRedirectView(){
        //要注意的是重定向跳转资源的地址是需要写项目名的。
        //下面就相当于response.sendRedirect("/SpringMVC/test/mav");
        //也就说明SpringMVC重定向会自动加上工程名
        return "redirect:/test/mav";
    }
}
