package com.luo.dao.impl;

import com.luo.dao.StudentDao;
import com.luo.domain.Student;
import com.luo.util.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> query() {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "select id , name, age from student";
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                //resultSet.getInt("id");第二种获取方法：根据数据库里面的字段名称

                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                Student s = new Student();
                s.setId(id);

                s.setName(name);

                s.setAge(age);
                students.add(s);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(resultSet, ps, connection);
        }

        return students;
    }

    @Override
    public void save(Student student) {
        Connection connection = null;
        PreparedStatement ps = null;

        String sql = "insert into student(name, age)  values(?,?) ";
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, student.getName());

            ps.setInt(2, student.getAge());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(resultSet, ps, connection);
        }

    }

}
