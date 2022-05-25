package com.ssafy.mytown.model.service.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.AvgDealAmountDto;
import com.ssafy.mytown.model.dto.statistics.AvgRentAmountDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;
import com.ssafy.mytown.model.mapper.statistics.StatisticsMapper;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Override
	public List<AvgDealAmountDto> adaList(String userid) throws Exception {
		//평균 매매가
		return statisticsMapper.adaList(userid);
	}
	
	@Override
	public List<DealCountDto> dcList(String userid) throws Exception {
		//아파트 평균 거래 횟수 리스트
		return statisticsMapper.dcList(userid);
	}

	@Override
	public List<TaxIncomeDto> tcList(String userid) throws Exception {
		//세입량
		return statisticsMapper.tcList(userid);
	}
	
	@Override
	public List<AvgRentAmountDto> araTotList(String userid) throws Exception {
		//평균 전세 계약가
		return statisticsMapper.araTotList(userid);
	}

	@Override
	public List<AvgRentAmountDto> araMonList(String userid) throws Exception {
		//평균 월세 계약가
		return statisticsMapper.araMonList(userid);
	}

	@Override
	public List<RentCountDto> rcList(String userid) throws Exception {
		//전월세 거래 횟수
		return statisticsMapper.rcList(userid);
	}

	@Override
	public List<AgeAvgDto> agList(String userid) throws Exception {
		//평균 나이
		return statisticsMapper.agList(userid);
	}
}

