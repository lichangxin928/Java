package org.example;

import com.lcx.Service.UserService;
import com.lcx.Service.UserServiceImpl;
import com.lcx.dao.StudentDao;
import com.lcx.dao.impl.StudentDaoImpl;
import com.lcx.domain.User;
import com.lcx.utils.MyBaitsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.lang.reflect.Proxy;
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
        User user = new User();
        user.setName("lcx");
        user.setId(1);
        SqlSession sqlSession = MyBaitsUtils.getSqlSession();
        sqlSession.selectOne("com.lcx.dao.StudentDao.selectUserByIdAndName",user);
        StudentDao da = sqlSession.getMapper(StudentDao.class);
        user = da.selectUserByIdAndName(1, "1");
        System.out.println(user);
    }


    @Test
    public void ProxyTest(){
        UserService userService = new UserServiceImpl();
        UserService o = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                (proxy,method,args) -> {
                    System.out.println(method.getName());
                    method.invoke(userService,args);
                    return "fafa";
                });
        String str = o.hello();
        System.out.println(str);
    }
}
