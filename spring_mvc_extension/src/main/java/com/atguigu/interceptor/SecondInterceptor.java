package com.atguigu.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 *
 *
 * 拦截器的三个方法
 * preHandle：控制器方法执行之前执行preHandle()，其boolean类型的返回值表示是否拦截或放行，返
 * 回true为放行，即调用控制器方法；返回false表示拦截，即不调用控制器方法
 * postHandle：控制器方法执行之后执行postHandle()
 * afterCompletion：处理完视图和模型数据，渲染视图完毕之后执行afterCompletion()
 */

//没实现接口居然不报错说明了什么？
    //说明这个接口要么没有抽象方法
    //要么都有默认的方法体就是在抽象方法前面加上了default

    //ctrl + o 实现接口
@Component
public class SecondInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在拦截器之前
        System.out.println("SecondInterceptor--->preHandle");
        //返回false，则所有东西都被拦截
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("SecondInterceptor--->postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("SecondInterceptor--->afterCompletion");
    }
}
