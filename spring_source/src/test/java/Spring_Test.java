import com.lcx.demo.Service.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_Test {


    @Test
    public void Test(){

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.getAge());


    }
}