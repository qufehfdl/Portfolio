package com.hrilke.project.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {

	private int user_num;
	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*")
	private String user_name;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_id;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw;
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw2;

	// 쿠키
	private boolean cook;
	// 사용자가 중복확인을 했는지 안했는지 검사
	private boolean userIdExist;
	// 사용자가 로그인을 했는지 안했는지에대한 판단 여부 : 로그인 성공하면 true값으로 변경되게
	private boolean userLogin;

	public UserBean() {
		this.userIdExist = false;
		this.userLogin = false;
		this.cook = false;
	}
	
	
}
