package com.luo.repo;

import com.luo.dao.EmployeeRepo;
import com.luo.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeRepo.class})
public class EmployeeRepoTest {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    public void query() {
        Optional<Employee> re = employeeRepo.findById(1);
        System.out.println(re.get());

    }
}
