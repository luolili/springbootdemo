package springmvcdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableCaching
public class SpringmvcDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringmvcDemoApplication.class, args);
    }


}
