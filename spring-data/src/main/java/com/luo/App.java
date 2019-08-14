package com.luo;

import com.luo.dao.EmployeeRepo;
import com.luo.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableJpaRepositories
public class App implements ApplicationRunner {
    @Autowired
    private EmployeeRepo employeeRepo;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Employee e = query();

        System.out.println("---");
        System.out.println(e);
    }

    public Employee query() {
        Optional<Employee> re = employeeRepo.findById(1);
        return re.get();


    }
}
