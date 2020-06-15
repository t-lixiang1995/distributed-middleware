package com.pcitc.dubboconsumer.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lixiang
 * @date 2020/4/15 14:34
 * @desc
 */

@Configuration
@EnableDubbo(scanBasePackages = "com.pcitc.dubboconsumer.action")
@PropertySource("classpath:/spring/dubbo-consumer.properties")
@ComponentScan(value = {"com.pcitc.dubboconsumer.action"})
 public class ConsumerConfiguration {

}