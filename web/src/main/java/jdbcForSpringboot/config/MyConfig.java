package jdbcForSpringboot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableConfigurationProperties(MyProperty.class)
public class MyConfig {


    @Bean
    public static DataSource dataSource(MyProperty property) {
        System.out.println(property.getUsername());
        return null;
    }
}
