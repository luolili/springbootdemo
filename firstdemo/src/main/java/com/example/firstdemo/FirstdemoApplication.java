package com.example.firstdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * webflux:netty, not tomcat
 */
@SpringBootApplication
@Slf4j
public class FirstdemoApplication implements CommandLineRunner {

    //config single dataSource
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(FirstdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        showConnection();
    }

    private void showConnection() throws SQLException {

        log.info(dataSource.toString());
        //dataSource :HikariDataSource (null)
        Connection conn = dataSource.getConnection();
        log.info(conn.toString());
        //autoconfig by sprngboot:HikariProxyConnection@1860060918
        // wrapping conn0: url=jdbc:h2:mem:testdb user=SA
        conn.close();

    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM FOO")
                .forEach(row -> {
                    log.info(row.toString());
                });
    }
}
