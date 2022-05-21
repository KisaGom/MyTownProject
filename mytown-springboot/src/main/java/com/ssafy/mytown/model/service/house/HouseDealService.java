package com.ssafy.mytown.model.service.house;

import java.util.List;

import com.ssafy.mytown.model.dto.house.HouseDealDto;

public interface HouseDealService {
	List<HouseDealDto> getHouseDeal(String dongCode) throws Exception;

}
