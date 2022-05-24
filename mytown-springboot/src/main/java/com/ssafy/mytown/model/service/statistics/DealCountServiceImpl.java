package com.ssafy.mytown.model.service.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.mapper.statistics.DealCountMapper;

@Service
public class DealCountServiceImpl implements DealCountService {

	@Autowired
	private DealCountMapper dealCountMapper;
	
	@Override
	public List<DealCountDto> list(String userid) throws Exception {
		return dealCountMapper.list(userid);
	}

}
