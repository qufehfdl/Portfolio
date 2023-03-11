package com.hrilke.project.mapper;
 
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hrilke.project.beans.ReplyBean;

public interface ReplyMapper {
	
	//댓글 작성
	@Insert("insert into reply_table(board_category,content_num,user_num,user_name,user_id,reply) "
			+ "values(#{board_category},#{content_num},#{user_num},#{user_name},#{user_id},#{reply})")
	public void addReply(ReplyBean replyBean); 

	//댓글 리스트
	@Select("select * from reply_table where content_num =#{content_num} order by reply_num desc")
	public List<ReplyBean> replyList(int content_num);
	
	//댓글 수정
	@Update("update reply_table set reply = #{reply} where reply_num = #{reply_num}")
	public void modify(ReplyBean replyBean);
}
