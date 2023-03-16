package com.hrilke.project.controller.concurrent;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hrilke.project.beans.concurrent.ConcurrentTestBean;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AtomicInstanceController {
	private final AtomicReference<ConcurrentTestBean> atomicReference;

	@GetMapping("/myAtomic/{str}")
	public String myAtomic(@PathVariable String str) {
		atomicReference.set(new ConcurrentTestBean());
		ConcurrentTestBean concurrentTestBean = atomicReference.get();
		concurrentTestBean.setStr(str);

		// 2초간 정지
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return concurrentTestBean.getStr();
	}
}
