package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author by KingOfTetris
 * @date 2023/5/31
 *
 * 查询所有的用户信息--->/user ---get
 * 根据id查询用户信息--->/user/1 ---get
 * 增加用户信息--->/user --- post
 * 修改用户信息--->/user --- put
 * 根据ID删除用户信息--->/user/1 --- delete
 *
 * 注意目前浏览器只能发送get和post请求，
 * 在web.xml里面配置HiddenHttpMethodFilter开启隐藏请求方式PUT DELETE
 * 而且表单必须指定为post
 * 然后
 *  <!--开启隐藏方式PUT name一定要写成_method-->
 *     <input type="hidden" name="_method" value="put">
 */
@Controller
public class TestRestController {


    //Rest风格就必须要设置请求方式
//    @RequestMapping(value = "/user/",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getAllUser(){
        System.out.println("查询所有的用户信息--->/user ---get");
        return "success";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") String id){
        System.out.println("根据id查询用户信息--->/user/" + id + "---get");
        return "success";
    }

    @PostMapping("/user")
    public String insertUser(String username,String password){
        System.out.println("增加用户信息--->/user --- post");
        System.out.println("用户名:"+ username);
        System.out.println("密码:" + password);
        return "success";
    }

    @PutMapping("/user")
    public String updateUser(String username,String password){
        System.out.println("修改用户信息--->/user --- put");
        System.out.println("用户名:"+ username);
        System.out.println("密码:" + password);
        return "success";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable("id") String id){
        System.out.println("根据ID" + id  + "删除用户信息--->/user/" + id  + "--- delete");
        return "success";
    }
}
