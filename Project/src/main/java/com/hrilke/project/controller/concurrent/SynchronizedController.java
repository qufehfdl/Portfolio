package com.hrilke.project.controller.concurrent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
public class SynchronizedController {

	int count;

	@GetMapping("/synchronized")
//	synchronized를 사용해서 동시성 문제 해결
//	성능 저하
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
