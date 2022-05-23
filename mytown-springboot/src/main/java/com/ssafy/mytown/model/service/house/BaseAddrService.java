package com.ssafy.mytown.model.service.house;

import java.util.List;

import com.ssafy.mytown.model.dto.house.BaseAddrDto;

public interface BaseAddrService {
	List<BaseAddrDto> getSido() throws Exception;
	List<BaseAddrDto> getGugun(String sido) throws Exception;
	List<BaseAddrDto> getDong(String gugun) throws Exception;
	BaseAddrDto getAddrByDongCode(String dongCode) throws Exception;
}
