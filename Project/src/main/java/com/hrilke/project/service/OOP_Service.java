package com.hrilke.project.service;

import org.springframework.stereotype.Service;

import com.hrilke.project.dao.OOP_Dao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OOP_Service {

	private final OOP_Dao oop_Dao;

	public void myWrite(String name, String subject, String content) {
		oop_Dao.myWrite(name, subject, content);
	}
}
