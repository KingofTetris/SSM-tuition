package com.atguigu.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public class SqlSessionUtil {

    public static SqlSession getSqlSession(){
        //获取核心配置文件的输入流
        InputStream resourceAsStream = null;
        SqlSession sqlSession = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获取SqlSessionFactoryBuilder对象
            //工厂对象提供的就是Factory前面的那部分，如果有Builder，那需要先把工厂建出来
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //建立工厂
            SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
            //从工厂获取SqlSession对象，这个SqlSession对象是MyBatis提供的数据库操作对象
            //这个b就是自动提交事务autoCommitted
            sqlSession = factory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
