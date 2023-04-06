package com.hrilke.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.hrilke.project.service.concurrent.AsyncService;

@Configuration
public class ThreadPoolConfig {

	// ThreadPool : Thread를 허용된 개수 안에서 사용하도록 제한
	// ThreadPoolTaskExecutor은 기본적으로 논블로킹 방식으로 동작함!
	// 적절한 설정으로 작업 처리 성능을 최적화할 수 있다!
	@Bean(name = "myExecutor")
	public ThreadPoolTaskExecutor myExecutor() {
		
		// 작업를 비동기적으로 실행하기 위해 스레드 풀을 관리하는 클래스 객체 생성
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

	@Bean
	public AsyncService asyncService() {
		return new AsyncService();
	}

}
