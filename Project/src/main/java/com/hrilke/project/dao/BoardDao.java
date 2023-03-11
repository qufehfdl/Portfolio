package com.hrilke.project.dao;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hrilke.project.beans.ContentBean;
import com.hrilke.project.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDao {

	private final BoardMapper boardMapper;

	// 게시글 작성
	public void addContentInfo(ContentBean writeContentBean) {
		boardMapper.addContentInfo(writeContentBean);
	}

	// top.jsp에 보여질 카테고리 이름
	public String getBoardInfoName(int board_category) {
		return boardMapper.getBoardInfoName(board_category);
	}

	// 게시글 리스트
	public List<ContentBean> getContentList(int board_category, RowBounds rowBounds) {
		return boardMapper.getContentList(board_category, rowBounds);
	}

	// 게시글 읽기
	public ContentBean getContentInfo(int content_num) {
		
		
		return boardMapper.getContentInfo(content_num);
	}

	// 게시글 수정
	public void modifyContentInfo(ContentBean modifyContentBean) {
		boardMapper.modifyContentInfo(modifyContentBean);
	}

	// 게시글 삭제
	public void deleteContentInfo(int content_num) {
		boardMapper.deleteContentInfo(content_num);
	}

	// 카테고리별 화면 페이징처리
	public int getContentCnt(int content_board_num) {
		return boardMapper.getContentCnt(content_board_num);
	}

	// 검색 리스트
	public Vector<ContentBean> getSearchList(String content_subject, RowBounds rowBounds) {
		return boardMapper.getSearchList(content_subject, rowBounds);
	}

	// 검색 페이징처리
	public int getSearchCnt(String content_subject) {
		return boardMapper.getSearchCnt(content_subject);
	}
}
