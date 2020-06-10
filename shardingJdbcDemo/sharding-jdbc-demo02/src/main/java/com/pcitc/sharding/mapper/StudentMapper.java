package com.pcitc.sharding.mapper;

import java.util.List;

import com.pcitc.sharding.entity.Student;

/**
 * 处理学生的数据操作接口
 * @author pcitc
 *
 */
public interface StudentMapper {  
      
    Integer insert(Student s);  
      
    List<Student> findAll();  
      
    List<Student> findByStudentIds(List<Integer> studentIds);  
 
} 