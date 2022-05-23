package com.ssafy.mytown.model.service.member;

import java.sql.SQLException;

import com.ssafy.mytown.model.dto.member.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean register(MemberDto memberDto) throws Exception;
	public boolean modify(MemberDto memberDto) throws SQLException;
	
}
