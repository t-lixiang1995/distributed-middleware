package com.pcitc.demo.service;


import com.pcitc.demo.entity.UserInfo;
import com.pcitc.demo.mapper.UserInfoMapper;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService {

    @Resource
    UserInfoMapper userInfoMapper;

    /**
     * 保存用户
     */
    public void saveUser(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    /**
     * 查询用户
     * @param userId
     */
    public UserInfo queryUser(Long userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }
}
