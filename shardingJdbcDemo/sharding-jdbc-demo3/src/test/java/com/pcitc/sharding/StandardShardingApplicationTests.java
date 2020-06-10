package com.pcitc.sharding;

import com.pcitc.sharding.model.ItemGenerator;
import com.pcitc.sharding.model.Order;
import com.pcitc.sharding.model.OrderGenerator;
import com.pcitc.sharding.model.OrderItem;
import com.pcitc.sharding.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VipShardingApplication.class)
public class StandardShardingApplicationTests {

    @Autowired
    OrderService orderService;

    @Test
    public void contextLoads() {
        Order order = OrderGenerator.generate();
        order.setUserId(10000000);
        order.setOrderId(1000000);
        OrderItem orderItem = ItemGenerator.generate();
        orderItem.setUserId(order.getUserId());
        orderItem.setOrderId(order.getOrderId());
        orderService.save(order, orderItem);
    }

}

