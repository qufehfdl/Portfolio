package com.hrilke.project.controller.concurrent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.e;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConcurrentHashMapController {

	private final ConcurrentHashMap<String, String> concurrentHashMap;

	@GetMapping("/test/{key}/{str}")
	public String test(@PathVariable String str, @PathVariable String key) {

		System.out.println(Thread.currentThread().getName() + "   밸류값은 : " + str);

		concurrentHashMap.put(key, str);

//		// 1초간 스레드 정지
		mySleep();
		log.info(concurrentHashMap.get("testKey") + "          쓰레드이름은 : " + Thread.currentThread().getName());
		return concurrentHashMap.get("testKey");
	}

	@GetMapping("/concurrent")
	public String concurrent() {
		Thread t4 = new Thread(() -> myRequest4("/Project/test/testKey/~~~~~~"));
		Thread t1 = new Thread(() -> myRequest1("/Project/test/testKey/qqqqqq"));
		Thread t2 = new Thread(() -> myRequest2("/Project/test/testKey/123456"));
		Thread t3 = new Thread(() -> myRequest3("/Project/test/testKey/xxxxxx"));
		Thread t5 = new Thread(() -> myRequest5("/Project/test/testKey/oooooo"));
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
