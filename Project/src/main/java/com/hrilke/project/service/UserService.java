package com.hrilke.project.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.hrilke.project.beans.UserBean;
import com.hrilke.project.dao.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final ThreadPoolTaskExecutor executor;
	private final UserDao userDao;

	@Qualifier("loginUserBean")
	private final UserBean loginUserBean;

	// 회원 가입
	// 쓰레드 풀 개수를 조정
	@Async
	public void addUserInfo(UserBean joinUserBean) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				userDao.addUserInfo(joinUserBean);
			}
		});
	}

	// 아이디 중복 확인
	public boolean checkUserIdExist(String user_id) {
		String user_name = userDao.checkUserIdExist(user_id);
		if (user_name == null) {
			return true;
		} else {
			return false;
		}
	}

	// 로그인 시 회원 정보를 가져와서 맞는지 판단
	public void getLoginUserInfo(UserBean loginUser) {
		UserBean loginUser2 = userDao.getLoginUserInfo(loginUser);

		if (loginUser2 != null) {
			loginUserBean.setUser_num(loginUser2.getUser_num());
			loginUserBean.setUser_name(loginUser2.getUser_name());
			loginUserBean.setUser_id(loginUser2.getUser_id());

			// 로그인 되어있게 바꿈
			loginUserBean.setUserLogin(true);
		}
	}

	// 회원 수정을 위해 회원 정보 리턴
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean = userDao.getModifyUserInfo(loginUserBean.getUser_num());
		// 데이터베이스에서 가져온 사용자 정보를 매개변수에 담아준것 이게 유저컨트롤러로 감
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_num(tempModifyUserBean.getUser_num());
	}

	// 회원 수정
	public void modifyUserInfo(UserBean modifyUserBean) {
		modifyUserBean.setUser_num(loginUserBean.getUser_num());
		userDao.modifyUserInfo(modifyUserBean);
	}
}
