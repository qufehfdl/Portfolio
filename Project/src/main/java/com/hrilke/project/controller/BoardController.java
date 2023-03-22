package com.hrilke.project.controller;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrilke.project.beans.ContentBean;
import com.hrilke.project.beans.PageBean;
import com.hrilke.project.beans.ReplyBean;
import com.hrilke.project.beans.UserBean;
import com.hrilke.project.service.BoardService;
import com.hrilke.project.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	@Qualifier("loginUserBean")
	private final UserBean loginUserBean;
	private final ReplyService replyService;

	// 카테고리별 메인화면
	@GetMapping("/main")
	public String main(@RequestParam("board_category") int board_category,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		model.addAttribute("board_category", board_category);

		String boardInfoName = boardService.getBoardInfoName(board_category);
		model.addAttribute("boardInfoName", boardInfoName);

		List<ContentBean> contentList = boardService.getContentList(board_category, page);
		model.addAttribute("contentList", contentList);

		PageBean pageBean = boardService.getContentCnt(board_category, page);
		model.addAttribute("pageBean", pageBean);

		// 글 읽고 목록으로가면 페이지번호가 1로가는데 글을 클릭했었던 페이지번호로 가도록 설정
		model.addAttribute("page", page);

		return "board/main";
	}

	// 게시글 리스트
	@RequestMapping(value = "/searchList", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@RequestParam(value = "content_subject", required = false) String content_subject,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		Vector<ContentBean> vector = boardService.getSearchList(content_subject, page);
		PageBean pageBean = boardService.getSearchCnt(content_subject, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("page", page);
		model.addAttribute("vector", vector);
		model.addAttribute("content_subject", content_subject);
		return "board/search_list";
	}

	// 게시글 작성
	@GetMapping("/write")
	public String write(@ModelAttribute("writeBean") ContentBean writeBean) {
		return "board/write";
	}

//	wirte에서 입력한 데이터들은 @모델어트리뷰트에 의해 자동으로 주입되어서 유효성 검사 실시
//	폼태그에 enctype="multipart/form-data" 설정하면 파일을 업로드했음에도 유효성 검사 실패가 뜸
//	JSP에서 폼태그에  enctype="multipart/form-data"를 세팅하면
//	일반적으로 요청할 때 데이터를 전달하는 방식이 아닌 다른 방식으로 데이터가 전달이 되어서 자동으로 주입되지 못하는 현상 발생
//	만약 servlet/jsp라면 데이터를 뽑아내기위해 과정이 복잡하지만 스프링은 빈만 정의하면 된다
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeBean") ContentBean writeBean, BindingResult result) {
		if (result.hasErrors()) {
			return "board/write";
		}
		System.out.println(writeBean.getContent_num());
		boardService.addContentInfo(writeBean);
		System.out.println(writeBean.getContent_num());
		return "board/write_success";
	}

	// 글 읽기
	@GetMapping("/read")
	public String read(@RequestParam("board_category") int board_category, @RequestParam("content_num") int content_num,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@ModelAttribute("replyBean") ReplyBean replyBean, Model model ,HttpServletResponse response) {

		model.addAttribute("board_category", board_category);
		// 수정 삭제때 필요
		model.addAttribute("content_num", content_num);

		ContentBean readContentBean = boardService.getContentInfo(content_num);
		model.addAttribute("readContentBean", readContentBean);

		model.addAttribute("loginUserBean", loginUserBean);

		model.addAttribute("page", page);

		List<ReplyBean> replyList = replyService.replyList(content_num);
		model.addAttribute("replyList", replyList);
		
		return "board/read";
	}

	// 글 수정
	@GetMapping("/modify")
	public String modify(@RequestParam("board_category") int board_category,
			@RequestParam("content_num") int content_num, @ModelAttribute("modifyBean") ContentBean modifyBean,
			@RequestParam("page") int page, Model model) {

		model.addAttribute("board_category", board_category);
		model.addAttribute("content_num", content_num);
		model.addAttribute("page", page);

		ContentBean tempContentBean = boardService.getContentInfo(content_num);
		modifyBean.setContent_writer_name(tempContentBean.getContent_writer_name());
		modifyBean.setContent_date(tempContentBean.getContent_date());
		modifyBean.setContent_subject(tempContentBean.getContent_subject());
		modifyBean.setContent_text(tempContentBean.getContent_text());
		modifyBean.setContent_file(tempContentBean.getContent_file());
		modifyBean.setContent_board_num(board_category);
		modifyBean.setContent_num(content_num);

		return "board/modify";
	}

	// 글 수정 처리
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyBean") ContentBean modifyBean, BindingResult result,
			@RequestParam("page") int page, Model model) {

		model.addAttribute("page", page);

		if (result.hasErrors()) {
			return "board/modify";
		}
		boardService.modifyContentInfo(modifyBean);
		return "board/modify_success";
	}

	// 글 삭제
	@GetMapping("/delete")
	public String delete(@RequestParam("board_category") int board_category,
			@RequestParam("content_num") int content_num, Model model) {
		boardService.deleteContentInfo(content_num);
		model.addAttribute("board_category", board_category);
		return "board/delete";
	}

	// 글 작성자가 아닐 때 이동
	@GetMapping("/not_writer")
	public String not_writer() {
		return "/board/not_writer";
	}
}
