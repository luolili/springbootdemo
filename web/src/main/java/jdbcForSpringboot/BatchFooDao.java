package jdbcForSpringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * batch operation
 */
@Repository
public class BatchFooDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // for batch op
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void insertBatch() {
        // first method : jdbc-way to batch insert
        jdbcTemplate.batchUpdate("insert into FOO(BAR) VALUES (?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, "b-" + i);
                    }

                    //result:b-0,b-1
                    @Override
                    public int getBatchSize() {
                        return 2;//the time of batch op
                    }
                });

        //second method to batch op
        List<FOO> list = new ArrayList<>();
        list.add(FOO.builder().id(100L).bar("b-100").build());
        list.add(FOO.builder().id(101L).bar("b-101").build());

        namedParameterJdbcTemplate
                .batchUpdate("insert into FOO(ID,BAR) values(:id, :bar)",
                        SqlParameterSourceUtils.createBatch(list));
    }

}
