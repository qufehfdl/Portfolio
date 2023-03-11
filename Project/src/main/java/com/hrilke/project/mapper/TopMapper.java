package com.hrilke.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hrilke.project.beans.BoardInfoBean;
 
public interface TopMapper {

	@Select("select board_category,board_name from board_info_table order by board_category")
	List<BoardInfoBean> getTopMenuList();
}
