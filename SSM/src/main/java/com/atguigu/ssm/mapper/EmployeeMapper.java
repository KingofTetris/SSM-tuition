package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/6/2
 */
//mybatis是通过接口动态代理实现实现类，不用你手动创建
public interface EmployeeMapper {
    List<Employee> getAllEmployee();
}
