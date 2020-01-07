package com.wwy.test.timing;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
/**
 * 创建定时任务的demo
 * @author wwy
 * @date 2019年12月11日
 * @version v0.0.1
 *
 */
//@EnableAsync
//@Component
public class Timing {
	
	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor e=new ThreadPoolTaskExecutor();
		e.setCorePoolSize(5);
		e.setMaxPoolSize(20);
		e.setQueueCapacity(3);
		e.initialize();
		return e;
	}
	
	/**
	 * 使用jdk自带的Timer实现定时任务(单线程串行执行)
	 */
	@Bean
public Timer timer() {
	//创建TimerTask的匿名内部类，重写抽象方法，run方法中定义具体执行的任务		
	TimerTask t=new TimerTask() {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"使用timer实现的定时任务1");
		}
	};
	TimerTask t2=new TimerTask() {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"使用timer实现的定时任务2");
		}
	};
	//创建timer对象，（执行定时任务的对象）
	Timer timer=new Timer();
	//在1秒之后开始每5秒执行一次t中run方法的任务
	timer.schedule(t,1000,5000);
	timer.schedule(t2,1000,5000);
	System.out.println("______");
	return timer;
}
	/**
	 * 使用jdk自带的ScheduledExecutorService执行定时任务
	 * @return
	 */
@Bean
public ScheduledExecutorService ses() {
	//单线程串行执行定时任务，我们也可以使用newScheduledThreadPool（）方法指定线程工厂对象和线程数，使定时任务在
	//多线程并行执行
	ScheduledExecutorService s=Executors.newSingleThreadScheduledExecutor();
	//执行定时任务，1秒后每隔5秒执行一个任务
	s.scheduleAtFixedRate(
			//jdk1.8的新特性，lambda表达式，可以看做是runnable的一个匿名内部类对象
			()->System.out.println(Thread.currentThread().getName()+"使用ScheduledExecutorService执行定时任务1")
			,1, 5, 
			//单位
			TimeUnit.SECONDS);
	s.scheduleAtFixedRate(
			//jdk1.8的新特性，lambda表达式，可以看做是runnable的一个匿名内部类对象
			()->System.out.println(Thread.currentThread().getName()+"使用ScheduledExecutorService执行定时任务2")
			,1, 5, 
			//单位
			TimeUnit.SECONDS);
	return s;
}
/**
 * 使用spring自带的定时任务执行
 */
//单线程串行执行线程任务，如果需要在多线程并行执行则需要配置线程池
@Scheduled(fixedDelay = 1000*60*3)
@Async
public void ss() {
	System.out.println(Thread.currentThread().getName()+"使用spring自带的定时任务执行1:3分钟");
}
@Scheduled(fixedDelay = 5000)
@Async
public void ss2() {
	System.out.println(Thread.currentThread().getName()+"使用spring自带的定时任务执行2");
}
}
