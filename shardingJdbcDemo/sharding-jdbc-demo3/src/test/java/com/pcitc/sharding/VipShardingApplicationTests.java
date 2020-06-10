package com.pcitc.sharding;

import com.pcitc.sharding.model.User;
import com.pcitc.sharding.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VipShardingApplication.class)
public class VipShardingApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("wangwu");
        userService.save(user);
    }

}

