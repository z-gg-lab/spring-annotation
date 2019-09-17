package com.tail.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

    private String name;

    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(){
        super();
    }
}
