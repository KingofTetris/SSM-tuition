package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * @author by KingOfTetris
 * @date 2023/5/30
 */
public interface CacheMapper {

    Emp getEmpById(@Param("empId") Integer empId);

    void insertEmp(Emp emp);
}
