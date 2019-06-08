package contextdemo;

import contextdemo.context.TestBean;
import contextdemo.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringContextDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringContextDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AnnotationConfigApplicationContext fooContext =
                new AnnotationConfigApplicationContext(FooConfig.class);

        ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"}, fooContext
        );

        TestBean testBeanX = fooContext.getBean("testBeanX", TestBean.class);

        testBeanX.hello();

        log.info("-----------");
        testBeanX = barContext.getBean("testBeanX", TestBean.class);
        testBeanX.hello();

        testBeanX = barContext.getBean("testBeanY", TestBean.class);
        testBeanX.hello();

    }
}
