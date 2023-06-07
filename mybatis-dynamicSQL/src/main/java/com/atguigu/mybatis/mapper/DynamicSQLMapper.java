package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/30
 */

/**
 * Mybatis框架的动态SQL技术是一种根据特定条件动态拼装SQL语句的功能，它存在的意义是为了
 * 解决 拼接SQL语句字符串时的痛点问题。
 *
 * 比如用户选择查询的条件不确定的时候，你就需要用到SQL拼接
 */
public interface DynamicSQLMapper {


    /**
     * 根据条件查询员工信息
     */
    List<Emp> getEmpByCondition(Emp emp);


    /**
     * 根据Choose查询员工信息
     */
    List<Emp> getEmpByChoose(Emp emp);

    /**
     * 批量添加员工信息
     * @param emps
     */
    void insertMoreEmp(@Param("emps") List<Emp> emps);


    /**
     * 批量删除员工信息
     * @param empIds
     */
    void deleteMoreEmpByIds(@Param("empIds") Integer[] empIds);
}
