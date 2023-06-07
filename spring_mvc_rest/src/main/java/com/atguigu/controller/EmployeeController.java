package com.atguigu.controller;

import com.atguigu.dao.EmployeeDao;
import com.atguigu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */

@Controller
public class EmployeeController {


    //找不到Bean无非两种情况，没加注解或者没开启扫描
    @Autowired
    private EmployeeDao employeeDao;
//    访问首页 / GET 已经通过配置视图控制器完成了

    //    跳转到添加数据页面 /to/add GET
    // 显然这个也只是为了一个跳转，没有其他功能，可以直接用视图配置器
    // 为什么不直接用超链接？因为超链接无法携带数据

//    查询全部数据 /employee GET
    @GetMapping("/employee")
    public String getAllEmployee(Model model){
        Collection<Employee> allEmployee = employeeDao.getAll();
        model.addAttribute("allEmployee",allEmployee);
        //跳转到列表页面
        return "employee_list";
    }
    //    根据ID跳转到更新员工信息页面 /employee/2 GET
    @GetMapping("/employee/{id}")
    public String toUpdate(@PathVariable("id") Integer id,Model model){
        //跳转到员工信息的更新页面
        Employee employee = employeeDao.get(id);
        //因为要跳转到新的页面，记得将员工信息共享到请求域中
        model.addAttribute("employee",employee);
        return "employee_update";
    }


//    保存员工信息 /employee POST
    @PostMapping("/employee")
    //请求传过来的信息可以直接用实体类接收
    //要保证name和属性名一致
    public String insertEmployee(Employee employee){
        //保存员工信息
        employeeDao.save(employee);
        //添加成功以后要跳回去显示所有员工
        //所以你要重新请求一次@GetMapping("/employee")
        //注意不是直接转会显示页面，显示页面没发送请求就什么都没有
        //重定向到列表功能 就相当于在地址栏输入地址 就是Get请求
        return "redirect:/employee";

        //测试直接返回到显示视图
        //结果确实是什么数据都没有，因为你根本没有发送查询所有用户的请求
        //只是跳转到了页面。
//        return "employee_list";
    }

    //    更新员工信息 /employee PUT
    @PutMapping("/employee")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }

//    根据ID删除员工信息 /employee/2 DELETE
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employee";
    }
}
