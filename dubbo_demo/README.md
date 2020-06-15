# Dubbo实例

## Dubbo有四种配置方式：
###1.注解配置：(需要 2.6.3 及以上版本支持)<br>  

服务提供方:<br>
Service注解暴露服务
#####
    @Service
    public class DemoServiceImpl implements DemoService {
        @Override
        public String sayHello(String name) {
            return "hello, " + name;
        }
    }
增加应用共享配置<br>
#####
    # dubbo-provider.properties
    dubbo.application.name=dubbo-provider
    dubbo.registry.address=zookeeper://127.0.0.1:2181
    dubbo.protocol.name=dubbo
    dubbo.protocol.port=20880
指定Spring扫描路径<br>
#####
    @Configuration
    @EnableDubbo(scanBasePackages = "com.pcitc.dubboprovider.service.impl")
    @PropertySource("classpath:/spring/dubbo-provider.properties")
    public class DubboProviderApplication {
           
    }
服务消费方:<br>
Reference注解引用服务
#####
    @Component("annotationAction")
    public class AnnotationAction {
    
        @Reference
        private DemoService demoService;
        
        public String doSayHello(String name) {
            return demoService.sayHello(name);
        }
    }

增加应用共享配置
#####
    # dubbo-consumer.properties
    dubbo.application.name=dubbo-consumer
    dubbo.registry.address=zookeeper://127.0.0.1:2181
    dubbo.consumer.timeout=3000
指定Spring扫描路径
#####
    @Configuration
    @EnableDubbo(scanBasePackages = "com.pcitc.dubboconsumer.action")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"com.pcitc.dubboconsumer.action"})
     public class ConsumerConfiguration {
    
    }
调用服务
#####
    public static void main(String[] args) throws Exception{

        SpringApplication.run(DubboConsumerApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        String hello = annotationAction.doSayHello("world");
        System.out.println(hello);
    }