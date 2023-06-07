package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/30
 */
public class MBGTest {

    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        /**
         * 根据ID查数据
         */
        /*Emp emp = mapper.selectByPrimaryKey(1);
        System.out.println(emp);*/
        /**
         * 查询所有数据
         */
       /* List<Emp> emps = mapper.selectByExample(null);//没有条件就是查询所有
        emps.forEach(System.out::println);*/

        /**
         * 根据条件查询数据
         * example使用的是QBC风格编码
         */
       /* EmpExample example = new EmpExample();
        example.createCriteria()
                .andEmpNameLike("%1%")  //Like要自己加上%% %在sql里面就表示任意字符
                .andAgeEqualTo(29);
        //上面设置都是and，下面改为or
        //WHERE ( emp_name like ? and age = ? ) or( gender = ? )
        example.or().andGenderEqualTo("男");
        List<Emp> emps = mapper.selectByExample(example);
        emps.forEach(System.out::println);*/

        //测试普通修改功能 修改用户1
        Emp record = new Emp(1,"小黑",null,"女");
//        mapper.updateByPrimaryKey(record);
        //选择性修改，不会更改实体属性为null对应的字段
        //update t_emp SET emp_name = ?, gender = ? where emp_id = ?
        mapper.updateByPrimaryKeySelective(record);
    }

}
