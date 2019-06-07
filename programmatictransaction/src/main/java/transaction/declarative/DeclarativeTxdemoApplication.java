package transaction.declarative;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 1. interface and its impl
 * 2. add @Transaction on impl method
 * 3. specify every method rollbackFor exception
 * 4. autowire the interface and jdbcTemplate
 * 5. invoke the method in interface and use jdbcTemplate to execute sql
 */
@SpringBootApplication
@Slf4j

public class DeclarativeTxdemoApplication implements CommandLineRunner {

    @Autowired
    private FooService fooService;


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTxdemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("AAA: {}", jdbcTemplate
                .queryForObject("select count(*) from FOO where BAR ='AAA'", Long.class));

        try {
            fooService.insertThenRollBack();
        } catch (Exception e) {
            log.info("BBB: {}", jdbcTemplate
                    .queryForObject("select count(*) from FOO where BAR ='BBB'", Long.class));

        }

        try {
            //no transaction
            fooService.invokeInsertThenRollBack();
        } catch (Exception e) {
            log.info("BBB: {}", jdbcTemplate
                    .queryForObject("select count(*) from FOO where BAR ='BBB'", Long.class));

        }
    }


}
