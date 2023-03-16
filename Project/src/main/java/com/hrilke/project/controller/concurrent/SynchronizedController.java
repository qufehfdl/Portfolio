package com.hrilke.project.controller.concurrent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SynchronizedController {
	int count;

//	synchronized를 사용
//		최소한으로 사용해야함 : 성능 저하
//		데드락 상황이 발생될 수 있으므로 공유자원에 대한 락 획득하는 순서를 일관성있게 유지
	@GetMapping("/synchronized")
	public synchronized int sync() {
		count++;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}

}
