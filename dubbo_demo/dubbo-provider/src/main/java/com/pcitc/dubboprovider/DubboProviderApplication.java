package com.pcitc.dubboprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication
@Configuration
@EnableDubbo(scanBasePackages = "com.pcitc.dubboprovider.service.impl")
@PropertySource("classpath:/spring/dubbo-provider.properties")
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
