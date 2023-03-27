package com.hrilke.project.controller.concurrent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hrilke.project.beans.concurrent.ConcurrentTestBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AtomicInstanceController {

	private final AtomicReference<ConcurrentTestBean> atomicReference;

//  여러 스레드 요청이 오게 되면 ConcurrentTestBean 가 공유되어 동시성 문제 발생
//	private final ConcurrentTestBean concurrentTestBean;

	@GetMapping("/myAtomic/{str}")
	public String myAtomic(@PathVariable String str) {

		ConcurrentTestBean concurrentTestBean = atomicReference.get();

		if (concurrentTestBean == null) {
			concurrentTestBean = new ConcurrentTestBean();

			// 여러 스레드 요청이 오더라도 ConcurrentTestBean 가 공유되지는 않지만
			// 33번 라인의 객체 생성 부분이 동시성 문제가 발생하는 부분!
//			atomicReference.set(concurrentTestBean);

			// 첫번째 변수 : 기대하는 값
			// 두번째 변수 : 새로운 값
			// 현재 값이 첫번째 변수와 같으면 두번째 변수의 값으로 변경되고 true 반환!
			boolean result = atomicReference.compareAndSet(concurrentTestBean, concurrentTestBean);
			log.info("결과 : {}", result);
		}

		log.info("입력 될 값 :------------------- {}", str);
		concurrentTestBean.setStr(str);

		// 1초간 스레드 정지
		mySleep();

		log.info("입력 된 값 : {}", concurrentTestBean.getStr());
		return concurrentTestBean.getStr();
	}

	@GetMapping("/atomic")
	public String myAtomic() {
	    Thread t4 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest4("/Project/myAtomic/~~~~~~");
	        }
	    });
	    Thread t1 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest1("/Project/myAtomic/qqqqqq");
	        }
	    });
	    Thread t2 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest2("/Project/myAtomic/123456");
	        }
	    });
	    Thread t3 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest3("/Project/myAtomic/xxxxxx");
	        }
	    });
	    Thread t5 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            myRequest5("/Project/myAtomic/oooooo");
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
