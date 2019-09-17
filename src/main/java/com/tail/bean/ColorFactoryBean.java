package com.tail.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * spring创建的FactoryBean
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    public Color getObject() throws Exception {
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否单例
     *      true:是单例
     *      false:多实例
     */
    public boolean isSingleton() {
        return true;
    }
}
