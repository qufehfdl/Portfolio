package com.hrilke.project.dao;

import org.springframework.stereotype.Repository;

import com.hrilke.project.beans.UserBean;
import com.hrilke.project.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {

	private final UserMapper userMapper;

	// 아이디 중복확인 검사
	public String checkUserIdExist(String user_id) {
		return userMapper.checkUserIdExist(user_id);
	}

	// 유저 정보 확인
	public UserBean getLoginUserInfo(UserBean loginUser) {
		return userMapper.getLoginUserInfo(loginUser);
	}

	// 유저 정보 가져오기
	public UserBean getModifyUserInfo(int user_num) {
		return userMapper.getModifyUserInfo(user_num);
	}

	// 유저 정보 수정 : 따로 구현은 하지 않음
	public void modifyUserInfo(UserBean modifyUserBean) {
		userMapper.modifyUserInfo(modifyUserBean);
	}

	// 회원 가입
	public void addUserInfo(UserBean joinUserBean) {
		userMapper.addUserInfo(joinUserBean);
	}

}
