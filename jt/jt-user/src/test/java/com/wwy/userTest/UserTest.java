package com.wwy.userTest;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import redis.clients.jedis.Jedis;
//@RunWith(SpringRunner.class)
//@SpringBootTest
@SuppressWarnings("unused")
public class UserTest {
//	@Autowired
//	Jedis jedis;
@Test
public void RedisTest() {
	String f="aaa.txt";
	String suName=f.substring(f.indexOf("."));
	System.out.println(suName);
	String Date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	System.out.println(Date);
}
}
