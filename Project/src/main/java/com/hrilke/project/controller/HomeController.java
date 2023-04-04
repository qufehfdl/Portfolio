package com.hrilke.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hrilke.project.beans.BoardInfoBean;
import com.hrilke.project.service.TopService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final TopService topService;

	// 홈 화면
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home2(Model model) {
		List<BoardInfoBean> board_list = topService.getTopMenuList();
		model.addAttribute("board_list", board_list);

		return "home";
	}

}

//servlet?
// 서블릿은 HTTP 프로토콜을 사용하여 인터넷을 통해 사용자와 상호 작용하는 웹 애플리케이션을 구축하는 데 사용
// 서블릿은 클라이언트 요청을 처리하고 동적 웹 페이지를 생성하기 위해 웹 서버에서 실행되는 Java 프로그램
// 클라이언트가 웹 서버에 HTTP 요청(Request)을 보내면 
// 서버는 요청 매개 변수 및 응용 프로그램별 논리를 기반으로 응답(Response)을 생성하는 적절한 서블릿에 요청을 전달