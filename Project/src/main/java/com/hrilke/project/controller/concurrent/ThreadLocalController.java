package com.hrilke.project.controller.concurrent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hrilke.project.beans.concurrent.ConcurrentTestBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ThreadLocalController {

// 	threadLocal은 스레드에 고유한 값이며 한 스레드에서 값을 설정하면 다른 스레드에서는 같은 값을 가져올 수 없다!!
//	공유 객체를 스레드별로 분리해서 사용하자!
	private final ThreadLocal<ConcurrentTestBean> threadLocal;

//  여러 스레드 요청이 오게 되면 ConcurrentTestBean 가 공유되어 동시성 문제 발생
//	private final ConcurrentTestBean concurrentTestBean; 동시성 문제 발생!

	@GetMapping("/myThreadLocal/{content}")
	public String test(@PathVariable String content) {
		ConcurrentTestBean concurrentTestBean = threadLocal.get();
		// 불필요한 객체 생성을 방지하자!
		if (concurrentTestBean == null) {
			concurrentTestBean = new ConcurrentTestBean();
			threadLocal.set(concurrentTestBean);
		}

		log.info("입력 될 값 :------------------- {}", content);
		concurrentTestBean.setStr(content);

		// 메모리 누수 방지
		threadLocal.remove();

		// 1초간 스레드 정지
		mySleep();

		log.info("입력 된 값 : {}", concurrentTestBean.getStr());
		return concurrentTestBean.getStr();
	}

	@GetMapping("/local")
	public String myThreadLocal() {
	    Thread t4 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest4("/Project/myThreadLocal/~~~~~~");
	        }
	    });
	    Thread t1 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest1("/Project/myThreadLocal/qqqqqq");
	        }
	    });
	    Thread t2 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest2("/Project/myThreadLocal/123456");
	        }
	    });
	    Thread t3 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest3("/Project/myThreadLocal/xxxxxx");
	        }
	    });
	    Thread t5 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest5("/Project/myThreadLocal/oooooo");
	        }
	    });
	    t1.start();
	    t2.start();
	    t3.start();
	    t4.start();
	    t5.start();
	    return "ok";
	}

	private void myRequest1(String myURL) {
		try {
			URL url = new URL("http://localhost:8080" + myURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void myRequest2(String myURL) {
		try {
			URL url = new URL("http://localhost:8080" + myURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void myRequest3(String myURL) {
		try {
			URL url = new URL("http://localhost:8080" + myURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void myRequest5(String myURL) {
		try {
			URL url = new URL("http://localhost:8080" + myURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void myRequest4(String myURL) {
		try {
			URL url = new URL("http://localhost:8080" + myURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void mySleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
