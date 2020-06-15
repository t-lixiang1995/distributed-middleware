package com.pcitc.dubboconsumer;

import com.pcitc.dubboconsumer.action.AnnotationAction;
import com.pcitc.dubboconsumer.config.ConsumerConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) throws Exception{

        SpringApplication.run(DubboConsumerApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        String hello = annotationAction.doSayHello("world");
        System.out.println(hello);
    }

}

