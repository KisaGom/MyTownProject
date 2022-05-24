package com.ssafy.mytown.model.mapper.member;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.mytown.model.dto.member.MemberDto;


public interface MemberMapper {
	public List<MemberDto> list() throws Exception;
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int register(MemberDto memberDto) throws SQLException;
	public int modify(MemberDto memberDto) throws SQLException;
	public int delete(String userid) throws SQLException;
	public String findPwd(MemberDto memberDto) throws SQLException;
	
}