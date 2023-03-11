package com.hrilke.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrilke.project.beans.ReplyBean;
import com.hrilke.project.beans.UserBean;
import com.hrilke.project.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

	private final UserBean loginUserBean;
	private final ReplyService replyService;

	//댓글 작성 : 동기 처리
	@PostMapping("/write")
	public String write(@ModelAttribute("replyBean") ReplyBean replyBean, Model model) {
		replyBean.setUser_num(loginUserBean.getUser_num());
		replyBean.setUser_id(loginUserBean.getUser_id());
		replyBean.setUser_name(loginUserBean.getUser_name());
		replyService.addReply(replyBean);

		model.addAttribute("board_category", replyBean.getBoard_category());
		model.addAttribute("content_num", replyBean.getContent_num());
		return "redirect:/board/read";
	}

	//댓글 수정 : 비동기 처리
	@ResponseBody
	@GetMapping("/modify/{replyModify}/{reply_num}")
	public ReplyBean modify(@PathVariable String replyModify, @PathVariable int reply_num) {
		ReplyBean replyBean = new ReplyBean();
		replyBean.setReply(replyModify);
		replyBean.setReply_num(reply_num);
		replyService.modify(replyBean);
		return replyBean;
	}

}
