package com.tail.bean;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Log
@Component
public class Dog {

    public Dog() {
        log.info("dog constructor ...");
    }

    @PostConstruct
    public void init() throws Exception {
        log.info("dog PostConstruct ...");
    }

    @PreDestroy
    public void destroy() throws Exception {
        log.info("dog PreDestroy ...");
    }
}
