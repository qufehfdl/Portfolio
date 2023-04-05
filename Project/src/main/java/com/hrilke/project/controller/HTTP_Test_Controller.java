package com.hrilke.project.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HTTP_Test_Controller {

// 데이터를 보내는 3가지 형식
	
//  GET  : 앰퍼샌드(&)로 구분된 키-값 쌍으로 인코딩된다
//		   메세지 바디없이 [URL]의 쿼리파라미터에 데이터를 포함해서 전달

//  POST : content-type: application/x-www-form-urlencoded
//		   메세지 [바디]에 쿼리파라미터 형식으로 전달

//  raw(JSON,XML,TEXT..)는 메세지 바디에 데이터를 [직접!!] 담아서 전달 

	
	@PostMapping("/converter")
	public HTTP_Test_Bean myConverter(@RequestBody HTTP_Test_Bean http_Test_Bean) {

		// 포스트맨으로 {"data1" : "aaa" ,"data2" : "hello" } 라고 보낸다면
		// HTTP메세지 컨버터가 잘 동작은 했지만 data1은 int타입이기 때문에 
		// 아규먼트리졸버에서 에러가 남!!!

		log.info("json : {}", http_Test_Bean);
		log.info("data1 : {}", http_Test_Bean.getData1());
		log.info("data2 : {}", http_Test_Bean.getData2());

		return http_Test_Bean;
	}
}

@Getter
@Setter
class HTTP_Test_Bean {

	private int data1;
	private String data2;
}
