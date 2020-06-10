package com.pcitc.sharding.service;

import com.pcitc.sharding.model.Order;
import com.pcitc.sharding.model.OrderItem;

import java.util.List;

public interface OrderService {

    public List<Order> findHint();

    public void save(Order order, OrderItem item);

}
