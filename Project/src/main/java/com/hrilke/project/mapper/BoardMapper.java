package com.hrilke.project.mapper;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.hrilke.project.beans.ContentBean;

public interface BoardMapper {

//	글을 저장하기 전에			이 쿼리문을 먼저 실행하고 담긴 값을                     		여기다 넣는다         		먼저실행        	타입은 인티저
	@SelectKey(statement = "select max(content_num)+1 from content_table", keyProperty = "content_num", before = true, resultType = int.class)

	// 게시글 작성
	@Insert("insert into content_table(content_num,content_subject,content_text, "
			+ "content_file ,content_writer_num , content_board_num ,content_date) "
			+ "values (#{content_num},#{content_subject},#{content_text},#{content_file,jdbcType=VARCHAR}, "
			+ "#{content_writer_num}, #{content_board_num},now())")
	void addContentInfo(ContentBean writeContentBean);

	// 카테고리 선택을 위해서 이름 가져옴
	@Select("select board_name from board_info_table where board_category = #{board_category}")
	String getBoardInfoName(int board_category);

	// 게시글 리스트
	@Select("select a1.content_num,a1.content_subject,a2.user_name as content_writer_name, "
			+ "DATE_FORMAT(a1.content_date, '%Y/%m/%d') as content_date "
			+ "from content_table a1 , user_table a2 where a1.content_writer_num = a2.user_num "
			+ "and a1.content_board_num = #{board_category} order by a1.content_num desc")
	List<ContentBean> getContentList(int board_category, RowBounds rowBounds);

	// 하나의 게시글 정보
	@Select("select a2.user_name as content_writer_name , DATE_FORMAT(a1.content_date, '%Y/%m/%d') as content_date, "
			+ "a1.content_subject , a1.content_text , a1.content_file , a1.content_writer_num "
			+ "from content_table a1 ,user_table a2 "
			+ "where a1.content_writer_num = a2.user_num and content_num = #{content_num}")
	ContentBean getContentInfo(int content_num);

	// 게시글 수정
	@Update("update content_table " + "set content_subject =#{content_subject} , content_text = #{content_text}, "
			+ "content_file = #{content_file , jdbcType=VARCHAR} " + "where content_num = #{content_num}")
	void modifyContentInfo(ContentBean modifyContentBean);

	// 게시글 삭제
	@Delete("delete from content_table where content_num = #{content_num}")
	void deleteContentInfo(int content_num);

	// 게시글 전체 개수
	@Select("select count(*) from content_table where content_board_num = #{content_board_num}")
	int getContentCnt(int content_board_num);

	// 검색된 게시글 개수
	@Select("select count(*) from content_table where content_subject Like CONCAT('%',#{content_subject},'%')")
	int getSearchCnt(String content_subject);

	// 검색된 게시글 리스트
	@Select("select * from content_table where content_subject Like CONCAT('%',#{content_subject},'%')")
	Vector<ContentBean> getSearchList(String content_subject, RowBounds rowBounds);

//  -------------------------------- 오라클 --------------------------------

//	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_num", before = true, resultType = int.class)
//
//	@Insert("insert into content_table(content_num ,content_subject,content_text, "
//			+ "content_file ,content_writer_num , content_board_num ,content_date) "
//			+ "values (#{content_num},#{content_subject},#{content_text},#{content_file,jdbcType=VARCHAR}, "
//			+ "#{content_writer_num}, #{content_board_num},sysdate)")
//	void addContentInfo(ContentBean writeContentBean);
//
//	@Select("select board_name from board_info_table where board_category = #{board_category}")
//	String getBoardInfoName(int board_category);
//
//	@Select("select a1.content_num,a1.content_subject,a2.user_name as content_writer_name, "
//			+ "to_char(a1.content_date,'YYYY-MM-DD') as content_date "
//			+ "from content_table a1 , user_table a2 where a1.content_writer_num = a2.user_num "
//			+ "and a1.content_board_num = #{board_category} order by a1.content_num desc")
//	List<ContentBean> getContentList(int board_category, RowBounds rowBounds);
//
//	@Select("select a2.user_name as content_writer_name , to_char(a1.content_date ,'YYYY-MM-DD') as content_date, "
//			+ "a1.content_subject , a1.content_text , a1.content_file , a1.content_writer_num "
//			+ "from content_table a1 ,user_table a2 "
//			+ "where a1.content_writer_num = a2.user_num and content_num = #{content_num}")
//	ContentBean getContentInfo(int content_num);
//
//	@Update("update content_table "
//			+ "set content_subject =#{content_subject} , content_text = #{content_text}, "
//			+ "content_file = #{content_file , jdbcType=VARCHAR} " + "where content_num = #{content_num}")
//	void modifyContentInfo(ContentBean modifyContentBean);
//
//	@Delete("delete from content_table where content_num = #{content_num}")
//	void deleteContentInfo(int content_num);
//
//	@Select("select count(*) from content_table where content_board_num = #{content_board_num}")
//	int getContentCnt(int content_board_num);

//	-----------------------------------------------------------------------------------

}
