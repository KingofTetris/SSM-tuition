package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public interface SpecialSQLMapper {

    /**
     * 模糊查询
     * @param mohu
     * @return
     */
    List<User> getUserByLike(@Param("mohu") String mohu);

    /**
     * 批量删除
     */
    void deleteMoreUser(@Param("ids") String ids);

    /**
     * 动态表名
     * @param tableName
     * @return
     */
    List<User> getUserList(@Param("tableName")String tableName);

    /**
     * 添加用户信息，获取自增主键
     * @param user
     */
    void insertUser(User user);
}
