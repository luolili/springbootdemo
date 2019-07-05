package com.luo.repo;

import com.luo.domain.Employee;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * data repository
 */
@RepositoryDefinition(domainClass = Employee.class, idClass = Integer.class)//不用extends方法，用注解方法
public interface EmployeeRepository //extends Repository<Employee, Integer>
{

    //这里没有实现，也可以正确调用，这里方法的名字是有规定的，其他名字不行
    Employee findByName(String name);
}
