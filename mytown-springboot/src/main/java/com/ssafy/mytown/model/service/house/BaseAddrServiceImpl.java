package com.ssafy.mytown.model.service.house;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.house.BaseAddrDto;
import com.ssafy.mytown.model.mapper.house.BaseAddrMapper;

@Service
public class BaseAddrServiceImpl implements BaseAddrService {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAddrService.class);

	@Autowired
	BaseAddrMapper baseAddrMapper;
	
	@Override
	public List<BaseAddrDto> getSido() throws Exception {
//		logger.debug("sido service: {}",baseAddrMapper.getSido());
		return baseAddrMapper.getSido();
	}

	@Override
	public List<BaseAddrDto> getGugun(String sido) throws Exception {
		return baseAddrMapper.getGugun(sido);
	}

	@Override
	public List<BaseAddrDto> getDong(String gugun) throws Exception {
		return baseAddrMapper.getDong(gugun);
	}

	@Override
	public BaseAddrDto getAddrByDongCode(String dongCode) throws Exception {
		return baseAddrMapper.getAddrByDongCode(dongCode);
	}
}
