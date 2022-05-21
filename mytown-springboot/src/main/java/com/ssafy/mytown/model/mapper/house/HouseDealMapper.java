package com.ssafy.mytown.model.mapper.house;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.house.HouseDealDto;

@Mapper
public interface HouseDealMapper {
	List<HouseDealDto> getHouseDeal(String dongCode) throws Exception;
}
