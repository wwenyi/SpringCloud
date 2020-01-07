package com.wwy.test.rabbittest.consume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.wwy.config.RabbitMqConfig;
import com.wwy.entry.Order;
import lombok.extern.slf4j.Slf4j;
/**
 * rabbitMq接收端
 * @author wwy
 * @date 2019年12月11日
 * @version v0.0.1
 *
 */
@Slf4j
@Service
public class RabbitMqServcie {
	/**
	 * 监听队列1消息
	 * @param message
	 */
	@RabbitListener(queues=RabbitMqConfig.QUEUE_NAME)
public void re(Order message) {
		log.info("队列1接收成功");
		log.info(message.toString());
}
	/**
	 * 监听队列2消息
	 * @param message
	 */
	@RabbitListener(queues=RabbitMqConfig.QUEUE_NAME_2)
public void re2(Order message) {
		log.info("队列2接收成功");
		log.info(message.toString());
}	
	

}
