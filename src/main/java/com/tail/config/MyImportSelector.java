package com.tail.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param annotationMetadata 当前标注@Import的类的所有注解信息
     * @return 需要导入到容器中的组件全类名
     */
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{"com.tail.bean.Blue","com.tail.bean.Yellow"};
    }
}
