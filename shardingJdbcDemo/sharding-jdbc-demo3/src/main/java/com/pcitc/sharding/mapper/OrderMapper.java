package com.pcitc.sharding.mapper;

import com.pcitc.sharding.model.Order;

import java.util.List;

public interface OrderMapper {
    public int save(Order order);

    public List<Order> selectHint();
}
