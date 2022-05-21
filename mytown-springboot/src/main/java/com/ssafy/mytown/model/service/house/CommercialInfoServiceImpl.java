package com.ssafy.mytown.model.service.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.house.CommercialInfoDto;
import com.ssafy.mytown.model.mapper.house.CommercialInfoMapper;

@Service
public class CommercialInfoServiceImpl implements CommercialInfoService {
	
	@Autowired
	private CommercialInfoMapper commercialInfoMapper;

	@Override
	public List<CommercialInfoDto> getComInfoDong(String dongCode) throws Exception {
		return commercialInfoMapper.getComInfoDong(dongCode);
	}

}
