package com.pcitc.sharding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.sharding.entity.Student;
import com.pcitc.sharding.entity.User;
import com.pcitc.sharding.mapper.StudentMapper;
import com.pcitc.sharding.mapper.UserMapper;
import com.pcitc.sharding.service.UserService;

@Service  
@Transactional  
public class UserServiceImpl implements UserService {  
  
    @Autowired  
    public UserMapper userMapper;  
      
    @Autowired  
    public StudentMapper studentMapper;  
  
    public boolean insert(User u) {  
        return userMapper.insert(u) > 0 ? true :false;  
    }  
  
    public List<User> findAll() {  
        return userMapper.findAll();  
    }  
  
    public List<User> findByUserIds(List<Integer> ids) {  
        return userMapper.findByUserIds(ids);  
    }  
  
    @Transactional(propagation=Propagation.REQUIRED)  
    public void transactionTestSucess() {  
        User u = new User();  
        u.setUserId(13);  
        u.setAge(25);  
        u.setName("war3 1.27");  
        userMapper.insert(u);  
          
        Student student = new Student();  
        student.setStudentId(21);  
        student.setAge(21);  
        student.setName("hehe");  
        studentMapper.insert(student);  
    }  
  
    @Transactional(propagation=Propagation.REQUIRED)  
    public void transactionTestFailure() throws IllegalAccessException {  
        User u = new User();  
        u.setUserId(13);  
        u.setAge(25);  
        u.setName("war3 1.27 good");  
        userMapper.insert(u);  
          
        Student student = new Student();  
        student.setStudentId(21);  
        student.setAge(21);  
        student.setName("hehe1");  
        studentMapper.insert(student);  
        throw new IllegalAccessException();  
    }  
      
} 