package com.luo.dao;

import com.luo.domain.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao {

    List<Student> query();

    void save(Student student);
}
