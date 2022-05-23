package com.ssafy.mytown.model.dto.community;

public class BoardDto {
	private int id;
	private String userid;
	private String content;
	private String regtime;
	private int like;
	public BoardDto(int id, String userid, String content, String regtime, int like) {
		this.id = id;
		this.userid = userid;
		this.content = content;
		this.regtime = regtime;
		this.like = like;
	}
	public BoardDto() {}
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
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", userid=" + userid + ", content=" + content + ", regtime=" + regtime + ", like="
				+ like + "]";
	}
}
