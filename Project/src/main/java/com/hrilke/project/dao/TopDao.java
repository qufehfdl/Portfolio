package com.hrilke.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hrilke.project.beans.BoardInfoBean;
import com.hrilke.project.mapper.TopMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TopDao {

	private final TopMapper topMenuMapper;

	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		return topMenuList;
	}
}
