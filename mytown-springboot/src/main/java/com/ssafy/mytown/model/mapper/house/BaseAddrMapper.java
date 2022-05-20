package com.ssafy.mytown.model.mapper.house;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.house.BaseAddrDto;

@Mapper
public interface BaseAddrMapper {
	BaseAddrDto getSido() throws Exception;
	BaseAddrDto getGugun(String sido) throws Exception;
	BaseAddrDto getDong(String gugun) throws Exception;
}
