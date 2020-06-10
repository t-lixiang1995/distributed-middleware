package com.pcitc.sharding.service.impl;

import com.pcitc.sharding.mapper.OrderItemMapper;
import com.pcitc.sharding.mapper.OrderMapper;
import com.pcitc.sharding.model.Order;
import com.pcitc.sharding.model.OrderItem;
import com.pcitc.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void save(Order order, OrderItem item) {
        orderMapper.save(order);
        orderItemMapper.save(item);
    }

    @Override
    public List<Order> findHint() {
        return orderMapper.selectHint();
    }
}
