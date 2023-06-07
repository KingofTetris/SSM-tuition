package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/28
 */
public interface UserMapper {

    /**
     * 添加用户信息
     * @return
     */
    int insertUser();

    /**
     * 修改用户信息
     * @return
     */
    void updateUser();

    /**
     * 删除用户信息
     */
    void deleteUser();

    /**
     * 根据ID找一个用户
     * @return
     */
    User getUserById();

    /**
     * 查所有用户
     * @return
     */
    List<User> getAllUser();
}
