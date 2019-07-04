package com.luo;

import com.luo.dao.StudentDao;
import com.luo.dao.impl.StudentDaoImpl;
import com.luo.domain.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DataSourceTests {

    private ApplicationContext ac = null;
    private StudentDao studentDao = null;

    @Before
    public void before() {
        this.ac = new ClassPathXmlApplicationContext("beans.xml");
        studentDao = (StudentDao) ac.getBean("studentDao");
        System.out.println("--start");
    }

    @After
    public void destroy() {
        this.ac = null;
        System.out.println("--end");
    }

    @Test
    public void testDataSource() {
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void testJdbcTemplate() {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
        Assert.assertNotNull(jdbcTemplate);
    }

    @Test
    public void testQuery() throws SQLException, IOException, ClassNotFoundException {
        List<Student> students = studentDao.query();

        for (Student s : students) {

            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getAge());

        }

    }

    @Test
    public void testSave() {
        Student s = new Student();
        s.setName("h");
        s.setAge(3);

        studentDao.save(s);

    }
}
