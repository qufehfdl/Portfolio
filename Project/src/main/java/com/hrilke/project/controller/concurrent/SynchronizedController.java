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

		// 1초간 슬립
		MySleep.mySleep();

		return count;
	}

//	------------------ 데드락 공부 ------------------

	// 락을 걸 키 설정
	// 두번째 요청이 와도 이 객체를 공유
	private Object key = new Object();

	public void method() {
		// synchronized 블럭은 해당 키를 획득한 스레드만이 실행하고
		// 다른 스레드는 키를 얻을 때 까지 기다림
		synchronized (key) {
		}
	}

	Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
			method();
		}
	});

	@GetMapping("DeadLock")
	public String DeadLock() {

		// 두번째 요청부터는 첫번째 요청한 스레드가 아직 키를 가지고있기 때문에 데드락 발생!
		thread.start();

		return "ok";
	}
}
