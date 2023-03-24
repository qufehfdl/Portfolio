package com.hrilke.project.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hrilke.project.beans.BoardInfoBean;
import com.hrilke.project.beans.UserBean;
import com.hrilke.project.service.TopService;

import lombok.RequiredArgsConstructor;

// 상단메뉴는 어떤작업을 요청하던지 다 동작을 해야하므로 인터셉터로 만듬

// 인터셉터는 자동주입 받지 못함!!
// 인터셉터에서 사용할 객체들은 인터셉터로 등록하는쪽에서 빈을 주입받은 후 생성자로 넘겨서 작업해야함

@RequiredArgsConstructor
public class TopMenuInterceptor implements HandlerInterceptor {

	private final TopService topService;
	private final UserBean loginUserBean;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		List<BoardInfoBean> topMenuList = topService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		 
		return true;
	}
}
