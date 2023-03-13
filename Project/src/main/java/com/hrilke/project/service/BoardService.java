package com.hrilke.project.service;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrilke.project.beans.ContentBean;
import com.hrilke.project.beans.PageBean;
import com.hrilke.project.beans.UserBean;
import com.hrilke.project.dao.BoardDao;

import lombok.RequiredArgsConstructor;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
@RequiredArgsConstructor
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;

	@Value("${page.listcnt}")
	private int page_listcnt;

	@Value("${page.pagenationcnt}")
	private int page_pagenationcnt;

	private final BoardDao boardDao;

	// 로그인된 유저번호를 받기위해
	@Qualifier("loginUserBean")
	private final UserBean loginUserBean;

	// 파일을 저장하는 메서드 (중복파일이 올라오면 덮어씌어지기 때문에 파일이름 앞에 시간을 붙여줌)
	private String saveUploadFile(MultipartFile upload_file) {
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		try {
			// transferTo하면 파일로 저장이 되고 경로만 세팅해주면 됨
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file_name;
	}

	public void addContentInfo(ContentBean writeContentBean) {
		// 파일 업로드 코드
		MultipartFile upload_file = writeContentBean.getUpload_file();
		if (upload_file.getSize() > 0) {
			// 첨부한 파일이 있을경우에만 파일이름에 세팅이 되지만
			// 첨부한 파일이 없을경우 null값 세팅
			// db의 컬럼은 null을 허용하게 만들었지만 마이바티스에서 막아버림
			// 쿼리문에서 타입을 명시해 주면 된다 : #{content_file , jdbcType=VARCHAR} ※소문자로 varchar 쓰면 에러
			String file_name = saveUploadFile(upload_file);
			writeContentBean.setContent_file(file_name);
		}
		// 로그인된 사용자번호
		writeContentBean.setContent_writer_num(loginUserBean.getUser_num());
		boardDao.addContentInfo(writeContentBean);
	}

	// 카테고리 선택을 위해서 이름 가져옴
	public String getBoardInfoName(int board_category) {
		return boardDao.getBoardInfoName(board_category);
	}

	// 게시글 리스트
	public List<ContentBean> getContentList(int board_category, int page) {
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		return boardDao.getContentList(board_category, rowBounds);
	}

	// 하나의 게시글 정보
	public ContentBean getContentInfo(int content_num) {
		return boardDao.getContentInfo(content_num);
	}

	// 게시글 수정
	public void modifyContentInfo(ContentBean modifyContentBean) {
		MultipartFile upload_file = modifyContentBean.getUpload_file();
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			modifyContentBean.setContent_file(file_name);
		}
		boardDao.modifyContentInfo(modifyContentBean);
	}

	// 게시글 삭제
	public void deleteContentInfo(int content_num) {
		boardDao.deleteContentInfo(content_num);
	}

	// 게시글 전체 개수
	public PageBean getContentCnt(int content_board_num, int currentPage) {
		int content_cnt = boardDao.getContentCnt(content_board_num);
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_pagenationcnt);
		return pageBean;
	}

	// 검색된 게시글 개수
	public Vector<ContentBean> getSearchList(String content_subject, int page) {
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		return boardDao.getSearchList(content_subject, rowBounds);
	}

	// 검색된 게시글 리스트
	public PageBean getSearchCnt(String content_subject, int currentPage) {
		int content_cnt = boardDao.getSearchCnt(content_subject);
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_pagenationcnt);
		return pageBean;
	}
}
