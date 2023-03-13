package com.hrilke.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hrilke.project.beans.ReplyBean;
import com.hrilke.project.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDao {

	private final ReplyMapper replyMapper;

	// 답글 달기
	public void addReply(ReplyBean replyBean) {
		replyMapper.addReply(replyBean);
	}

	// 답글 리스트
	public List<ReplyBean> replyList(int content_num) {
		return replyMapper.replyList(content_num);
	}

	// 답글 수정
	public void modify(ReplyBean replyBean) {

		replyMapper.modify(replyBean);
	}
	
	
}
