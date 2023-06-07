package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public interface DeptMapper {

    /**
     * 根据部门id查询部门信息
     * @param deptId
     * @return
     */
    Dept getDeptByDeptId(Integer deptId);

    /**
     * 查询部门及所属员工信息
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByDeptId(Integer deptId);

}
