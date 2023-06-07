package com.atguigu.dao;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.atguigu.pojo.Employee;
import org.springframework.stereotype.Repository;
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    /**
     * 静态代码块只会执行一次。执行顺序去复习Java基础
     */
    static{
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1));
        employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1));
        employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0));
        employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0));
        employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1));
    }
    private static Integer initId = 1006;
    //添加和修改
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }
    public Collection<Employee> getAll(){
        return employees.values();
    }
    public Employee get(Integer id){
        return employees.get(id);
    }
    public void delete(Integer id){
        //删除key就把整个对象删除了
        employees.remove(id);
    }
}