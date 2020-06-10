RabbitMQ应用实例
=================================
#各种功能属性测试
* 推模式和拉模式消费消息以及拒绝消息
* 非持久化交换器丶自动删除交换器丶内置交换器的创建，检测交换器是否存在，删除交换器
* Direct类型交换器丶fanout类型交换器丶Topic类型交换器的消息路由
* 创建排他队列和死信队列丶检测队列是否存在丶删除队列丶清空队列
* 消息过期时间TTL丶备份交换器丶mandatory（如果exchange根据自身类型和消息routeKey无法找到一个符合条件的queue，那么会调用basic.return方法将消息返回给生产者）
* 事务机制丶发送方确认机制（逐条确认，批量确认，异步确认）

#应用实例
* rabbitmq实现RPC