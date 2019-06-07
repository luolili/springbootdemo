package transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootApplication
@Slf4j

public class ProgramaticTxdemoApplication implements CommandLineRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(ProgramaticTxdemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("count before  tx:{}", getCount());//0

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.execute("insert into FOO(ID,BAR) VALUES (1,'aaa')");
                log.info("count in transaction {}", getCount());//1
                status.setRollbackOnly();//0
            }
        });

        log.info("count after transaction:{}", getCount());
    }

    public long getCount() {

        return (long) jdbcTemplate
                .queryForList("select count(*) as cnt from FOO").get(0).get("cnt");
    }
}
