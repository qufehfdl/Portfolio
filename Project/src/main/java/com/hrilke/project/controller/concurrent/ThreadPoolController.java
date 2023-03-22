package com.hrilke.project.controller.concurrent;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ThreadPoolController {
	private final ThreadPoolTaskExecutor myExecutor;

	// ThreadPool기반의 비동기 처리
	// 작업이 끝날 때마다 해당 스레드는 풀로 반환
	@GetMapping("/ThreadPool")
	public String threadPool() {
		Long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {

			// 스레드 풀의 worker 스레드에게 할당
			myExecutor.execute(new Runnable() {
				@Override
				public void run() {
					proccess(); // 스레드 풀에서 할당된 worker 스레드에 의해 병렬적으로 실행됨!
				}
			});
		}
		Long end = System.currentTimeMillis();
		log.info("실행 시간 : {}", end - start); // 2~10 밀리 세컨드
		return "ok";
	}

	@GetMapping("/NoThreadPool")
	public String noThreadPool() throws InterruptedException {
		Long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			proccess();
		}
		Long end = System.currentTimeMillis();
		log.info("실행 시간 : {}", end - start); // 2600~2900 밀리 세컨드
		return "ok";
	}

	private void proccess() {
		int count = 0;
		for (int i = 0; i < 1000000; i++) {
			count = count + i;
		}
	}

}