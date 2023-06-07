package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author by KingOfTetris
 * @date 2023/5/31
 */

/**
 * 获取请求中的请求参数
 * 1.原生ServletAPI
 * 2.@RequestMapping 如果请求参数名和形参名一致，则自动绑定
 *      如果不一致 就需要用@RequestParam 指定绑定
 * 3.@RuquestHeader:将请求头和控制器方法的形参绑定
 * 4.@CookieValue:将cookie数据和控制方法的形参绑定
 * 三个注解的用法是一模一样的
 */
@Controller
public class TestParamController {
    @RequestMapping("/param/servletAPI")
    //这里的HttpServletRequest对应的request
    //其实是给DispatcherServlet用的，因为TestParamController只是个普通JAVA类
    //没有实现任何Servlet接口，就调用不了getParameters方法
    //但是DispatcherServlet实现了Servlet,DispatcherServlet会根据映射调用方法。
    //而DispatcherServlet能够真正得到相应的request请求,
    // 所以他调用的方法会把真实的request赋值给我们这里命名的形参
    public String getParamByServletAPI(HttpServletRequest request){
        //设置编码之前不能设置任何的请求参数，否则你的设置完全没有效果
//        request.setCharacterEncoding();
        //而你的request是DispatcherServlet给你的，它早就已经获取了请求参数
        //所以你再设置也无济于事。
        //对于GET类型的请求乱码，tomcat已经自行解决了，但是POST还是得自己设置。
        //具体设置在web.xml中对Spring的<filter>进行配置。

        //手动创建Cookie，通过获取session， 让session在响应报文里创建cookie
        //从此以后只要cookie不消息，你的报文里面都会携带cookie
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        return "success";
    }


    //既然SpringMVC封装了Servlet，当然不会再像上面用原生的获取参数的方法
    //你在方法里面设置和请求参数同名的形参，那么默认就会把请求参数赋值给形参。
    //注意要一定要同名。
    //另外还有一点，当你在服务器什么都没有提交，在服务器会被解析成什么？
    //如果是文本则被解析成空字符串，如果是选框则是null

    //@RequestParam 一般是用不到的，毕竟前端的请求参数名字，后端肯定都是知道的。
    //设置为同名就行了，没有必要专门指定，
    //默认requird为true,如果没有设置对应参数,或者没有默认值
    //则报错HTTP状态 400 - 错误的请求
    //类型 状态报告
    //消息 Required String parameter 'username' is not present
    //如果是false,那不传就设置为默认值。
    @RequestMapping("/param")
    public String getParamBy(@RequestParam(value = "username",
            required = false,
            defaultValue = "hello") String username,
                             @RequestParam("password") String password,
                             //请求头都是键值对的形式，key不区分大小写。
                             @RequestHeader(value = "referer",required = false) String referer,
                             //取Cookie的方法就是从key JSESSIONID 获取
                             //  JSESSIONID=288A1264B36E52DCB369F02125148FF0
                             @CookieValue(value = "JSESSIONID",required = false) String cookie) throws UnsupportedEncodingException {
        //中文转码
        String username_utf8 = new String(username.getBytes("iso-8859-1"),"utf-8");
        System.out.println("username:" + username_utf8);
        System.out.println("password:" + password);
        System.out.println("referer:" + referer);
        System.out.println("cookie:" + cookie);
        return "success";
    }


    @RequestMapping("/param/pojo")
    //保证实体类的属性名和请求参数名一致，就可以自动绑定，就不用写那么多形参了
    //所以这个方法就是处理如果你的形参特别多的时候，就写个实体类去接收。
    public String getParamByPojo(User user){
        System.out.println(user);
        return "success";
    }

}
