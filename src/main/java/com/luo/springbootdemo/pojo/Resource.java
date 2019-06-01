package com.luo.springbootdemo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * read property value from properties file
 * properties map to the entity
 */
@Configuration
@ConfigurationProperties(prefix = "com.luo")//prefix
//@PropertySource("classpath:resource.properties")
@PropertySource(value = "classpath:resource.properties")
public class Resource {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
