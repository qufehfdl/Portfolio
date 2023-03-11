package com.hrilke.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hrilke.project.beans.ContentBean;
import com.hrilke.project.beans.UserBean;
import com.hrilke.project.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckWriterInterceptor implements HandlerInterceptor {

	private final UserBean loginUserBean;
	private final BoardService boardService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String str = request.getParameter("content_num");
		int content_num = Integer.parseInt(str);

		ContentBean currentContentBean = boardService.getContentInfo(content_num);

		// 작성자와 현재로그인 사용자가 일치하지않다면!
		if (currentContentBean.getContent_writer_num() != loginUserBean.getUser_num()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;
		}
		// 일치하다면 통과
		return true;
	}
}
