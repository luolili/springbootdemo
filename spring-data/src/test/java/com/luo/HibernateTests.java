package com.luo;

import com.luo.dao.StudentDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试：hibernate是否可以自动生成实体对应的表
 */
public class HibernateTests {

    private ApplicationContext ac = null;

    @Before
    public void before() {
        this.ac = new ClassPathXmlApplicationContext("beans-new.xml");
        System.out.println("--start");
    }

    @After
    public void destroy() {
        this.ac = null;
        System.out.println("--end");
    }

    //执行一个空的测试

    /**
     * Hibernate:
     * <p>
     * create table Employee (
     * id integer not null,
     * age integer,
     * name varchar(255),
     * primary key (id)
     * ) engine=InnoDB
     * <p>
     * - Returns
     * - EntityReturnImpl(entity=com.luo.domain.Employee, querySpaceUid=<gen:0>,
     * path=com.luo.domain.Employee)
     * <p>
     * QuerySpaces
     * - EntityQuerySpaceImpl(uid=<gen:0>, entity=com.luo.domain.Employee)
     * - SQL table alias mapping - employee0_
     * - alias suffix - 0_
     * - suffixed key columns - {id1_0_0_}
     */
    @Test
    public void testEntityManagerFactory() {

    }

}
