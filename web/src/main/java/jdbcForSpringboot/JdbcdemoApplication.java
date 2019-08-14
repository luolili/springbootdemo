package jdbcForSpringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class JdbcdemoApplication implements CommandLineRunner {


    @Autowired
    DataSource dataSource;

    @Autowired
    private FooDao fooDao;

    @Autowired
    private BatchFooDao batchFooDao;

    public static void main(String[] args) {
        SpringApplication.run(JdbcdemoApplication.class, args);
    }

    @Bean
    @Autowired
    public SimpleJdbcInsert insert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("FOO").usingGeneratedKeyColumns("ID");

    }

    @Override
    public void run(String... args) throws Exception {
        //fooDao.insertData();
        batchFooDao.insertBatch();
        fooDao.listData();


    }
}
