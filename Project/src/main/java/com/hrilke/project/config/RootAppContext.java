package com.hrilke.project.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.hrilke.project.beans.UserBean;
import com.hrilke.project.beans.concurrent.ConcurrentTestBean;

// bean 정의하는 클래스
@Configuration
public class RootAppContext {

	// 여러가지 용도로 사용되기 때문에 따로 이름을 부여해서 다른것과 구분하자
	// 세션 스코프로 등록
	@Bean
	@Qualifier("loginUserBean")
	@SessionScope
	public UserBean loginUserBean(HttpSession session) {

//		session.setMaxInactiveInterval(60);
		return new UserBean();
	}

	// 동시성 공부
	@Bean
	public ThreadLocal<ConcurrentTestBean> threadLocal() {
		return new ThreadLocal<ConcurrentTestBean>();
	}
	@Bean
	public AtomicReference<ConcurrentTestBean> atomicReference() {
		return new AtomicReference<ConcurrentTestBean>();
	}
	@Bean
	ConcurrentHashMap<String, String> concurrentHashMap(){
		return new ConcurrentHashMap<String, String>();
	}
}
