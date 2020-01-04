package com.tail.bean;

import lombok.extern.java.Log;

@Log
public class Car {

    public Car() {
        log.info("car constructor ...");
    }

    public void init(){
        log.info("car init ...");
    }

    public void destroy(){
        log.info("car destroy ...");
    }
}
