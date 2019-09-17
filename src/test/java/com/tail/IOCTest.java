package com.tail;

import com.tail.bean.ColorFactoryBean;
import com.tail.bean.Person;
import com.tail.config.SpringConfig;
import lombok.extern.java.Log;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

@Log
public class IOCTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    public void test01(){
        // xml配置方式
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 注解配置
        Person person = (Person)applicationContext.getBean("zhu");

        log.info(person.toString());
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
            log.info(bean);
        }
    }

    @Test
    public void test02(){
        printBean();
        printOs();
    }

    @Test
    public void test03(){
        // 工厂Bean获取的是调用getObject()创建的对象
        Object object1 = applicationContext.getBean("colorFactoryBean");
        Object object2 = applicationContext.getBean("colorFactoryBean");
        Object object3 = applicationContext.getBean("&colorFactoryBean");
        log.info(object1.getClass().toString());
        log.info(object3.getClass().toString());
        log.info(String.valueOf(object1==object2));
    }



    private void printBean(){
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String bean : beanNamesForType) {
            log.info(bean);
        }

        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        log.info(personMap.toString());
    }

    private void printOs(){
        String osName = applicationContext.getEnvironment().getProperty("os.name");
        log.info("当前的操作系统是-->" + osName);
    }
}
