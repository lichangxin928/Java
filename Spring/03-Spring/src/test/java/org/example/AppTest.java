package org.example;

import static org.junit.Assert.assertTrue;

import com.lcx.pa1.Person;
import com.lcx.pa1.School;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest 
{;
    @Test
    public void Test01(){
        String config = "ApplicationContext.xml";
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Person myPerson = (Person) ac.getBean("myPerson");
        School school = (School) ac.getBean("school");
        System.out.println(school);
        System.out.println(myPerson);
    }
}
