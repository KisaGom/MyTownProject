package com.ssafy.mytown.model.mapper.house;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.house.BaseAddrDto;

@Mapper
public interface BaseAddrMapper {
	List<BaseAddrDto> getSido() throws Exception;
	List<BaseAddrDto> getGugun(String sido) throws Exception;
	List<BaseAddrDto> getDong(String gugun) throws Exception;
	BaseAddrDto getAddrByDongCode(String gugun) throws Exception;
}
