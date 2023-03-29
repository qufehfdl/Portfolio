package com.hrilke.project.controller.concurrent;

import java.util.concurrent.locks.ReentrantLock;

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
	Object key1 = new Object();
	Object key2 = new Object();

	Thread thread1 = new Thread(new Runnable() {
		public void run() {
			synchronized (key1) {
				// thread1가 key1 획득

				MySleep.mySleep(); // 1초 대기

				// thread1가 key2 기다림
				synchronized (key2) {
					// 실행되지 않음
				}
			}
		}
	});

	Thread thread2 = new Thread(new Runnable() {
		public void run() {
			synchronized (key2) {
				// thread2가 key2 획득

				MySleep.mySleep(); // 1초 대기

				// thread2가 key1 기다림
				synchronized (key1) {
					// 실행되지 않음
				}
			}
		}
	});

	@GetMapping("DeadLock")
	public String DeadLock() {
		thread1.start();
		thread2.start();
		return "ok";
	}

	// 해결방법 - 데드락 상황이 발생하지 않게 key얻는 순서를 일관되게 유지

//	Thread thread1 = new Thread(new Runnable() {
//		public void run() {
//			synchronized (key1) {
//
//				MySleep.mySleep();
//
//				synchronized (key2) {
//				}
//			}
//		}
//	});
//
//	Thread thread2 = new Thread(new Runnable() { 
//		public void run() {
//			synchronized (key1) {
//				MySleep.mySleep();
//				synchronized (key2) {
//				}
//			}
//		}
//	});
}
