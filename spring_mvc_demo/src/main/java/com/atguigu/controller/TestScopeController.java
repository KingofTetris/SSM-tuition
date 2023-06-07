package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @author by KingOfTetris
 * @date 2023/5/31
 */

@Controller
public class TestScopeController {

    /**
     * 向请求域Request共享数据最常用推荐的方法为ModelAndView.
     * @return
     */
    @RequestMapping("/test/mav")
    public ModelAndView testMAV(){
        /**
         * 你要用mav 那就一定得返回mav 不然没有任何效果
         * ModelAndView包含Model和View的功能
         * Model向请求域共享数
         * View设置逻辑视图实现页面跳转
         * 剩下的什么Model,ModelMap,Map都是一样的，
         * 底层都是通过BindingAwareModelMap进行创建的,最后都会封装成ModelAndView
         */
        ModelAndView mav = new ModelAndView();
        //添加共享数据给testRequestScope
        mav.addObject("testRequestScope","hello,ModelAndView");
        //设置逻辑视图名称
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/test/session")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope","hello,session");
        return "success";
    }
    @RequestMapping("/test/application")
    //不能直接用ServletContext代表Application
    public String testApplication(HttpSession session){
        //Application对象可以通过其他的域对象获取Request,Session都行
        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope","hello,application");
        return "success";
    }
}
