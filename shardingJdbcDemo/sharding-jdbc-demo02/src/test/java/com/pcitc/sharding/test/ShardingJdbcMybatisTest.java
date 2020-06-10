package com.pcitc.sharding.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcitc.sharding.entity.Student;
import com.pcitc.sharding.entity.User;
import com.pcitc.sharding.service.StudentService;
import com.pcitc.sharding.service.UserService;

/**
 * 测试分库分表规则
 * @author pcitc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:config/spring/spring-database.xml", "classpath*:config/spring/spring-sharding.xml" })  
public class ShardingJdbcMybatisTest {  
 
    @Autowired  
    public UserService userService;  
      
    @Autowired  
    public StudentService studentService;  
 
    @Test  
    public void testUserInsert() {  
        User u = new User();  
        u.setUserId(11);  
        u.setAge(25);  
        u.setName("github");  
        Assert.assertEquals(userService.insert(u), true);  
    }  
      
    @Test  
    public void testStudentInsert() {  
        Student student = new Student();  
        student.setStudentId(21);  
        student.setAge(21);  
        student.setName("hehe");  
        Assert.assertEquals(studentService.insert(student), true);  
    }  
 
    @Test  
    public void testFindAll(){  
        List<User> users = userService.findAll();  
        if(null != users && !users.isEmpty()){  
            for(User u :users){  
                System.out.println(u);  
            }  
        }  
    }  
      
    @Test  
    public void testSQLIN(){  
        List<User> users = userService.findByUserIds(Arrays.asList(1));  
        if(null != users && !users.isEmpty()){  
            for(User u :users){  
                System.out.println(u);  
            }  
        }  
    }  
      
    @Test  
    public void testTransactionTestSucess(){  
        userService.transactionTestSucess();  
    }  
      
    @Test(expected = IllegalAccessException.class)  
    public void testTransactionTestFailure() throws IllegalAccessException{  
        userService.transactionTestFailure();  
    }  
} 