package com.ssafy.mytown.model.dto.community;

public class CommentDto {
	private int id;
	private String userid;
	private String content;
	private String regtime;
	private int board_id;
	public CommentDto(int id, String userid, String content, String regtime, int board_id) {
		super();
		this.id = id;
		this.userid = userid;
		this.content = content;
		this.regtime = regtime;
		this.board_id = board_id;
	}
	public CommentDto() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", userid=" + userid + ", content=" + content + ", regtime=" + regtime
				+ ", board_id=" + board_id + "]";
	}
}
