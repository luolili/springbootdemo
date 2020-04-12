package cn.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassPathXmlApplicationContext 调用 AbstractApplicationContext 的 rerfesh()--》
 * finishBeanFactoryInitialization(beanFactory);--》DefaultListableBeanFactory的
 * preInstantiateSingletons()初始化非lazy的bean;-->
 * doGetBean();-->getSingleton()
 */
@Component
public class MyClass {
    private final MyBean myBean;

    /**
     * MyClass 通过下面这个指定的构造方法实例化MyClass
     * 和MyBean 的调用链路差不多，
     *
     * @param myBean
     */
    @Autowired
    public MyClass(MyBean myBean) {
        this.myBean = myBean;
    }
}
