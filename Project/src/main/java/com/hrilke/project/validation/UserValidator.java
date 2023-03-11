package com.hrilke.project.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hrilke.project.beans.UserBean;

public class UserValidator implements Validator {

	// UserBean의 값들을 유효성검사 하기위한 서포트 메서드
	@Override
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	// 실제 유효성 검사 코드
	@Override
	public void validate(Object target, Errors errors) {
		// 유효성 검사를 하기 위한 Bean객체가 타겟으로 넘어오므로 형변환 먼저 함
		UserBean userBean = (UserBean) target;

		// 로그인할때도 회원가입용 유효성검사가 통과되기 때문에 로그인에서도 아이디 중복확인 검사가 실행됨
		// UserBean클래스를 하나더 만들어서 회원가입용,로그인용 두개로 운영해도 됨
		// 여기선 들어오는 Bean의 이름을 가지고 분기
		String beanName = errors.getObjectName();

		// 회원가입 또는 정보수정 할 때 유효성 검사
		if (beanName.equals("joinUser") || beanName.equals("modifyUser")) {
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
		// 회원가입 할 때 효성 검사
		if (beanName.equals("joinUser")) {
			if (userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
	}
}
