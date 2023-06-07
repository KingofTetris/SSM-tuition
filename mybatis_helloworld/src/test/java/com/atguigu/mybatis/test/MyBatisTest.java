package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public class MyBatisTest {

    //mybatis实现JDBC的过程
    @Test
    public void test() throws IOException {
        //获取核心配置文件的输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象
        //工厂对象提供的就是Factory前面的那部分，如果有Builder，那需要先把工厂建出来
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //建立工厂
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        //从工厂获取SqlSession对象，这个SqlSession对象是MyBatis提供的数据库操作对象
        //这个b就是自动提交事务autoCommitted
        SqlSession sqlSession = factory.openSession(true);
        //sqlSession的getMapper方法通过动态代理,获取UserMapper的代理实现类对象，
        // 通过代理创建接口的实现类，根据映射文件中的sql重写接口方法
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper的方法，实现功能
        int i = mapper.insertUser();
        System.out.println("结果是:" + i);
        //要注意这里自动开启了事务，但是不会自动结束事务，需要提交
//        sqlSession.commit();
        //会话记得关掉
        sqlSession.close();
    }


    //测试其他语句

    @Test
    public void test2(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser();
        sqlSession.close();
    }


    @Test
    public void test3(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser();
        sqlSession.close();
    }

    @Test
    public void test4(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById();
        System.out.println(userById);
        sqlSession.close();
    }

    @Test
    public void test5(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getAllUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
        //或者方法引用 TODO 不懂就去查一查方法引用是什么
        users.forEach(System.out::println);
        sqlSession.close();
    }
}
