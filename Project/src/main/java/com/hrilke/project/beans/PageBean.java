package com.hrilke.project.beans;

import lombok.Getter;

@Getter
public class PageBean {
 
	// 최소 페이지 번호
	private int min; 
	// 최대 페이지 번호
	private int max;
	// 이전 버튼의 페이지번호
	private int prevPage;
	// 다음 버튼의 페이지번호
	private int nextPage;
	// 전체 페이지 개수
	private int pageCnt;
	// 현재 페이지 번호
	private int currentPage;

//					   전체 글 개수        현재 글 번호       페이지당 글의 개수    페이지 버튼의 개수
	public PageBean(int content_cnt, int currentPage, int contentPageCnt, int pageNationCnt) {
		// 현재 페이지 번호
		this.currentPage = currentPage;

		// 전체 페이지 개수
		pageCnt = content_cnt / contentPageCnt;
		if (content_cnt % contentPageCnt > 0) { // 전체글의개수가 페이지당 글의개수로 나눈 나무지가 0보다 크면 페이지를 하나더 추가
			pageCnt++;
		}

		min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;

		// 페이지 개수가 53개인데 51~60까지 나오면 안되므로 51~53까지 나오게 세팅
		max = min + pageNationCnt - 1;
		if (max > pageCnt) {
			max = pageCnt;
		}

		prevPage = min - 1;
		nextPage = max + 1;
		if (nextPage > pageCnt) {
			nextPage = pageCnt;
		}

	}
}
