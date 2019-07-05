package com.luo.repo;

import com.luo.domain.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

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
 * 缺点：方法名太长+复杂的查询无法解决
 * 使用@Query
 */
@RepositoryDefinition(domainClass = Employee.class, idClass = Integer.class)//不用extends方法，用注解方法
public interface EmployeeRepository //extends Repository<Employee, Integer>
{

    @Query("select e from Employee  e where id = (select max(id) from Employee) ")
    Employee getEmployeeByMaxId();

    //-1 用占位符?
    @Query("select e from Employee  e where e.name = ?1 and e.age = ?2")
    List<Employee> getByParams(String name, Integer age);

    //-2 用:参数名
    @Query("select e from Employee  e where e.name = :name and e.age = :age")
    List<Employee> getByParams02(@Param(value = "name") String name, @Param(value = "age") Integer age);

    @Query("select e from Employee  e where e.name like %?1%")
    List<Employee> getByLike(String name);

    @Query(value = "select count(1) from employee  ", nativeQuery = true)
    Integer getCount();

    //--update

    @Query("update Employee o set o.age = ?2 where o.id = ?1")
    @Modifying
    void update(Integer id, Integer age);

    //---------
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
