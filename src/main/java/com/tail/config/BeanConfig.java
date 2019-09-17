package com.tail.config;

import com.tail.bean.Color;
import com.tail.bean.ColorFactoryBean;
import com.tail.bean.Person;
import com.tail.service.TestService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

import java.security.PublicKey;

// 满足条件，所有bean才会注册
@Conditional({WindowsCondition.class})
// 告诉Spring这是一个配置；类
@Configuration
// 包扫描
@ComponentScans({
        @ComponentScan(
                // 扫描包路径
                value = "com.tail",
                // 排除过滤器
                includeFilters = {
                        // 注解过滤
//                        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class),
                        // 指定类型过滤
//                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = TestService.class),
                        // 自定义过滤
                        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)}
        ,useDefaultFilters = false)})
@Log
// 导入组件，id默认是组件的全类名
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class BeanConfig {

    /**
     * 向容器注册一个Bean，类型为返回值类型，id默认为方法名
     */
    @Bean(value = "persion")
    public Person person01(){
        return new Person("lisi",21);
    }


    /**
     * singleton:单实例（默认）
     * prototype:多实例
     * request
     * session
     */
    @Scope("singleton")
    // 懒加载，延迟加载。程序去IOC容器中获取Bean的时候才创建
    @Lazy
    @Bean(value = "zhu")
    public Person person02(){
        log.info("开始向IOC容器中注册zhu...");
        return new Person("zhu",21);
    }

    @Scope("prototype")
    @Bean(value = "tail")
    public Person person03(){
        log.info("开始向IOC容器中注册tail...");
        return new Person("tail",21);
    }


    // @Conditional:按照条件进行判断，满足条件才注册

    /**
     * 系统是Windows时注册
     */
    @Bean(value = "bill")
    @Conditional({WindowsCondition.class})
    public Person bill(){
        log.info("开始向IOC容器中注册tail...");
        return new Person("bill",62);
    }

    /**
     * 系统是Linux时注册
     */
    @Bean(value = "linux")
    @Conditional({LinuxCondition.class})
    public Person linux(){
        log.info("开始向IOC容器中注册tail...");
        return new Person("linux",48);
    }

    /**
     * 给ioc容器注册bean的方式:
     * 1.包扫描，@ComponentScan，扫描@Component，@Controller，@Service，@Repository 注解的bean（自己写的类）
     * 2.@Bean（第三方包里面的组件）
     * 3.@Import（快速给容器导入组件）
     *      1.@Import(Class):id默认为组件的全类名
     *      2.ImportSelector:返回需要注册组件的全类名数组
     *      3.ImportBeanDefinitionRegistrar:手动注册bean到容器
     * 4.使用Spring提供的FactoryBean（工厂Bean）
     *      1.默认获取的是getObject()创建的对象
     *      2.获取工厂Bean，需要在id前加一个"&" --> &colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}
