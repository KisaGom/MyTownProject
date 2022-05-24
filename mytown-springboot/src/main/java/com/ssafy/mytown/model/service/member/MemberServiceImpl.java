package com.ssafy.mytown.model.service.member;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.member.MemberDto;
import com.ssafy.mytown.model.mapper.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> list() throws Exception {
		return memberMapper.list();
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public boolean register(MemberDto memberDto) throws Exception {
		return memberMapper.register(memberDto) == 1;
	}

	@Override
	public boolean modify(MemberDto memberDto) throws SQLException {
		return memberMapper.modify(memberDto) == 1;
	}

	@Override
	public boolean delete(String userid) throws SQLException {
		return memberMapper.delete(userid) == 1;
	}

	@Override
	public String findPwd(MemberDto memberDto) throws SQLException {
		return memberMapper.findPwd(memberDto);
	}


}
