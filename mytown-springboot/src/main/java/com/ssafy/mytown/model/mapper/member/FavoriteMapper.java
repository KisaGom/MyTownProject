package com.ssafy.mytown.model.mapper.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.member.FavoriteDto;

@Mapper
public interface FavoriteMapper {
	List<FavoriteDto> list(String userid) throws Exception;
	int register(FavoriteDto favoriteDto) throws Exception;
	int delete(String userid, String dongCode) throws Exception;
}
