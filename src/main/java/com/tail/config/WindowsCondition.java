package com.tail.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        // ioc的BeanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        // 类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        // bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        // 操作系统名称 -Dos.name=Linux
        String osName = conditionContext.getEnvironment().getProperty("os.name");

        if (osName.contains("Windows")){
            return true;
        }

        return false;
    }
}
