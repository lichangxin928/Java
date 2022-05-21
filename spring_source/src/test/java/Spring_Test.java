import com.lcx.Cache.config.SpringConfiguration;
import com.lcx.demo.Service.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring_Test {


    @Test
    public void Test(){

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.getAge());

    }

    /* 循环依赖 + 三级缓存 */
    @Test
    public void Test2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println(beanDefinitionNames[i]);
        }
    }

}