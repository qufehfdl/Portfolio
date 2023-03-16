package com.hrilke.project.controller.concurrent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hrilke.project.beans.concurrent.ConcurrentTestBean;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ThreadLocalController {

	private final ThreadLocal<ConcurrentTestBean> threadLocal;
	// threadLocal은 스레드에 고유한 값이며 한 스레드에서 값을 설정하면 다른 스레드에서는 같은 값을 가져올 수 없다!!

	@GetMapping("/myThreadLocal/{content}")
	public String myThreadLocal(@PathVariable String content) {

		threadLocal.set(new ConcurrentTestBean());
		ConcurrentTestBean concurrentTestBean = threadLocal.get();
		concurrentTestBean.setStr(content);

		// 메모리 누수 방지
		threadLocal.remove();

		// 2초간 정지
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return concurrentTestBean.getStr();
	}
}
