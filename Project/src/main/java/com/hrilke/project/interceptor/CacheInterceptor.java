package com.hrilke.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CacheInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (response.getStatus() == HttpServletResponse.SC_OK) {
			
			// 캐시 유효시간이 초과해도 서버의 데이터가 갱신되지 않으면 304 상태코드를 응답한다
			// 클라이언트는 서버가 보낸 응답으로 캐시의 메타정보를 갱신
			// 클라이언트는 캐시에 저장되어 있는 데이터를 재.활.용!!
			// 결과적으로는 네트워크 다운로드가 발생하지만 용량이 적은 헤더 정보만 다운로드 한다
			
			response.setHeader("cache-control", "max-age=10");
		}
	}
}  