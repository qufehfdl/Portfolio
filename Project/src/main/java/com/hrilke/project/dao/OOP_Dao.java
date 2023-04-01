package com.hrilke.project.dao;

import org.springframework.stereotype.Repository;

import com.hrilke.project.OOP.MyModify;
import com.hrilke.project.OOP.MyWrite;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OOP_Dao {
 
	private final MyWrite myWrite;
	private final MyModify modify;

	public void myWrite(String name, String subject, String content) {
		
		// MyModify를 구현한 2개의 구현 객체 중에
		// 어떤 구현클래스를 주입 받느냐에 따라 DB에 저장되는 값이 달라짐
		String sum_name = modify.modify(name);
		
		//DB에 저장
		myWrite.myWrite(sum_name, subject, content);
	}
}
