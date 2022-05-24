package com.ssafy.mytown.model.service.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;
import com.ssafy.mytown.model.mapper.statistics.StatisticsMapper;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Override
	public List<DealCountDto> dcList(String userid) throws Exception {
		return statisticsMapper.dcList(userid);
	}

	@Override
	public List<TaxIncomeDto> tcList(String userid) throws Exception {
		return statisticsMapper.tcList(userid);
	}

	@Override
	public List<RentCountDto> rcList(String userid) throws Exception {
		return statisticsMapper.rcList(userid);
	}

	@Override
	public List<AgeAvgDto> agList(String userid) throws Exception {
		return statisticsMapper.agList(userid);
	}

}
