package org.example;

import static org.junit.Assert.assertTrue;

import com.lcx.dao.StudentDao;
import com.lcx.dao.impl.StudentDaoImpl;
import com.lcx.domain.User;
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
}
