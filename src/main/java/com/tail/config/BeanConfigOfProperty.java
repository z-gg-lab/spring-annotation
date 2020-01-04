package com.tail.config;

import com.tail.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用@PropertySource读取配置文件的k/v到环境变量，用${k}获取v
 * @author ZhuTail
 */
@PropertySource({"classpath:/person.properties"})
@Configuration
public class BeanConfigOfProperty {

    @Bean
    public Person person(){
        return new Person();
    }

}
