package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.EmployeeMapper;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/6/2
 */
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * <!--配置Mapper接口的扫描,可以把设置包com.atguigu.ssm.mapper下的所有mapper接口
     *         通过上面配置的SqlSession创建代理实现类对象，并且把这些对象交给IoC管理
     *         这样你就可以在任何一个组件自动装配Mapper接口
     *     -->
     * <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     *         <!---->
     *         <property name="basePackage" value="com.atguigu.ssm.mapper"></property>
     *     </bean>
     *  为什么能自动装配Mapper是因为这个配置
     */
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    @Override
    public PageInfo<Employee> getEmployeePage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum,4);
        //查询所有的员工信息
        //没错，这个PageHelper就是查出所有然后进行操作
        List<Employee> allEmployee = employeeMapper.getAllEmployee();
        //获取分页相关数据
        //page两个参数，所有要分页的数据和分页数
        PageInfo<Employee> page = new PageInfo<>(allEmployee,5);
        //PageInfo里面藏了所有的分页信息
        return page;
    }
}
