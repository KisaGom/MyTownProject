package com.ssafy.mytown.model.service.statistics;

import java.util.List;

import com.ssafy.mytown.model.dto.statistics.DealCountDto;

public interface DealCountService {
	List<DealCountDto> list(String userid) throws Exception;

}
