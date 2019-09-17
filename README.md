# spring-annotation

> [spring注解开发](https://www.bilibili.com/video/av59569306/):

## 组件注册

* @ComponentScan配置包扫描，默认会将@Controller，@Service，@Repository，@Component修饰的Bean注入到IOC容器中，可以使用@ComponentScan.Filter()配置扫描过滤器。适合注入自己创建的Java Bean。

  ```xml
  <!-- @ComponentScan对应的xml配置-->
  <context:component-scan base-package="com.tail" use-default-filters="false"/>
  ```

  * 常见的过滤方式

    * ```java
      @ComponentScans({
              @ComponentScan(
                      // 扫描包路径
                      value = "com.tail",
                      // 排除过滤器
                      includeFilters = {
                              // 注解过滤
                              @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class),
                              // 指定类型过滤
                              @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = TestService.class),
                              // 自定义过滤
                              @ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)}
              ,useDefaultFilters = false)})
      ```

      ```java
      /**
       * 自定义扫描过滤器
       **/
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
      ```

* 组件注册的方式
  * 使用@ComponentScan配置包扫描。

* @Bean注解，可以配合@Conditional实现条件注册，@Scope（默认是单例）配置作用范围，@Lazy实现懒加载。

  ```xml
   <!-- @Bean注解对应的xml-->
   <bean id="person" class="com.tail.bean.Person" scope="prototype">-->
          <!--<property name="name" value="tail"></property>-->
          <!--<property name="age" value="12"></property>-->
   </bean>
  ```

  * @Conditional({<T entend Condition>})可以标注在类或者方法上，实现Condition接口即可

    ```java
    /**
     * 系统是Windows时注册
     */
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
    ```

* @Import注解

  ```java
  @Import({Person.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
  ```

  * @Import(Persion.class)，Bean的id默认为Persion全类名

  * ImportSelector：返回需要注册组件的全类名数组

    ```java
    public class MyImportSelector implements ImportSelector {
        /**
         * @param annotationMetadata 当前标注@Import的类的所有注解信息
         * @return 需要导入到容器中的组件全类名
         */
        public String[] selectImports(AnnotationMetadata annotationMetadata) {
            return new String[]{"com.tail.bean.Blue","com.tail.bean.Yellow"};
        }
    }
    ```

  * ImportBeanDefinitionRegistrar：手动注册Bean到IOC容器

    ```java
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
    ```

* 使用Spring提供的FactoryBean

  ```java
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
  ```

  * 获取Bean的时候，默认获取是getObject()创建的对象

  * 获取工厂Bean，需要在id前加**&**，即 

    ```java
    context.getBean("&colorFactoryBean")
    ```

    

## 生命周期

## 自动装配

## AOP原理（源码）



