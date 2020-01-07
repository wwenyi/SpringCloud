package com.wwy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMq配置类
 * 
 * @author wwy
 * @date 2019年12月9日
 * @version v0.0.1
 *
 */
@Configuration
public class RabbitMqConfig {
	// 队列名
	public static final String QUEUE_NAME = "order-send";
	public static final String QUEUE_NAME_2 = "order-send-2";
	// 交换机名
	public static final String QUEUE_EXCHANGE_NAME = "order-send-exchange";
	/**
	 * 创建队列1
	 * 
	 * @return
	 */

	@Bean 
	public Queue queue() { 
		// 是否持久化 
		boolean durable = true; 
		//仅创建者可以使用的私有队列，断开后自动删除 
		boolean exclusive = false; 
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false; 
		return new Queue(QUEUE_NAME, durable, exclusive,autoDelete); 
	}
	/**
	 * 创建队列2
	 * 
	 * @return
	 */
	@Bean("queue2") 
	public Queue queue2() { 
		// 是否持久化 
		boolean durable = true; 
		//仅创建者可以使用的私有队列，断开后自动删除 
		boolean exclusive = false; 
		// 当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false; 
		return new Queue(QUEUE_NAME_2, durable, exclusive,autoDelete); 
	}
	/**
	 *  创建交换机
	 * 
	 * @return
	 */

	@Bean 
	public TopicExchange exchange() { 
		// 是否持久化 
		boolean durable = true; 
		//当所有消费客户端连接断开后，是否自动删除队列
		boolean autoDelete = false; 
		return new TopicExchange(QUEUE_EXCHANGE_NAME, durable, autoDelete); 
	}
	/**
	 * 声明路由和队列1的绑定关系
	 * 
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean 
	public Binding binding(Queue queue, TopicExchange exchange) { 
		return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME); 
	}
	/**
	 * 声明路由和队列2的绑定关系
	 * 
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean 
	public Binding binding2(@Qualifier("queue2") Queue queue2, TopicExchange exchange) { 
		return BindingBuilder.bind(queue2).to(exchange).with(QUEUE_NAME_2); 
	} 
}
