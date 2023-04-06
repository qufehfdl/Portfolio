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

//    Atomic 클래스는 멀테스레드 환경에서 동기화 없이 안전하게 변수를 업데이트하게 해준다 [원자성을 보장해 줌] 
//    내부적으로 비교-교환 연산(CAS)를 사용해서 현재값과 변경하려는 값이 같은지 비교 후 같으면 새로운 값으로 변경하고
//    다르면 아무 일도 하지 않음 이 과정이 모든 스레드에서 일관적으로 수행된다!
//    여러 스레드가 동시에 변수에 접근할 때 발생할 수 있는 문제(경쟁상태)를 방지하면서 변수를 변경할 수 있다
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
			atomicReference.compareAndSet(concurrentTestBean, concurrentTestBean);
		}

		log.info("입력 될 값 :------------------- {}", str);
		concurrentTestBean.setStr(str);

		// 1초간 스레드 정지
		MySleep.mySleep();

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


}
