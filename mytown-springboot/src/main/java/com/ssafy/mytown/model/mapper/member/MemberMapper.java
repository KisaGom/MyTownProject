package com.ssafy.mytown.model.mapper.member;

import java.sql.SQLException;

import com.ssafy.mytown.model.dto.member.MemberDto;


public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	
}