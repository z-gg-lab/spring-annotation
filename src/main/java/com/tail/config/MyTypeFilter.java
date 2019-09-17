package com.tail.config;

import lombok.extern.java.Log;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

@Log
public class MyTypeFilter implements TypeFilter {

    /**
     * 匹配过滤条件
     *
     * @param metadataReader        当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取其他任何类的信息
     * @return
     * @throws IOException
     */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 当前类的资源信息（存储路径等）
        Resource resource = metadataReader.getResource();
        // 获取类名
        String className = classMetadata.getClassName();
        log.info("class --> " + className);
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}
