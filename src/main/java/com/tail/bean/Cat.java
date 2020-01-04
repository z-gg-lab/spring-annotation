package com.tail.bean;

import lombok.extern.java.Log;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Log
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        log.info("cat constructor ...");
    }

    public void destroy() throws Exception {
        log.info("cat destroy ...");
    }

    public void afterPropertiesSet() throws Exception {
        log.info("cat afterPropertiesSet ...");
    }
}
