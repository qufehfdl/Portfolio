package com.hrilke.project.OOP;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MyWrite {

	@Insert("insert into MyWrite(name,subject,content) values(#{name},#{subject},#{content})")
	void myWrite(@Param("name") String name,@Param("subject") String subject,@Param("content") String content);
	
}