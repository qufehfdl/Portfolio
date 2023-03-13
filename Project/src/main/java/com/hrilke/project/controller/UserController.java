package com.hrilke.project.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrilke.project.beans.UserBean;
import com.hrilke.project.service.UserService;
import com.hrilke.project.validation.UserValidator;

import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	@Qualifier("loginUserBean")
	private final UserBean loginUserBean;

	// 회원가입
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUser") UserBean joinUser) {
		return "user/join";
	}

	// 회원가입 검사
	@PostMapping("/join")
	public String join_proc(@Valid @ModelAttribute("joinUser") UserBean joinUser, BindingResult result) {

		if (result.hasErrors()) {
			return "user/join";
		}
		userService.addUserInfo(joinUser);
		return "user/join_success";
	}

	// 로그인
	@GetMapping("/login")
	public String login(@ModelAttribute("loginUser") UserBean loginUser,
			@CookieValue(value = "user_id", required = false) String user_id) {
		loginUser.setUser_id(user_id);
		return "user/login";
	}

	// 로그인 검사
	@PostMapping("/login")
	public String login_pro(@Valid @ModelAttribute("loginUser") UserBean loginUser, BindingResult result,
			HttpServletResponse response, @RequestParam(value = "cook", required = false) boolean cook) {
		// 유효성 검사에 문제가 있으면 다시 로그인화면
		if (result.hasErrors()) {
			return "user/login";
		}
		// 문제가 없다면
		userService.getLoginUserInfo(loginUser);
		if (loginUserBean.isUserLogin() == true) {
			if (cook == true) {
				Cookie cookie = new Cookie("user_id", loginUser.getUser_id());
				cookie.setMaxAge(60);
				response.addCookie(cookie);
			}
			return "user/login_success";
		} else {
			return "user/login_fail";
		}
	}

	// 회원 수정
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUser") UserBean modifyUser) {
		// userService에서 서버에가서 가져온 것을 ↑여기다 넣어준것
		userService.getModifyUserInfo(modifyUser);
		return "user/modify";
	}

	// 회원 수정 검사
	@PostMapping("/modify")
	public String modify_pro(@Valid @ModelAttribute("modifyUser") UserBean modifyUser, BindingResult result) {
		if (result.hasErrors()) {
			return "user/modify";
		}
		userService.modifyUserInfo(modifyUser);
		return "user/modify_success";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout() {
		// 로그인 상태 false로 만듬
		loginUserBean.setUserLogin(false);
		return "user/logout";
	}

	// 로그인 사용자가 아닐경우
	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
}
