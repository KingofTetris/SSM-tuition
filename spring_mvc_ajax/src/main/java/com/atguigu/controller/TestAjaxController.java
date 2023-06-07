package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 *
 * SpringMVC中一个超级实用的@RequestBody注解
 * 1.@RequestBody，将请求体中的内容与形参进行绑定。
 * 2.使用@RequestBody注解将json格式的数据转换为java对象
 *   1.导入jackson的依赖 因为SpringMVC解析json底层调用了jackson
 *   2.在springmvc.xml中配置   <mvc:annotation-driven/>
 *   3.在对应方法的形参位置，直接设置json格式的请求参数要转化为的java类型的形参
 *     加上@RequestBody标识即可
 *
 * 3.还有一个非常实用的注解@ResponseBody
 * 将标识的控制器的方法的返回值作为响应报文的响应体返回给浏览器
 * 4.使用@ResponseBody注解给浏览器返回json格式的数据
 *  先说下原因，因为你后端返回的数据是五花八门的，有User对象，Order对象,Item对象等等
 *  以前的项目你得和前端设定好协议，什么方法返回什么参数。这个沟通过程十分吃力
 *  而对象传给前端，是不能直接response.write的，传过去的是对象的地址
 *  你必须先转化成json字符串传过去。
 *  有了@ResponseBody 从此解决这个问题
 *  1.导入Jackson依赖
 *  2.在springmvc.xml中配置   <mvc:annotation-driven/>
 *  3.将需要转化为json字符串的java对象直接作为控制器方法的返回值，
 *   使用@ResponseBody标识控制器方法。
 *   就可以把java对象转化成字符串传给前端
 *
 *   常用java对象转化为json的结果
 *   实体类--->json对象
 *   map--->json对象
 *   list--->json数组
 */
//@Controller //别TM再忘了，每次创建三层架构的时候一定要把对应的注解写上去！！
//@ResponseBody //用在类上代表该类中的所有方法都被添加了 @ResponseBody注解：
@RestController //这个注解就等于上面两个注解
public class TestAjaxController {
    @RequestMapping("/test/ajax")
    //因为ajax后面的id是用?拼接的，你直接用同名的参数就可以取出来了
    public void test(Integer id,
                     //@RequestBody注解获取请求体参数
                     @RequestBody String requestBody,
                     HttpServletResponse response) throws IOException {
        System.out.println("id:" + id);//id1001
        System.out.println(requestBody); //{"username":"admin","password":"123456"}
        response.getWriter().write("hello,axios");
    }

  /*  @RequestMapping("/test/RequestBody/json")
    //@RequestBody根据名字进行绑定，所以你的实体类属性名和json的key一定要一致。
    //不一致就无法绑定，就是默认空值
    public void test2(HttpServletResponse response, @RequestBody User user) throws IOException {
        System.out.println(user);
        response.getWriter().write("hello,requestBody");
    }*/

    // 如果你没有相应的实体类直接用map接收就行了
    @RequestMapping("/test/RequestBody/json")
    //@RequestBody根据名字进行绑定，所以你的属性名和json的key一定要一致。
    //不一致就无法绑定，就是默认空值
    public void test3(HttpServletResponse response, @RequestBody Map<String,Object> map) throws IOException {
        System.out.println(map);
        response.getWriter().write("hello,requestBody");
    }


    @RequestMapping("/test/ResponseBody")
    @ResponseBody // 可以直接将方法的返回值作为响应报文的响应体返回给浏览器
    //基本可以理解为你return什么 前端就接收什么
    public String testResponseBody(){
        return "success";
    }


    @RequestMapping("/test/ResponseBody/json")
    @ResponseBody // 可以直接将方法的返回值作为响应报文的响应体返回给浏览器
    //基本可以理解为你return什么 前端就接收什么
    //虽然返回的字符串，但你还是要设置为java对象
    public List<User> testResponseBodyJson(){
        User user1 = new User(1001,"admin","asdasd","男",22);
        User user2 = new User(1002,"admin","asdasd","男",22);
        User user3= new User(1003,"admin","asdasd","男",22);
//        Map<String,Object> map = new HashMap<>();
//        map.put("1",user1);
//        map.put("2",user2);
//        map.put("3",user3);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
