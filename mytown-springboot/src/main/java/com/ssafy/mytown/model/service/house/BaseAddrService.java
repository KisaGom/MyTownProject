package com.ssafy.mytown.model.service.house;

import com.ssafy.mytown.model.dto.house.BaseAddrDto;

public interface BaseAddrService {
	BaseAddrDto getSido() throws Exception;
	BaseAddrDto getGugun(String sido) throws Exception;
	BaseAddrDto getDong(String gugun) throws Exception;
}
