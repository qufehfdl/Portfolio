package com.hrilke.project.OOP;

import javax.annotation.PostConstruct;

public class MyModify_Impl_2 implements MyModify {
	String name;

	@Override
	public String modify(String name) {
		return this.name + name;
	}

	@PostConstruct
	public void first() {
		this.name = "Hong";
	}
}
