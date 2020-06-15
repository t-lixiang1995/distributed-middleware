package com.pcitc.dubboconsumer.action;

import com.pcitc.dubboprovider.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lixiang
 * @date 2020/4/15 11:31
 * @desc
 */
@Component("annotationAction")
public class AnnotationAction implements Serializable {

    @Reference
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }
}
