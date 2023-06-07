package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    User checkLogin(String username,String password);

    User checkLoginByMap(Map<String,Object> map);


    /**
     * 实际上下面这两种最常用
     * @param user
     */
    void insertUser(User user);

    //显示地把param1和param2改成username和password
    User checkLoginByParam(@Param("username") String username,@Param("password")String password);

    

}
