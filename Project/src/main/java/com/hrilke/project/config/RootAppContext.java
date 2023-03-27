package com.hrilke.project.config;

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
	
// Atomic 클래스와 ThreadLocal의 차이
	
// 		Atomic 클래스는 멀테스레드 환경에서 동기화 없이 안전하게 변수를 업데이트하게 해준다 [원자성을 보장해 줌] 
//		내부적으로 비교-교환 연산(CAS)를 사용해서 현재값과 변경하려는 값이 같은지 비교 후 같으면 새로운 값으로 변경하고
//		다르면 아무 일도 하지 않음 이 과정이 모든 스레드에서 일관적으로 수행된다!
//		여러 스레드가 동시에 변수에 접근할 때 발생할 수 있는 문제(경쟁상태)를 방지하면서 변수를 변경할 수 있다
//
//		ThreadLocal은 각 스레드마다 고유한 값을 가질 수 있도록 해줌	
//		스레드가 ThreadLocal의 값을 가져오거나 저장하면 해당 스레드의 로컬 메모리에 저장된다 
//		다른 스레드는 이 값을 직접적으로 접근할 수 없으므로 각 스레드에서의 값은 서로 영향을 미치지 않는다!
//		
//		Atomic 클래스는 원자성을 보장하기 위한 클래스
//		ThreadLocal은 스레드 별로 독립적인 값을 유지하는데 사용됨
}
