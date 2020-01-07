package com.wwy.test.rabbittest.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wwy.entry.APIEntry;
import com.wwy.entry.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * rabbitMq测试接口
 * @author wwy
 * @date 2019年12月11日
 * @version v0.0.1
 *
 */
@Api(tags = "rabbit测试")
@RestController
@RequestMapping("rabbit")
public class RabbitController {
	@Autowired
	private RabbitService rabbitService;
	@RequestMapping("send")
	@ApiOperation("发送消息")
public APIEntry send(Order order) {
		return rabbitService.send(order);
}
}
