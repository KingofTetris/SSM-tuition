package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/5/30
 */
public class PageTest {
    
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        //查询功能之前开启分页功能
        //每页展示4条，展示第2页
        //一个小知识 limit 4表示的就是limit(0,4)
        Page<Object> page = PageHelper.startPage(5, 4);
        List<Emp> list = mapper.selectByExample(null);//查询所有数据
        //查询功能之后可以获取分页相关的所有数据
        //navigatePages 导航分页的页码数
        //就是首页 上一页 1,2,3,4,5 下一页 末页 这个5就是这个含义
        PageInfo<Emp> pageInfo = new PageInfo<>(list,5);
        list.forEach(System.out::println);
        /**
         * 从这里你就可以看到PageInfo把所有分页所需的东西都封装好了
         * 你需要什么就直接取就行了
         * PageInfo{
         * pageNum=2, pageSize=4, size=4,//size展示的是当前页的数据。
         * startRow=5, endRow=8, total=31, //startRow=5, endRow=8数据库中的记录索引位置
         * pages=8, //pages就是最后一页的页码
         * list=Page{count=true, pageNum=2, pageSize=4, startRow=4, endRow=8, total=31, pages=8,
         * reasonable=false, pageSizeZero=false}
         * [Emp{empId=5, empName='拉萨道5', age=9, gender='女', deptId=4},
         * Emp{empId=6, empName='欧尔6', age=19, gender='女', deptId=3},
         * Emp{empId=7, empName='奥斯卡7', age=29, gender='男', deptId=4},
         * Emp{empId=10, empName='奥斯卡10', age=29, gender='男', deptId=2}],
         *
         * prePage=1, nextPage=3, isFirstPage=false, isLastPage=false,
         * hasPreviousPage=true, hasNextPage=true, navigatePages=5,
         * navigateFirstPage=1, navigateLastPage=5, navigatepageNums=[1, 2, 3, 4, 5]}
         */
        System.out.println(pageInfo);
    }
}
