package com.hrilke.project.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyBean {
	private Integer board_category;
	private int content_num;
	
	private int user_num;
	private String user_name;
	private String user_id;
	private String reply;
	private int reply_num;
}
