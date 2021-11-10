 # Spring 面向注解开发
## SpringIOC 的注解
### 1. 用于注册驱动的注解
#### 1.1 @Configuration
    作用：
	    它是在spring3.0版本之后加入的。此注解是spring支持注解驱动开发的一个标志。表明当前类是spring的一个配置类，作用是替代spring的applicationContext.xml。但其本质就是@Component注解，被此注解修饰的类，也会被存入spring的ioc容器。

    属性：
        value:
                用于存入spring的Ioc容器中Bean的id。
    使用场景：
        在注解驱动开发时，用于编写配置的类，通常可以使用此注解。一般情况下，我们的配置也会分为主从配置，@Configuration一般出现在主配置类上。例如，入门案例中的SpringConfiguration类上。值得注意的是，如果我们在注解驱动开发时，构建ioc容器使用的是传入字节码的构造函数，此注解可以省略。但是如果传入的是一个包，此注解则不能省略。
    
#### 1.2 @ComponentScan
    作用：
        用于指定创建容器时要扫描的包。该注解在指定扫描的位置时，可以指定包名，也可以指定扫描的类。同时支持定义扫描规则，例如包含哪些或者排除哪些。同时，它还支持自定义Bean的命名规则

    属性：
        value:用于指定要扫描的包。当指定了包的名称之后，spring会扫描指定的包及其子包下的所有类。
        
        basePackages:它和value作用是一样的。
        
        basePackageClasses:指定具体要扫描的类的字节码。
            
        nameGenrator:指定扫描bean对象存入容器时的命名规则

        scopeResolver:用于处理并转换检测到的Bean的作用范围。
            
        soperdProxy:用于指定bean生成时的代理方式。默认是Default，则不使用代理。详情请参考第五章第5小节ScopedProxyMode枚举。
        
        resourcePattern:用于指定符合组件检测条件的类文件，默认是包扫描下的  **/*.class
        
        useDefaultFilters:是否对带有@Component @Repository @Service @Controller注解的类开启检测,默认是开启的。
        
        includeFilters:自定义组件扫描的过滤规则，用以扫描组件。
            FilterType有5种类型：
                ANNOTATION, 注解类型 默认
                ASSIGNABLE_TYPE,指定固定类
                ASPECTJ， ASPECTJ类型
                REGEX,正则表达式
                CUSTOM,自定义类型

        excludeFilters:自定义组件扫描的排除规则。

        lazyInit:组件扫描时是否采用懒加载 ，默认不开启。

    使用场景：
        在注解驱动开发时，我们自己编写的类都使用注解的方式进行配置，要想让spring添加到ioc容器中，就需要使用此注解来实现组件的扫描。
    细节：
        在spring4.3版本之后还加入了一个@ComponentScans的注解，该注解就是支持配置多个@ComponentScan。

### 1.3. @Bean
    作用：
	    它写在方法上，表示把当前方法的返回值存入spring的ioc容器。同时还可以出现在注解上，作为元注解来使用。		
        
    属性：
        name:用于指定存入spring容器中bean的标识。支持指定多个标识。当不指定该属性时，默认值是当前方法的名称。
     
        value:此属性是在4.3.3版本之后加入的。它和name属性的作用是一样的。
            
        autowireCandidate:用于指定是否支持自动按类型注入到其他bean中。只影响@Autowired注解的使用。不影响@Resource注解注入。默认值为true，意为允许使用自动按类型注入。
            
        initMethod:用于指定初始化方法。
        
        destroyMethod:用于指定销毁方法。
        
    使用场景：
        通常情况下，在基于注解的配置中，我们用于把一个类存入spring的ioc容器中，首先考虑的是使用@Component以及他的衍生注解。但是如果遇到要存入容器的Bean对象不是我们写的类，此时无法在类上添加@Component注解，这时就需要此注解了。
            
    示例：
        例如，在我们配置JdbcTemplate使用Spring内置数据源DriverManagerDataSource时，
        数据源类是spring-jdbc这个jar包中类，此时我们无法编辑，在上面加注解，此时就可以使用@Bean注解配置。

#### 1.4. Import
    作用：
	    该注解是写在类上的，通常都是和注解驱动的配置类一起使用的。其作用是引入其他的配置类。使用了此注解之后，可以使我们的注解驱动开发和早期xml配置一样，分别配置不同的内容，使配置更加清晰。同时指定了此注解之后，被引入的类上可以不再使用@Configuration,@Component等注解。

    属性：
        value：用于指定其他配置类的字节码。它支持指定多个配置类。
        
    使用场景：
        当我们在使用注解驱动开发时，由于配置项过多，如果都写在一个类里面，配置结构和内容将杂乱不堪，此时使用此注解可以把配置项进行分门别类进行配置。

#### 1.5. ProperSource
    作用：
	    用于指定读取资源文件的位置。注意，它不仅支持properties，也支持xml文件，并且通过YAML解析器，配合自定义PropertySourceFactory实现解析yml配置文件
    属性：
        name:指定资源的名称。如果没有指定，将根据基础资源描述生成。
        
        value:指定资源的位置。可以是类路径，也可以是文件路径。
        
        ignoreResourceNotFound:指定是否忽略资源文件有没有，默认是false,也就是说当资源文件不存在时spring启动将会报错。
        
        encoding:指定解析资源文件使用的字符集。当有中文的时候，需要指定中文的字符集。
        factory:指定读取对应资源文件的工厂类，默认的是PropertySourceFactory。
        
    使用场景：
        我们实际开发中，使用注解驱动后，xml配置文件就没有了，此时一些配置如果直接写在类中，会造成和java源码的紧密耦合，修改起来不方便。此时一些配置可以使用properties或者yml来配置就变得很灵活方便。

### 2. 注入时机和设定注入条件的注解
    
#### 2.1. @DependsOn
    作用：用于指定某个类的创建依赖的bean对象先创建。spring中没有特定bean的加载顺序，使用此注解则可指定bean的加载顺序。(在基于注解配置中，是按照类中方法的书写顺序决定的)
        
    属性：
        value：用于指定bean的唯一标识。被指定的bean会在当前bean创建之前加载。
    
    使用场景：
        在观察者模式中，分为事件，事件源和监听器。一般情况下，我们的监听器负责监听事件源，
        当事件源触发了事件之后，监听器就要捕获，并且做出相应的处理。以此为前提，
        我们肯定希望监听器的创建时间在事件源之前，此时就可以使用此注解。

#### 2.2. @Lazy
    作用：用于指定单例bean对象的创建时机。在没有使用此注解时，单例bean的生命周期与容器相同。 但是当使用了此注解之后，单例对象的创建时机变成了第一次使用时创建。
             
    注意：这不是延迟加载思想（因为不是每次使用时都创建，只是第一次创建的时机改变了）。
        
    属性：
        value:指定是否采用延迟加载。默认值为true，表示开启。
    
    使用场景：在实际开发中，当我们的Bean是单例对象时，并不是每个都需要一开始都加载到ioc容器之中，有些对象可以在真正使用的时候再加载，当有此需求时，即可使用此注解。值得注意的是，此注解只对单例bean对象起作用，当指定了@Scope注解的prototype取值后，此注解不起作用。
    
#### 2.3. @Conditional
    作用：它的作用是根据条件选择注入的bean对象。
    属性：
        value:用于提供一个Condition接口的实现类，实现类中需要编写具体代码实现注入的条件。
        
    使用场景：当我们在开发时，可能会使用多平台来测试，例如我们的测试数据库分别部署到了linux和windows两个操作系统上面，现在根据我们的工程运行环境选择连接的数据库。此时就可以使用此注解。同时基于此注解引出的@Profile注解，就是根据不同的环境，加载不同的配置信息

#### 2.4. @Profile
    @Profile注解是spring提供的一个用来标明当前运行环境的注解。我们正常开发的过程中经常遇到的问题是，开发环境是一套环境，测试是一套环境，线上部署又是一套环境。这样从开发到测试再到部署，会对程序中的配置修改多次，尤其是从测试到上线这个环节，让测试的也不敢保证改了哪个配置之后能不能在线上运行。为了解决上面的问题，我们一般会使用一种方法，就是针对不同的环境进行不同的配置，从而在不同的场景中跑我们的程序。而spring中的@Profile注解的作用就体现在这里。在spring使用DI来注入的时候，能够根据当前制定的运行环境来注入相应的bean。最常见的就是使用不同的DataSource了。

### 3. 用于创建对象的注解
    
#### 3.1. @Componment 和三个衍生注解
    作用：
	    这四个注解都是用于修饰类的。是用于把当前类创建一个对象，存入spring的ioc容器中。在实例化时，首选默认无参构造函数。同时支持带参构造，前提是构造函数的参数依赖必须要有值。否则抛异常	
    
    属性：
        value:用于指定存入容器时bean的id。当不指定时，默认值为当前类的名称。
    
    使用场景：
        当我们需要把自己编写的类注入到Ioc容器中，就可以使用以上四个注解实现。以上四个注解中@Component注解通常用在非三层对象中。而@Controller，@Service，@Repository三个注解一般是针对三层对象使用的，提供更加精确的语义化配置。需要注意的是，spring在注解驱动开发时，要求必须先接管类对象，然后会处理类中的属性和方法。如果类没有被spring接管，那么里面的属性和方法上的注解都不会被解析。

#### 3.2. @Qualifier 
    作用于 @Autowrite 下面，当有多个实例时，可以指定名称
#### 3.3. @Scope 
    控制是否是单例的

### 4. 用于注入数据的注解
    
#### 4.1. @Autowired
    作用：
	    自动按照类型注入。当ioc容器中有且只有一个类型匹配时可以直接注入成功。当有超过一个匹配时，则使用变量名称（写在方法上就是方法名称）作为bean的id，在符合类型的bean中再次匹配，能匹配上就可以注入成功。当匹配不上时，是否报错要看required属性的取值。
    属性：
        required：是否必须注入成功。默认值是true，表示必须注入成功。当取值为true的时候，注入不成功会报错。
        
    使用场景：
        此注解的使用场景非常之多，在实际开发中应用广泛。
        通常情况下我们自己写的类中注入依赖bean对象时，都可以采用此注解。

#### 4.2. @Qualifier
    作用：当使用自动按类型注入时，遇到有多个类型匹配的时候，就可以使用此注解来明确注入哪个bean对象。注意它通常情况下都必须配置@Autowired注解一起使用
        
    属性：
        value：用于指定bean的唯一标识。
    使用场景：
        在我们的项目开发中，很多时候都会用到消息队列，我们一ActiveMQ为例。当和spring整合之后，Spring框架提供了一个JmsTemplate对象，它既可以用于发送点对点模型消息也可以发送主题模型消息。如果项目中两种消息模型都用上了，那么针对不同的代码，将会注入不同的JmsTemplate，而容器中出现两个之后，就可以使用此注解注入。当然不用也可以，我们只需要把要注入的变量名称改为和要注入的bean的id一致即可。

#### 4.3. @Resource
    作用：
	    此注解来源于JSR规范（Java Specification Requests）,其作用是找到依赖的组件注入到应用来，它利用了JNDI（Java Naming and Directory Interface Java命名目录接口 J2EE规范之一）技术查找所需的资源。默认情况下，即所有属性都不指定，它默认按照byType的方式装配bean对象。如果指定了name，没有指定type，则采用byName。如果没有指定name，而是指定了type，则按照byType装配bean对象。当byName和byType都指定了，两个都会校验，有任何一个不符合条件就会报错。

    属性：
        name：资源的JNDI名称。在spring的注入时，指定bean的唯一标识。
        
        type：指定bean的类型。
        
        lookup:引用指向的资源的名称。它可以使用全局JNDI名称链接到任何兼容的资源。
    
        authenticationType:指定资源的身份验证类型。它只能为任何受支持类型的连接工厂的资源指定此选项，而不能为其他类型的资源指定此选项。
    
        shareable：指定此资源是否可以在此组件和其他组件之间共享。
        
        mappedName：指定资源的映射名称。
        
        description：指定资源的描述。
    使用场景：
        当我们某个类的依赖bean在ioc容器中存在多个的时候，可以使用此注解指定特定的bean对象注入。当然我们也可以使用@Autowired配合@Qualifier注入。
         
#### 4.4. @Value
    作用：
	    用于注入基本类型和String类型的数据。它支持spring的EL表达式，可以通过${} 的方式获取配置文件中的数据。配置文件支持properties,xml和yml文件。

    属性：
        value:指定注入的数据或者spring的el表达式。

    使用场景：
        在实际开发中，像连接数据库的配置，发送邮件的配置等等，都可以使用配置文件配置起来。此时读取配置文件就可以借助spring的el表达式读取。
    
#### 4.5. @Inject
    作用：
        它也是用于建立依赖关系的。和@Resource和@Autowired的作用是一样。在使用之前需要先导入坐标：
```xml

        <!-- https://mvnrepository.com/artifact/javax.inject/javax.inject -->
        <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>
```
            
    但是他们之前也有区别：
        @Autowired：来源于spring框架自身。
                    默认是byType自动装配，当配合了@Qualifier注解之后，由@Qualifier实现byName装配。它有一个required属性，用于指定是否必须注入成功。

        @Resource：来源于JSR-250规范。
                在没有指定name属性时是byType自动装配，当指定了name属性之后，采用byName方式自动装配。

        @Inject：来源于JSR-330规范。（JSR330是Jcp给出的官方标准反向依赖注入规范。）它不支持任何属性，但是可以配合@Qualifier或者@Primary注解使用。同时，它默认是采用byType装配，当指定了JSR-330规范中的@Named注解之后，变成byName装配。
            
    属性：无

    使用场景：
        在使用@Autowired注解的地方，都可以替换成@Inject。它也可以出现在方法上，构造函数上和字段上，但是需要注意的是：因为JRE无法决定构造方法注入的优先级，所以规范中规定类中只能有一个构造方法带@Inject注解。

#### 4.6. @Primary
    作用：用于指定bean的注入优先级。被@Primary修饰的bean对象优先注入
    属性：无
    使用场景：
        当我们的依赖对象，有多个存在时，@Autowired注解已经无法完成功能,此时我们首先想到的是@Qualifier注解指定依赖bean的id。但是此时就产生了，无论有多少个bean，每次都会使用指定的bean注入。但是当我们使用@Primary，表示优先使用被@Primary注解的bean，但是当不存在时还会使用其他的。

### 5. 生命周期以及作用范围的注解
    
#### 5.1. @Scope
    作用：用于指定bean对象的作用范围。
    属性：
        value:指定作用范围的取值。在注解中默认值是""。
            但是在spring初始化容器时，会借助ConfigurableBeanFactory接口中的类成员：String SCOPE_SINGLETON = "singleton";
            
        scopeName:它和value的作用是一样的。
            
        proxyMode:它是指定bean对象的代理方式的。指定的是ScopedProxyMode枚举的值
        
            DEFAULT：默认值。（就是NO）
            NO：不使用代理。
            INTERFACES：使用JDK官方的基于接口的代理。
            TARGET_CLASS：使用CGLIB基于目标类的子类创建代理对象。
    使用场景：
        在实际开发中，我们的bean对象默认都是单例的。通常情况下，被spring管理的bean都使用单例模式来创建。但是也有例外，例如Struts2框架中的Action，由于有模型驱动和OGNL表达式的原因，就必须配置成多例的。

#### 5.2. @PostConstruct
    作用：用于指定bean对象的初始化方法。
        
    属性：无
        
    使用场景：在bean对象创建完成后，需要对bean中的成员进行一些初始化的操作时，就可以使用此注解配置一个初始化方法，完成一些初始化的操作。

#### 5.3. @PreDestroy
    作用：用于指定bean对象的销毁方法。
    属性：无
    使用场景：在bean对象销毁之前，可以进行一些清理操作。

## SpringAOP 的注解

### 1. 用于开启 AOP 注解支持
#### 1.1 @EnableAspectAutoProxy
    作用:
        表示开启spring对注解aop的支持。它有两个属性，分别是指定采用的代理方式和 是否暴露代理对象，通过AopContext可以进行访问。从定义可以看得出，它引入 AspectJAutoProxyRegister.class对象，该对象是基于注解@EnableAspectJAutoProxy 注册一个AnnotationAwareAspectJAutoProxyCreator，该对象通过调用 AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(regist ry);注册一个aop代理对象生成器。关于AnnotationAwareAspectJAutoProxyCreator请参 考第五章第二小节《AnnotationAwareAspectJAutoProxyCreator对象的分析》
    属性:
        proxyTargetClass:指定是否采用cglib进行代理。默认值是false，表示使用jdk的代理。 
        exposeProxy:指定是否暴露代理对象，通过AopContext可以进行访问。 
    使用场景:当我们注解驱动开发时，在需要使用aop实现某些功能的情况下，都需要用到此注解。

### 2. 用于配置切面的
#### 2.1 @Aspect
    作用:声明当前类是一个切面类。
    属性:
        value:
            默认我们的切面类应该为单例的。但是当切面类为一个多例类时，指定预处理的切入点表达式。
            用法是perthis(切入点表达式)。 它支持指定切入点表达式，或者是用@Pointcut修饰的方法名称(要求全限定方法名) 
    使用场景:此注解也是一个注解驱动开发aop的必备注解。

### 3. 用于配置切面表达式的
#### 3.1 @Potincut
    作用:此注解是用于指定切入点表达式的。
    属性:
        value:用于指定切入点表达式。
        argNames: 用于指定切入点表达式的参数。参数可以是execution中的，也可以是
        args中的。通常情况下不使用此属性也可以获得切入点方法参数。 
    使用场景:在实际开发中，当我们的多个通知需要执行，同时增强的规则确定的情况下，就可 以把切入点表达式通用化。
            此注解就是代替xml中的<aop:pointcut>标签，实现切入点表达式的通用化。
### 4. 用于配置通知的
#### 4.1 @Before
    作用:被此注解修饰的方法为前置通知。前置通知的执行时间点是在切入点方法执行之后
    属性:
        value:用于指定切入点表达式，或者是切入点表达式的引用
        argNames:用于指定切入点表达式参数的名称。它要求和切入点表达式中的参数名称一致。通常不指定也可以获取切入点方法的参数内容。
    使用场景:
        在实际开发中，我们需要对切入点方法执行之前进行增强， 此时就用到了前置通
        知。在通知(增强的方法)中需要获取切入点方法中的参数进行处理时，就要配合切入点表达
        式参数来使用。

#### 4.2 @AfterReturning
    作用:用于配置后置通知。后置通知的执行是在切入点方法正常执行之后执行。 需要注意的是，由于基于注解的配置时,spring创建通知方法的拦截器链时，后置
        通知在最终通知之后，所以会先执行@After注解修饰的方法。 
    属性:
        value:用于指定切入点表达式或者其引用
        pointcut: 它的作用和value是一样的。
        returning:指定切入点方法返回值的变量名称。它必须和切入点方法返回值名称一
        argNames: 用于指定切入点表达式参数的名称。它要求和切入点表达式中的参数名称
                  一致。通常不指定也可以获取切入点方法的参数内容。
    使用场景:
        此注解是用于配置后置增强切入点方法的。被此注解修饰方法会在切入点方法正常
        执行之后执行。在我们实际开发中，像提交事务，记录访问日志，统计方法执行效率等等都可
        以利用后置通知实现。

#### 4.3 @AfterThrowing
    作用:用于配置异常通知。
    属性:
        value:切入点表达式或者其引用
        pointcut: 它的作用和value是一样的。
        throwing:指定切入点方法执行产生异常时的异常对象变量名称。它必须和异常变量
                 名称一致。
        argNames:
                用于指定切入点表达式参数的名称。它要求和切入点表达式中的参数名称
                一致。通常不指定也可以获取切入点方法的参数内容。
    使用场景:用此注解修饰的方法执行时机是在切入点方法执行产生异常之后执行。

#### 4.4 @After
    作用:用于指定最终通知。
    属性:
        value:用于指定切入点表达式或者引用
        argNames:用于指定切入点表达式参数的名称。它要求和切入点表达式中的参数名称
                 一致。通常不指定也可以获取切入点方法的参数内容。
    使用场景:
        最终通知的执行时机，是在切入点方法执行完成之后执行，无论切入点方法执行是
        否产生异常最终通知都会执行。所以被此注解修饰的方法，通常都是做一些清理操作

#### 4.5 @Around
    作用:用于指定环绕通知。
    属性:
        value:
        argNames:
            用于指定切入点表达式参数的名称。它要求和切入点表达式中的参数名称
            一致。通常不指定也可以获取切入点方法的参数内容。
    使用场景:
        环绕通知有别于前面介绍的四种通知类型。它不是指定增强方法执行时机的，而是
    spring为我们提供的一种可以通过编码的方式手动控制增强方法何时执行的机制。

### 5. 用于扩展目标类的
#### 5.1 @DeclarParents
    作用:用于给被增强的类提供新的方法。(实现新的接口)
    
    属性:
        value:用于指定目标类型的表达式。当在全限定类名后面跟上+时，表示当前类
              及其子类
    
        defaultImpl:指定提供方法或者字段的默认实现类。
    
    使用场景:
        当我们已经完成了一个项目的某个阶段开发，此时需要对已完成的某个类加入一些
    新的方法，我们首先想到的是写一个接口，然后让这些需要方法的类实现此接口，但是如果目
    标类非常复杂，牵一发而动全身，改动的话可能非常麻烦。此时就可以使用此注解，然后建一
    个代理类，同时代理该类和目标类。

#### 5.2 @EnableLoadTimeWeaving
    作用:用于切换不同场景下实现增强。
    属性:
        aspectjWeaving:是否开启LTW的支持。
            ENABLED 开启LTW
            DISABLED 不开启LTW
            AUTODETECT 如果类路径下能读取到META‐INF/aop.xml文件,则开启LTW,否则关闭 
    使用场景:
    在Java 语言中，从织入切面的方式上来看，存在三种织入方式:编译期织入、类 加载期织入和运行期织入。编译期织入是指在Java编译期，采用特殊的编译器，将切面织入 到Java类中;而类加载期织入则指通过特殊的类加载器，在类字节码加载到JVM时，织入切 面;运行期织入则是采用CGLib工具或JDK动态代理进行切面的织入。
    AspectJ提供了两种切面织入方式，第一种通过特殊编译器，在编译期，将AspectJ 语言编写的切面类织入到Java类中，可以通过一个Ant或Maven任务来完成这个操作;第二种 方式是类加载期织入，也简称为LTW(Load Time Weaving)


## Spring 中的JDBC和事务
### 1. 事务中的注解
#### 1.1 @EnableTransactionManagement
    作用:此注解是Spring支持注解事务配置的标志。表明Spring开启注解事务配置的支持。是注解驱动开发事 务配置的必备注解。

#### 1.2 @Transactional
     此注解是Spring注解配置事务的核心注解，无论是注解驱动开发还是注解和XML混合开发，只有涉及配 置事务采用注解的方式，都需要使用此注解。
  通过源码我们看到，该注解可以出现在接口上，类上和方法上。分别表明:
     接口上:当前接口的所有实现类中重写接口的方法有事务支持。
     类上:当前类中所有方法有事务支持。
     方法上:当前方法有事务的支持。
优先级: 方法上>类上>接口上。

#### 1.3 @TransactionEventListener
     它是spring在4.2版本之后加入的注解。用于配置一个事务的事件监听器。使我们在事务提交和回滚前 后可以做一些额外的功能。
     例如:对事务执行监控，执行中同步做一些操作等等。


## SpringMVC 中的注解
### 1. 基础注解
#### 1.1 @Controller
    此注解用用于修饰表现层控制器的注解

#### 1.2 @RequestMapping
    作用: 用于建立请求URL和处理请求方法之间的对应关系。
    注意: 属性只要出现2个或以上时，他们的关系是与的关系。表示必须同时满足条件。
    属性:
        name:给请求URL提供一个名称。
        value:用于指定请求的URL。它和path属性的作用是一样的
        method:用于指定请求的方式。
        params:用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和value必须和配置的一模一样。
        headers:用于指定限制请求消息头的条件。例如:RequestMapping(value = "/something", headers = "content-type=text/*")
        consumes:用于指定可以接收的请求正文类型(MIME类型)
            例如:
                consumes = "text/plain"
                consumes = {"text/plain", "application/*"}
        produces:用于指定可以生成的响应正文类型。(MIME类型)
            例如:
                produces = "text/plain"
                produces = {"text/plain", "application/*"} produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    出现位置: 
        写在类上:
            请求URL的第一级访问目录。此处不写的话，就相当于应用的根目录。 它出现的目的是为了使我们的URL可以按照模块化管理，使我们的URL更加精细: 例如:
            账户模块:
                /account/add 
                /account/update 
                /account/delete ...
        订单模块:
                /order/add 
                /order/update 
                /order/delete
        方法上: 请求UR 的第二级访问目录。

#### 1.3 @RequestParam
    此注解是从请求正文中获取请求参数，给控制器方法形参赋值的。
    当请求参数的名称和控制器方法形参变量名称一致时，无须使用此注解。
    同时，当没有获取到请求参数时，此注解还可以给控制器方法形参提供默认值。
    注意:它只能出现在方法的参数上

    属性：
        value：表示请求参数的名称
        name：与value 等价
        require：指定参数是否必须有值。当为true时，参数没有值会报错。
        defaultValue：在参数没有值时的默认值。

#### 1.4 @InitBinder
    用于初始化表单请求参数的数据绑定器。
    属性：
        value：指定给哪些参数进行绑定操作。

#### 1.5 @ControllerAdvice
    用于给控制器提供一个增强的通知。
    以保证可以在多个控制器之间实现增强共享。
    它可以配合以下三个注解来用:
    {@link exceptionhandler@exceptionhandler} * {@link initbinder@initbinder}
    {@link modeltattribute@modeltattribute}
    
    属性：
        value：用于指定对哪些包下的控制器进行增强
        BasePackages：与value 等价
        basePackageClasses：它是4.0新加入的注解，可以通过指定类的字节码的方式来指定增强作用范围。
        assignableTypes：它是4.0新加入的注解，用于指定特定的类型提供增强。
        annotations：它是4.0新加入的注解，用于指定给特定注解提供增强。

#### 1.6 @RequestHeader
    此注解是从请求消息头中获取消息头的值，并把值赋给控制器方法形参。 
    注意:它只能出现在方法的参数上
    属性：
        value：用于指定请求消息头的名称。它和name属性作用一样。
        name：与value 等价
        require：用于指定是否必须有此消息头。当取默认值时，没有此消息头会报错。
        defaultValue：指定默认值

#### 1.7 @CookieValue
    此注解是从请求消息头中获取Cookie的值，并把值赋给控制器方法形参。
    注意：只能用在方法的参数上面
    属性：与@RequestHeader 一样

#### 1.8 @ModelAttribute
    它可以用于修饰方法，或者是参数。
    当修饰方法时，表示执行控制器方法之前，被此注解修饰的方法都会执行。
    当修饰参数时，用于获取指定的数据给参数赋值。

    当注解写在方法上，则表示存入时的名称。(值是方法的返回值)
    当注解写在参数上，可以从ModelMap,Model,Map中的获取数据。(前提是之前存入过) 
    指定的是存入时的key。

#### 1.9 @SessionAttribute和@SessionAttributes
    此注解是用于让开发者和ServletAPI进行解耦。
    让开发者可以无需使用HttpSession的getAttribute方法即可从会话域中获取数据。
    属性：
        name
        value
        required

#### 1.10 @ExceptionHandler
    用于注释方法，表明当前方法是控制器执行产生异常后的处理方法

    属性：
        value：表示错误类型

### 2. JSON 交互相关注解
#### 2.1 @RequestBody
    用于获取全部的请求体

    属性：
        Required：表示是否必须有请求体

#### 2.2 @ResponseBody
    用于用流输出响应正文
    无属性

#### 2.3 @RestController
    等同于
        @ResponseBody
        @Controller

#### 2.4 @RestControllerAdvice
    它和@ControllerAdvice注解的作用一样，并且支持@ResponseBody的功能

### 3. Rest风格URL请求相关注解
#### 3.1 @PathVariable
    它是springmvc框架支持rest风格url的标识。 
    它可以用于获取请求url映射中占位符对应的值。
    
    属性：
        value：指定url映射中占位符的名称
        name：value
        required：它是4.3.3版本新加入的属性，用于指定是否必须有此占位符。当取默认值时，没有会报错。 

### 4. 跨域访问
#### 4.1 @CrossOrigin
    此注解用于指定是否支持跨域访问