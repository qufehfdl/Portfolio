package com.hrilke.project.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hrilke.project.beans.UserBean;

public interface UserMapper {

	// 회원 가입
	@Insert("insert into user_table (user_name , user_id ,user_pw)" + "values (#{user_name} ,#{user_id},#{user_pw})")
	void addUserInfo(UserBean joinUserBean);

	// 회원 정보 조회
	@Select("select user_num ,user_name,user_id from user_table where user_id= #{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean loginUser);

	// 중복 확인
	@Select("select user_name from user_table where user_id = #{user_id}")
	String checkUserIdExist(String user_id);

	// 회원 정보 수정을 위해 정보가져옴
	@Select("select user_id ,user_name from user_table where user_num = #{user_num}")
	UserBean getModifyUserInfo(int user_num);

	// 회원 정보 수정
	@Update("update user_table set user_pw = #{user_pw} where user_num = #{user_num}")
	void modifyUserInfo(UserBean modifyUserBean);
	

//	-------------------------오라클-----------------------

//	@Insert("insert into taehwa.user_table (user_num, user_name , user_id ,user_pw)"
//			+ "values (taehwa.user_seq.nextval ,#{user_name} ,#{user_id},#{user_pw})")
//	void addUserInfo(UserBean joinUserBean);
//	
//	@Select("select user_num ,user_name from taehwa.user_table where user_id= #{user_id} and user_pw = #{user_pw}")
//	UserBean getLoginUserInfo(UserBean loginUser);
//	
//	@Select("select user_name from taehwa.user_table where user_id = #{user_id}")
//	String checkUserIdExist(String user_id);
//	
//	@Select("select user_id ,user_name from taehwa.user_table where user_num = #{user_num}")
//	UserBean getModifyUserInfo(int user_num);
//	
//	@Update("update taehwa.user_table set user_pw = #{user_pw} where user_num = #{user_num}")
//	void modifyUserInfo(UserBean modifyUserBean);

//	------------------------------------------------------------------
}
