package com.tail.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Data
@ToString
public class Person {

    @Value("zhu")
    private String name;

    @Value("#{20+1}")
    private Integer age;

    @Value("${person.mail}")
    private String mail;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(){
        super();
    }
}
