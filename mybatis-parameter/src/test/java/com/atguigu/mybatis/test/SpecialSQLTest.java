package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SpecialSQLMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public class SpecialSQLTest {

    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> users = mapper.getUserByLike("a");
        System.out.println(users);
    }
    @Test
    public void testDeleteMoreUsers(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        mapper.deleteMoreUser("7,8");
    }

    @Test
    public void testGetUserList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> tUser = mapper.getUserList("t_user");
        System.out.println(tUser);
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        User user = new User(null,
                "dlhc",
                "dlhc",
                23,
                "男",
                "dlhc@tw.com");
        mapper.insertUser(user);
        System.out.println(user.getId());
    }

    @Test
    public void testJDBC() throws SQLException {

        Connection conn = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssm?serverTimezone=UTC",
                    "root",
                    "root");
            String sql = "select * from t_user where username like %?%";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"a");
            ps.execute(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            conn.close();
        }
    }
}
