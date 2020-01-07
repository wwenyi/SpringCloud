package com.wwy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@MapperScan({"com.wwy.test.mybatisplustest.dao","com.wwy.test.**.mapper"})
public class springbootrun {
public static void main(String[] args) {
	SpringApplication.run(springbootrun.class, args);
}
}
