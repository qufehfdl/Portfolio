package com.hrilke.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hrilke.project.beans.UserBean;

import lombok.RequiredArgsConstructor;

//로그인 했을때만 사용할 수 있는 페이지라도 사용자가 직접주소치고 들어가는것을 방지
//여기서 로그인 여부를 확인하고 로그인 했을경우에만 다음단계

@RequiredArgsConstructor
public class CheckLoginInterceptor implements HandlerInterceptor {

	private final UserBean loginUserBean;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 하지 않은 사용자가 url을 치고 정보수정이나 삭제등 페이지로 강제이동할 시 인터셉터로 막자
		if (loginUserBean.isUserLogin() == false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");
			return false; // 다음단계 이동 x
		}

		return true; // 컨트롤러로 이동
	}

}
