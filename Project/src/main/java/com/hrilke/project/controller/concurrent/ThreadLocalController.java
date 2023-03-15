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

	@GetMapping("/myThreadLocal/{content}")
	public String myThreadLocal(@PathVariable String content) {

		ConcurrentTestBean concurrentTestBean = threadLocal.get();
		if (concurrentTestBean == null) {
			concurrentTestBean = new ConcurrentTestBean();
			threadLocal.set(concurrentTestBean);
		}
		concurrentTestBean.setData(content);
		threadLocal.remove();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return concurrentTestBean.getData();
	}
}
