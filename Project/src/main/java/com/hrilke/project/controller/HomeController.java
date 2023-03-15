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
