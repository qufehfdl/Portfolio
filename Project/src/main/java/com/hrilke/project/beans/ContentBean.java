package com.hrilke.project.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ContentBean {
	
	private int content_num;
	@NotBlank
	private String content_subject;
	@NotBlank
	private String content_text;

	// 클라이언트가 보낸 파일데이터는 MultipartFile 이라는 객체를 만들어서 주입하려고 시도
	// 타입이 문자열로 되어있기 때문에 주입에 실패해서 문제가 발생
	// ■ 서버에 저장된 파일이름을 담을 변수
	private String content_file;

	// ■ 클라이언트가 보낸 파일데이터를 담기위한 변수
	private MultipartFile upload_file;

	private int content_writer_num;
	private int content_board_num;
	private String content_date;
	private String content_writer_name;

	
	

}
