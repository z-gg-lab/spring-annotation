package com.tail.config;

import com.tail.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean生命周期：bean创建-->初始化-->销毁
 *
 * 对象创建
 *      单实例：默认是在容器启动时创建
 *      多实例：在每次获取对象的时候创建
 * 初始化
 *      对象创建并赋值完成，调用初始化方法
 * 销毁
 *      单实例：容器关闭的时候
 *      多实例：容器不会管理这个Bean，不会调用销毁方法，需要手动调用。
 * spring中bean的生命周期由ioc容器管理，我们可以自定义初始化和销毁的方法，容器在bean进行到当前生命周期的时候就会调用自定义的方法
 * 1.使用@Bean指定 init-method，destroy-method
 * 2.通过实现InitializingBean定义初始化逻辑，实现DisposableBean实现销毁逻辑
 * 3.JSR250规范注解
 *      @PostConstruct --> 在bean创建并属性赋值完成时调用
 *      @PreDestroy --> 在容器销毁bean之前调用
 * 4.BeanPostProcessor
 *
 *
 *
 *
 *
 * @author ZhuTail
 */
@Configuration
@ComponentScan("com.tail.bean")
public class BeanConfigOfLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

}
