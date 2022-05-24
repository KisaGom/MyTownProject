package com.ssafy.mytown.model.mapper.statistics;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;

@Mapper
public interface StatisticsMapper {
	List<DealCountDto> dcList(String userid) throws Exception;
	List<TaxIncomeDto> tcList(String userid) throws Exception;
	List<RentCountDto> rcList(String userid) throws Exception;
	List<AgeAvgDto> agList(String userid) throws Exception;
	
}
