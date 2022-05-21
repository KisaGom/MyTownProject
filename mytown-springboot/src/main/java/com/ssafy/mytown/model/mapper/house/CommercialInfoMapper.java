package com.ssafy.mytown.model.mapper.house;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.house.CommercialInfoDto;

@Mapper
public interface CommercialInfoMapper {
	List<CommercialInfoDto> getComInfoDong(String dongCode) throws Exception;
}
