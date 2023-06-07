package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author by KingOfTetris
 * @date 2023/5/30
 */
public class CacheMapperTest {
    @Test
    public void testLevelOneCache(){
        SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        /**
         * 一级缓存是SqlSession级别的，也是默认的缓存级别 不需要任何的设置
         * 通过同一个SqlSession查询的数据会被缓存，
         * 下次查询相同的数据，就会从缓存中直接获取，不会从数据库重新访问
         */
        Emp emp1 = mapper1.getEmpById(1);
        System.out.println(emp1);

        /**
         * 验证增删改改变了数据库的数据，当然也有可能刚好影响缓存的数据。
         * 因此增删改会击穿一级和二级缓存。
         */
//        Emp emp = new Emp(null,"三十年",33,"男");
//        mapper1.insertEmp(emp);

        //手动清空缓存，为什么要手动清空缓存
        //原因可能在于缓存过多，缓存的过期时常又比较久。
        //这个时候就需要手动清除老旧的缓存，存入新的缓存
        //那里为什么不直接把过期时限弄短点？
        //因为我们可能还需要针对某一个会话的cache进行清除，所以才
        //需要手动清空缓存,只能清空一级缓存
//        sqlSession1.clearCache();

        Emp emp2 = mapper1.getEmpById(1);
        System.out.println(emp2);

       /** 使一级缓存失效的四种情况：
        1) 不同的SqlSession对应不同的一级缓存
        2) 同一个SqlSession但是查询条件不同
        3) 同一个SqlSession两次查询期间执行了任何一次增删改操作
        4) 同一个SqlSession两次查询期间手动清空了缓存
        */
        SqlSession sqlSession2 = SqlSessionUtil.getSqlSession();
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp3= mapper2.getEmpById(1);
        System.out.println(emp3);
    }

    @Test
    public void testLevelTwoCache(){
        /**
         * 二级缓存是SqlSessionFactory级别，通过同一个SqlSessionFactory创建的SqlSession查询的结果会被
         * 缓存；此后若再次执行相同的查询语句，结果就会从缓存中获取
         */
//        二级缓存是SqlSessionFactory级别，所以你就不能用工具类每次用多个Facotry来创建了
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
            //保证两个sqlSession会话级别是由同一个facotry获取的 才能验证二级缓存
            SqlSession sqlSession1 = sqlSessionFactory.openSession();
            SqlSession sqlSession2 = sqlSessionFactory.openSession();
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);

            Emp emp1 = mapper1.getEmpById(3);
            System.out.println(emp1);
            //二级缓存必须在SqlSession关闭或提交之后有效
            //因为SqlSession在关闭或者提交后，一级缓存才会放入二级缓存之中。
            sqlSession1.close();
            //Cause: java.io.NotSerializableException: com.atguigu.mybatis.pojo.Emp
            //查询的数据所转换的实体类类型必须实现序列化的接口
            //其实想一想就是废话，肯定要实现序列化接口才能保存emp1的状态。
            //不然会话一关闭，jvm就把无用的emp1给回收了，还怎么存储emp1?
            //这个时候就需要实现序列化接口把emp1的状态给存起来，放到二级缓存里面
            //所以Emp要实现序列化接口
            //所以二级缓存命中的时候，其实是直接进行反序列化，而不是真的去查询。
            Emp emp2 = mapper2.getEmpById(3);
            System.out.println(emp2);
            /**
             * Cache Hit Ratio [com.atguigu.mybatis.mapper.CacheMapper]: 0.5 (LoggingCache.java:60)
             * 缓存命中率。
             * 二级缓存的建立意义就在于，如果数据命中，不用每次都去建立新的数据库连接。
             * 减轻数据库压力。
             */
            //现在二级缓存终于有了数据，下面的结果是false也证明了，确实不在同一个内存中，而是分了一二级缓存。
            //所以二级缓存开启的条件满J2多的
            //二级缓存开启的条件：
            //a>在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置
            //b>在映射文件中设置标签<cache/>
            //c>二级缓存必须在SqlSession关闭或提交之后有效
            //d>查询的数据所转换的实体类类型必须实现序列化的接口
            //使二级缓存失效的情况：
            //两次查询之间执行了任意的增删改，会使一级和二级缓存同时失效
            System.out.println(emp1 == emp2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * 先查询二级缓存，因为二级缓存中可能会有其他程序已经查出来的数据，可以拿来直接使用。
     * 如果二级缓存没有命中，再查询一级缓存
     * 如果一级缓存也没有命中，则查询数据库
     * SqlSession关闭之后，一级缓存中的数据会写入二级缓存
     *
     * 其实很简单的道理，二级缓存会存储所有提交或者关闭的一级缓存。
     * 它自然是大的，我们先从大的入手去查，但二级往往不会是最新的缓存
     * 如果没有。我们再去查一级，找最新的
     * 想想怎么测试
     *  先访问二级再访问一级
     */
    @Test
    public void testVisitedSequence(){

    }
}
