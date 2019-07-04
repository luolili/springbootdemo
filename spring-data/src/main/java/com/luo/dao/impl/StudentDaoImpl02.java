package com.luo.dao.impl;

import com.luo.dao.StudentDao;
import com.luo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl02 implements StudentDao {

    //@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> query() {
        final List<Student> students = new ArrayList<>();
        String sql = "select id , name, age from student";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    //resultSet.getInt("id");第二种获取方法：根据数据库里面的字段名称
                    String name = rs.getString(2);
                    int age = rs.getInt(3);
                    Student s = new Student();
                    s.setId(id);
                    s.setName(name);
                    s.setAge(age);
                    students.add(s);

                }
            }
        });
        return students;
    }

    @Override
    public void save(Student student) {
        String sql = "insert into student(name, age)  values(?,?) ";
        jdbcTemplate.update(sql, new Object[]{student.getName(), student.getAge()});
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
