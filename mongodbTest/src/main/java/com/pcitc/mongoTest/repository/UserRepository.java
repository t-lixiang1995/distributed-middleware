package com.pcitc.mongoTest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcitc.mongoTest.entity.User;

/**
 * 类似JPA
 * 
 * 定义基础CRUD操作DAO
 */
public interface UserRepository extends MongoRepository<User, String> {

	User findByName(String name);
}
