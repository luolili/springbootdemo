package com.luo.repo;

import com.luo.domain.Employee;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * data repository
 * 查询方法的定义规则：
 * 1. and: findByLastnameAndFirstname jpql: where x.lastname = ?1 and x.firstname = ?2
 *
 * or;
 * between : findByStartDateBetween  jpql: where x.startDate between ?1 and ?2
 * after / before/ isNull
 * less than: findByAgeLessThan jpql: where x.age > ?1
 * greater than
 * -----
 * like: findByFirstNameLike   where x.firstname like ?1
 * notlike
 *
 * startingWith fingByFirstnameStartingWith
 */
@RepositoryDefinition(domainClass = Employee.class, idClass = Integer.class)//不用extends方法，用注解方法
public interface EmployeeRepository //extends Repository<Employee, Integer>
{

    //这里没有实现，也可以正确调用，这里方法的名字是有规定的，其他名字不行
    Employee findByName(String name);

    //less than
    List<Employee> findByAgeLessThan(Integer age);

    //greater than
    List<Employee> findByAgeGreaterThan(Integer age);

    //like and age
    List<Employee> findByNameStartingWithAndAgeGreaterThan(String name, Integer age);

    List<Employee> findByNameEndingWithAndAgeGreaterThan(String name, Integer age);

    List<Employee> findByNameEndingWithOrAgeGreaterThan(String name, Integer age);

    List<Employee> findByNameLikeAndAgeGreaterThan(String name, Integer age);

    List<Employee> findByNameInAndAgeGreaterThan(List<String> names, Integer age);




}
