package com.wwy.test.rabbittest.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wwy.config.RabbitMqConfig;
import com.wwy.entry.APIEntry;
import com.wwy.entry.Order;
import lombok.extern.slf4j.Slf4j;
/**
 * rabbitMq发送端
 * @author wwy
 * @date 2019年12月11日
 * @version v0.0.1
 *
 */
@Service
@Slf4j
public class RabbitService {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	/**
	 * 发送消息
	 * @param order
	 * @return
	 */
	public APIEntry send(Order order) {
		log.info("开始发送消息");
		try {
			rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME_2,order);
		}catch(Exception e) {
			log.error("发送失败"+e);
			return APIEntry.ERROR("发送失败");
		}
		log.info("发送成功");
		return APIEntry.OK(null);
	}

}
