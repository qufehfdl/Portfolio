package com.hrilke.project.controller.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrilke.project.service.concurrent.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AsyncController {

	private final AsyncService asyncService;

	@GetMapping("/async")
	public String myAsync() {
		log.info("시작");

		// SimpleAsyncTaskExecutor에서 가져온 쓰레드
		log.info("쓰레드 이름 : " + Thread.currentThread().getName());

		// 서로 다른 쓰레드에서 실행된다
		asyncService.test1(); // Thread.sleep(3000)
		asyncService.test2(); // Thread.sleep(1000)

		log.info("끝"); // test1() , test2() 보다 먼저 콘솔에 찍힘
		return "ok";
		
//		콘솔 결과
//		1. 시작
//		2. SimpleAsyncTaskExecutor에서 가져온 쓰레드 이름
//		3. 끝
//		4. test2() 실행 : 내가 정의한 ThreadPool에서 가져온 쓰레드 이름
//		5. test1() 실행 : 내가 정의한 ThreadPool에서 가져온 쓰레드 이름
	}
}
