package com.hrilke.project.service.concurrent;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

	// 🔒동기식과 비동기식 : 결과에 관심 (호출된 함수의 종료를 호출한 함수가 처리하는지 호출된 함수가 처리하는지!)
	
	// 동기   : 어떤 메서드를 호출하면 그 결과를 기다리는 것
	//		- 호출한 함수가 결과에 관심을 가짐
	//		- 다음 할 일이 결과가 필요한 것일 때 사용 ex) 입출금...(출금에 성공해야 다른사람에게 입금 함)
	
	// 비동기 : 어떤 메서드를 호출하면 결과를 기다리지 않고 다른일을 할 수 있고 콜백함수로 결과를 받아 처리하는것
	//		- 호출된 함수(callback)가 결과에 관심을 가짐
	//		- 결과값을 간접적으로 받음

	// 🔒Blocking 과 Non-Blocking : 제어권에 관심
	
	// 		호출된 함수가 자신의 작업을 모두 마칠 때까지
	// 		호출한 함수에게 제어권을 넘겨주지 않고 대기하게 만든다면 blocking

	// 		호출된 함수가 작업의 완료 여부와는 무관하게 바로 응답해서 호출한 함수에게 제어권을 넘겨주고
	// 		호출한 함수가 다른 일을 할 수 있는 기회를 줄 수 있으면 non-blocking
	
// 	🚩Blocking + Synchronous      : 결과가 처리되기까지 기다린다음 결과를 가지고 바로 다음 일 실행
// 	  Blocking + ASynchronous     : 결과가 처리되기까지 기다린다음 결과를 호출된 함수가 처리
// 	  Non-Blocking + Synchronous  : 결과가 처리되기까지 기다리지않고 다른일을 하지만 계속 결과에 신경씀
// 				        결과가 나오면 나온 결과로 바로 다음 일 실행
// 	🚩Non-Blocking + ASynchronous : 결과가 처리되기까지 기다리지않고 다른일을 하다 결과를 호출된 함수가 처리
	
	@Async("myExecutor")
	public void test1() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("test1 success" + Thread.currentThread().getName());
	}

	@Async("myExecutor")
	public void test2() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("test2 success" + Thread.currentThread().getName());
	}

}
