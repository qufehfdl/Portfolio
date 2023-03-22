package com.hrilke.project.service.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

	@Async("myExecutor")
	public void test1() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("test1 success" + Thread.currentThread().getName());
	}

	@Async("myExecutor")
	public void test2() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("test2 success" + Thread.currentThread().getName());
	}

}
