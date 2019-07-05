package com.luo.repo;

import com.luo.domain.Employee;
import com.luo.service.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryTests {

    private ApplicationContext ac = null;
    private EmployeeRepository employeeRepository = null;
    private EmployeeService employeeService = null;//不能写成自动注入

    @Before
    public void before() {
        this.ac = new ClassPathXmlApplicationContext("beans-new.xml");
        employeeRepository = ac.getBean(EmployeeRepository.class);
        employeeService = ac.getBean(EmployeeService.class);
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

    @Test
    public void testFindByAgeLessTan() {
        Employee em = employeeRepository.findByName("xc");
        List<Employee> list = employeeRepository.findByAgeLessThan(30);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testFindByAgeGreaterTan() {
        Employee em = employeeRepository.findByName("xc");
        List<Employee> list = employeeRepository.findByAgeGreaterThan(30);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testFindByNameStartingWithAndAgeGreaterTan() {
        Employee em = employeeRepository.findByName("xc");
        List<Employee> list = employeeRepository.findByNameStartingWithAndAgeGreaterThan("te", 23);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testFindByNameEndingWithAndAgeGreaterTan() {
        Employee em = employeeRepository.findByName("xc");
        List<Employee> list = employeeRepository.findByNameEndingWithAndAgeGreaterThan("1", 10);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testFindByNameEndingWithOrAgeGreaterTan() {
        Employee em = employeeRepository.findByName("xc");
        List<Employee> list = employeeRepository.findByNameEndingWithAndAgeGreaterThan("1", 20);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testFindByNameLikeAndAgeGreaterTan() {
        Employee em = employeeRepository.findByName("xc");
        List<Employee> list = employeeRepository.findByNameLikeAndAgeGreaterThan("e", 23);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testFindByNameInAndAgeGreaterTan() {
        Employee em = employeeRepository.findByName("xc");
        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test9");

        List<Employee> list = employeeRepository.findByNameInAndAgeGreaterThan(names, 23);
        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testGetEmployeeByMaxId() {
        Employee e = employeeRepository.getEmployeeByMaxId();
        System.out.println(e.getId() + "--" + e.getAge() + "--" + e.getName());

    }

    @Test
    public void testGetByParams() {
        List<Employee> list = employeeRepository.getByParams("test66", 12);

        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }


    @Test
    public void testGetByParams02() {
        List<Employee> list = employeeRepository.getByParams02("test66", 12);

        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testGetByLike() {
        List<Employee> list = employeeRepository.getByLike("test");

        for (Employee e : list) {
            System.out.println(e.getId() + "--" + e.getName() + "--" + e.getAge());
        }
    }

    @Test
    public void testGetCount() {
        Integer count = employeeRepository.getCount();
        System.out.println(count.intValue());
    }


    @Test
    public void testUpdate() {
        employeeService.update(1, 9);
    }
}
