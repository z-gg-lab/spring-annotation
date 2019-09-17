package com.tail.config;

import com.tail.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param annotationMetadata 当前类的注解信息
     * @param beanDefinitionRegistry BeanDefinition定义的注册类
     *                               所有需要注册的bean调用 BeanDefinitionRegistry.registerBeanDefinition手动注册
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        boolean blue = beanDefinitionRegistry.containsBeanDefinition("Blue");
        boolean yellow = beanDefinitionRegistry.containsBeanDefinition("Yellow");
        if (blue && yellow){
            beanDefinitionRegistry.registerBeanDefinition("rainBow",new RootBeanDefinition(RainBow.class));
        }



    }
}
