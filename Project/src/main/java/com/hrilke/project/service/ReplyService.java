package com.hrilke.project.service;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.hrilke.project.beans.ReplyBean;
import com.hrilke.project.dao.ReplyDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	
	private final ReplyDao replyDao;
	
	public void addReply(ReplyBean replyBean) {
		replyDao.addReply(replyBean);
	}
	
	public List<ReplyBean> replyList(int content_num){
		return replyDao.replyList(content_num);
	}
	
	public void modify(ReplyBean replyBean) {
		replyDao.modify(replyBean);
	}
}
