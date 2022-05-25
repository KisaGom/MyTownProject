package com.ssafy.mytown.model.service.statistics;

import java.util.List;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;

public interface StatisticsService {
	List<DealCountDto> dcList(String userid) throws Exception;
	List<TaxIncomeDto> tcList(String userid) throws Exception;
	List<RentCountDto> rcList(String userid) throws Exception;
	List<AgeAvgDto> agList(String userid) throws Exception;
}
