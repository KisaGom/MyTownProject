package com.ssafy.mytown.model.service.house;

import java.util.List;

import com.ssafy.mytown.model.dto.house.CommercialInfoDto;

public interface CommercialInfoService {
	List<CommercialInfoDto> getComInfoDong(String dongCode) throws Exception;

}
