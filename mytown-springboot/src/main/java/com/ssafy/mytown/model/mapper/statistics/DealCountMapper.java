package com.ssafy.mytown.model.mapper.statistics;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.statistics.DealCountDto;

@Mapper
public interface DealCountMapper {
	List<DealCountDto> list(String userid) throws Exception;
}
