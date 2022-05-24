package com.ssafy.mytown.model.service.member;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.mytown.model.dto.member.MemberDto;

public interface MemberService {

	public List<MemberDto> list() throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean register(MemberDto memberDto) throws Exception;
	public boolean modify(MemberDto memberDto) throws SQLException;
	public boolean delete(String userid) throws SQLException;

	public String findPwd(MemberDto memberDto) throws SQLException;
	
}
