package com.luo.dao;

import com.luo.dao.impl.StudentDaoImpl;
import com.luo.domain.Student;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImplTests {

    @Test
    public void testQuery() throws SQLException, IOException, ClassNotFoundException {
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.query();

        for (Student s : students) {

            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getAge());

        }

    }

    @Test
    public void testSave() {
        StudentDao studentDao = new StudentDaoImpl();
        Student s = new Student();
        s.setName("gh");
        s.setAge(33);

        studentDao.save(s);

    }
}
