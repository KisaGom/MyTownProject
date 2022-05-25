package com.ssafy.mytown.model.mapper.statistics;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.AvgDealAmountDto;
import com.ssafy.mytown.model.dto.statistics.AvgRentAmountDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;

@Mapper
public interface StatisticsMapper {
	List<AvgDealAmountDto> adaList(String userid) throws Exception;
	List<DealCountDto> dcList(String userid) throws Exception;
	List<TaxIncomeDto> tcList(String userid) throws Exception;
	List<AvgRentAmountDto> araTotList(String userid) throws Exception;
	List<AvgRentAmountDto> araMonList(String userid) throws Exception;
	List<RentCountDto> rcList(String userid) throws Exception;
	List<AgeAvgDto> agList(String userid) throws Exception;
}
