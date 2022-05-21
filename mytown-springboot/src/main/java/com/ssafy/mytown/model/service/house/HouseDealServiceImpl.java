package com.ssafy.mytown.model.service.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.house.HouseDealDto;
import com.ssafy.mytown.model.mapper.house.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private HouseDealMapper houseDealMapper;
	
	@Override
	public List<HouseDealDto> getHouseDeal(String dongCode) throws Exception {
		return houseDealMapper.getHouseDeal(dongCode);
	}

}
