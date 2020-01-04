package com.tail;

import com.tail.bean.Person;
import com.tail.config.BeanConfigOfLifeCycle;
import com.tail.config.BeanConfigOfProperty;
import lombok.extern.java.Log;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class BeanOfPropertyTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfigOfProperty.class);
        Person person = (Person) applicationContext.getBean("person");
        log.info(person.toString());
    }
}
