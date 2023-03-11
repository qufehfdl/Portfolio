package com.hrilke.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrilke.project.beans.BoardInfoBean;
import com.hrilke.project.dao.TopDao;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class TopService {

	private final TopDao topMenuDao;
	
	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		return topMenuList;
	}
}
