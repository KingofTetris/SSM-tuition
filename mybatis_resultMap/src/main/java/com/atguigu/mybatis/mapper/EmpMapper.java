package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public interface EmpMapper {

    Emp getEmpByEmpId(Integer empId);

    Emp getEmpAndDeptByEmpId(Integer empId);

    Emp getEmpAndDeptByStep(Integer empId);

    List<Emp> getEmpsByDeptId(Integer deptId);
}
