package com.hrilke.project.config;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hrilke.project.beans.UserBean;
import com.hrilke.project.beans.concurrent.ConcurrentTestBean;

// bean 정의하는 클래스
@Configuration
@EnableAsync
public class RootAppContext extends HandlerInterceptorAdapter {

	// 여러가지 용도로 사용되기 때문에 따로 이름을 부여해서 다른것과 구분하자
	// 세션 스코프로 등록
	@Bean
	@Qualifier("loginUserBean")
	@SessionScope
	public UserBean loginUserBean(HttpSession session) {

//		session.setMaxInactiveInterval(60);
		return new UserBean();
	}
	
	//동시성 공부
	@Bean
	public ConcurrentTestBean concurrentTestBean() {
		return new ConcurrentTestBean();
	}
	
	@Bean
	public ThreadLocal<ConcurrentTestBean> threadLocal(){
		return new ThreadLocal<ConcurrentTestBean>();
	}
}
