package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public class SelectTest {
    @Test
    public void test1(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        //mapper.xxx方法，实际上是根据你的SQL语句
        //去调用sqlSession的对应方法。
        //然后根据xxx方法的返回类型进行返回。
        //比如下面这句话其实等于 sqlSession.selectOne(sql);
        User userById = mapper.getUserById(2);
        System.out.println(userById);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(System.out::println);
    }
    
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
//        Integer count = mapper.getCount();
//        System.out.println(count);
        //如果没有对应的实体类型，直接用map接收就可以了。
        //mybatis会自动以字段名为key，字段的值为value进行存储
        //因为map的key无序的所以输出才无序，另外字段值为null的字段不会放入map中
        Map<String, Object> userByIdToMap = mapper.getUserByIdToMap(1);
        System.out.println(userByIdToMap);
    }

    @Test
    public void testallUserToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        // //结果你会发现他其实每条记录对应的一张map。
        List<Map<String, Object>> allUserToMapList = mapper.getAllUserToMapInsertintoList();
        allUserToMapList.forEach(System.out::println);
        Map<String, Object> allUserToMap = mapper.getAllUserToMap();
        System.out.println(allUserToMap);
    }
}
