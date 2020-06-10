package com.pcitc.sharding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.sharding.entity.Student;
import com.pcitc.sharding.mapper.StudentMapper;
import com.pcitc.sharding.service.StudentService;

@Service  
public class StudentServiceImpl implements StudentService{  
      
    @Autowired  
    public StudentMapper studentMapper;  
  
    public boolean insert(Student student) {  
        return studentMapper.insert(student) > 0 ? true : false;  
    }  
  
} 