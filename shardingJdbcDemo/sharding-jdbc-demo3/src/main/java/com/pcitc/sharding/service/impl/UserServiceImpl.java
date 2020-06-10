package com.pcitc.sharding.service.impl;

import com.pcitc.sharding.mapper.UserMapper;
import com.pcitc.sharding.model.User;
import com.pcitc.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }
}
