package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public interface SelectMapper {

    User getUserById(Integer id);

    List<User> getAllUser();

    //查询用户的总数量
    Integer getCount();

    //用ID查询单个用户信息，但是不用pojo接收，而是用map
    Map<String,Object> getUserByIdToMap(Integer id);

    //用ID查询所有用户信息，但是不用pojo接收，而是用map
    //第一种方法用List接收
    List<Map<String,Object>> getAllUserToMapInsertintoList();

    //第二种还是继续用Map。加上这个注解
    @MapKey("id") //把查到的数据放到这个mapkey集合中,@MapKey里面value，就作为外层Map的Key
    //这里用主键作为value就不会出现重复了。
    //其实就是Map套Map。Map<id,Map<String,Object>>
    Map<String,Object> getAllUserToMap();
}
