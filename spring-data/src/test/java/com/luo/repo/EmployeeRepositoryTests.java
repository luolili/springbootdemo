package com.luo.repo;

import com.luo.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeRepositoryTests {

    private ApplicationContext ac = null;
    private EmployeeRepository employeeRepository = null;

    @Before
    public void before() {
        this.ac = new ClassPathXmlApplicationContext("beans-new.xml");
        employeeRepository = ac.getBean(EmployeeRepository.class);
        System.out.println("--start");
    }

    @After
    public void destroy() {
        this.ac = null;
        System.out.println("--end");
    }


    /**
     * 默认的表名都有后缀0_
     * select
     * employee0_.id as id1_0_,
     * employee0_.age as age2_0_,
     * employee0_.name as name3_0_
     * from
     * Employee employee0_
     * where
     * employee0_.name=?
     */
    @Test
    public void testFindByName() {
        Employee em = employeeRepository.findByName("xc");
        System.out.println(em.getId() + "---" + em.getAge() + "==" + em.getName());


    }
}
