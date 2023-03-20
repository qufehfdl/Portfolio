package com.hrilke.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	// Tomcat8 부터는 non-blocking [다수의 커넥션에 1개의 쓰레드]
	// blocking [1커넥션에 1개의 쓰레드]
	// ThreadPool : Thread를 허용된 개수 안에서 사용하도록 제한
	@Bean
	public ThreadPoolTaskExecutor myExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		// 기본 쓰레드 개수 : 작업이 없다면 기본적으로 가지는 개수
		executor.setCorePoolSize(100);
		
		// 최대로 가질 수 있는 쓰레드 개수 : 항상 이 개수를 유지하는것은 아님
		executor.setMaxPoolSize(200);

		// 작업 Queue 사이즈 : 대기중인 작업수 제한
		executor.setQueueCapacity(9999);

		// 생성되는 쓰레드의 접두어
		executor.setThreadNamePrefix("taehwa-");
		
		executor.initialize();
		return executor;
	}
}
