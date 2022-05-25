package com.ssafy.mytown.model.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.AvgDealAmountDto;
import com.ssafy.mytown.model.dto.statistics.AvgRentAmountDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;
import com.ssafy.mytown.model.mapper.statistics.MapinfoMapper;

@Service
public class MapinfoServiceImpl implements MapinfoSerivce {

	@Autowired
	private MapinfoMapper mapinfoMapper;
	
	@Override
	public AvgDealAmountDto getADA(String dongCode) throws Exception {
		return mapinfoMapper.getADA(dongCode);
	}

	@Override
	public DealCountDto getDC(String dongCode) throws Exception {
		return mapinfoMapper.getDC(dongCode);
	}

	@Override
	public TaxIncomeDto getTC(String dongCode) throws Exception {
		System.out.println(mapinfoMapper.getTC(dongCode));
		return mapinfoMapper.getTC(dongCode);
	}

	@Override
	public AvgRentAmountDto getAMRA(String dongCode) throws Exception {
		return mapinfoMapper.getAMRA(dongCode);
	}

	@Override
	public AvgRentAmountDto getATRA(String dongCode) throws Exception {
		return mapinfoMapper.getATRA(dongCode);
	}

	@Override
	public RentCountDto getRC(String dongCode) throws Exception {
		return mapinfoMapper.getRC(dongCode);
	}

	@Override
	public AgeAvgDto getAA(String dongCode) throws Exception {
		return mapinfoMapper.getAA(dongCode);
	}

}
