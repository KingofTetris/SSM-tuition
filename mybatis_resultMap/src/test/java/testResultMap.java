import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/5/29
 */
public class testResultMap {

    @Test
    public void testGetEmpByEmpId(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empByEmpId = mapper.getEmpByEmpId(1);
        System.out.println(empByEmpId);
    }


    @Test
    public void testGetEmpAndDeptByEmpId(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDeptByEmpId = mapper.getEmpAndDeptByEmpId(1);
        System.out.println(empAndDeptByEmpId);
    }

    @Test
    public void testGetEmpAndDeptByStep(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStep(1);
        //开启懒加载后，你会发现，你只需要emp的empName，那么这个查询就只会执行第一步
        //不会再继续进行下一步，这是分步查询的最大优点，减少不必要的查询，提高速度，节省内存。
        System.out.println(emp.getEmpName());
    }


    @Test
    public void testGetDeptByDeptId(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept deptByDeptId = mapper.getDeptAndEmpByDeptId(3);
        //开启懒加载后，你会发现，你只需要emp的empName，那么这个查询就只会执行第一步
        //不会再继续进行下一步，这是分步查询的最大优点，减少不必要的查询，提高速度，节省内存。
        System.out.println(deptByDeptId.getDeptName());
    }
}
