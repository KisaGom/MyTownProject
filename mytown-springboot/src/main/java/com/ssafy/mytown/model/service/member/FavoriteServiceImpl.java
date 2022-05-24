package com.ssafy.mytown.model.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.member.FavoriteDto;
import com.ssafy.mytown.model.mapper.member.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public List<FavoriteDto> list(String userid) throws Exception {
		return favoriteMapper.list(userid);
	}

	@Override
	public boolean register(FavoriteDto favoriteDto) throws Exception {
		return favoriteMapper.register(favoriteDto) == 1;
	}

	@Override
	public boolean delete(String userid, String dongCode) throws Exception {
		return favoriteMapper.delete(userid, dongCode) == 1;
	}

}
