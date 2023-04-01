package com.hrilke.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrilke.project.OOP.MyModify;
import com.hrilke.project.OOP.MyModify_Impl_1;
import com.hrilke.project.OOP.MyModify_Impl_2;
import com.hrilke.project.service.OOP_Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OOP_Controller {
	private final OOP_Service oop_Service;
	private final MyModify modify;

	@GetMapping("/OOP_Test")
	public String write() {

		// 저장될 값 전달
		oop_Service.myWrite("태화", "제목", "내용");

		// 참조값을 알아보자
		if (modify instanceof MyModify_Impl_1) {
			log.info("MyModify_Impl_1 을 참조중!");
		}
		if (modify instanceof MyModify_Impl_2) {
			log.info("MyModify_Impl_2 을 참조중!");
		}
		return "ok";
	}
}
