package jdbcForSpringboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 如果不在这个类上加@Component把他加入到spring容器
 * 就要MyConfig加@EnableConfigurationProperties(MyProperty.class)
 */
@ConfigurationProperties(prefix = "com")
//@Component
@Data
public class MyProperty {

    public String username;


}
