package com.tail;

import com.tail.config.BeanConfigOfLifeCycle;
import lombok.extern.java.Log;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class BeanOfLifeCycleTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfigOfLifeCycle.class);
        log.info("IOC容器创建完成...");
        applicationContext.close();
    }
}
