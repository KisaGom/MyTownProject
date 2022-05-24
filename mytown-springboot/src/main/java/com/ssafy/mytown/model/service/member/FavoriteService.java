package com.ssafy.mytown.model.service.member;

import java.util.List;

import com.ssafy.mytown.model.dto.member.FavoriteDto;

public interface FavoriteService {
	List<FavoriteDto> list(String userid) throws Exception;
	boolean register(FavoriteDto favoriteDto) throws Exception;
	boolean delete(String userid, String dongCode) throws Exception;
}
