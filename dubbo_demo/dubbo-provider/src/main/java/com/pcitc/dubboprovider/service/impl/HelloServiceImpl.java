package com.pcitc.dubboprovider.service.impl;

import com.pcitc.dubboprovider.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

import java.io.Serializable;

/**
 * @author lixiang
 * @date 2020/4/15 11:27
 * @desc
 */
@Service
public class HelloServiceImpl implements DemoService, Serializable {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
