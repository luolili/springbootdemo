package springbucks;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableCaching
public class SpringJsonViewDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringJsonViewDemoApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    //	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
//		return builder -> builder.indentOutput(true);
//	}

    //json output beautify
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        
        return Builder -> Builder.indentOutput(true);
    }
}
