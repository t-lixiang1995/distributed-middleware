package com.pcitc.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pcitc.sharding.mapper")
public class VipShardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VipShardingApplication.class, args);
	}

}

