package org.example;

import com.lcx.dao.StudentDao;
import com.lcx.dao.impl.StudentDaoImpl;
import com.lcx.domain.User;
import com.lcx.utils.MyBaitsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSelect(){
        StudentDao studentDao = new StudentDaoImpl();
        List<User> users = studentDao.userList();
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void proxyMybatis(){
        /**
         * mybatis 的动态代理，mybatis 帮忙实现
         *
         */
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        // 调用 dao 的方法
        List<User> users = dao.userList();
        users.forEach(user -> System.out.println(user));
    }
    @Test
    public void testSelectById(){
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        User user = mapper.selectUserById(1);
        System.out.println(user);

    }

    /**
     *
     *
     */
    @Test
    public void testManyParam(){
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        StudentDao da = sqlSession.getMapper(StudentDao.class);
        User user = da.selectUserByIdAndName(1, "12");
        System.out.println(user);
    }
}
