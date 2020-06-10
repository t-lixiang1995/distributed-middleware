package com.pcitc.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 
 * @author lixiang
 * @createTime 2018年11月25日 下午3:13:39
 * 生产者：用于向RabbitMQ发送消息 
 */
public class RabbitMQProduct {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory(); //创建连接工厂
		//配置
		factory.setHost("192.168.110.130");
		factory.setPort(5672);
		factory.setUsername("rabbitstudy");
		factory.setPassword("123456");
		Connection connection = factory.newConnection(); //创建连接
		Channel channel = connection.createChannel(); //创建通道
		channel.exchangeDeclare("demo.exchange", "direct"); //创建交换器
		channel.queueDeclare("demo.queue", true, false, false, null); //创建队列
		channel.queueBind("demo.queue", "demo.exchange", "com.pcitc.semb");
		
		String message="抽纸一车";
		//向RabbitMQ发送一条消息
		channel.basicPublish("demo.exchange", "com.pcitc.semb", 
				MessageProperties.PERSISTENT_TEXT_PLAIN, 
				message.getBytes());
		
		//关闭资源
		channel.close();
		connection.close();
	}
}
