package com.ssafy.mytown.model.service.statistics;

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.AvgDealAmountDto;
import com.ssafy.mytown.model.dto.statistics.AvgRentAmountDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;

public interface MapinfoSerivce {
	AvgDealAmountDto getADA(String dongCode) throws Exception;
	DealCountDto getDC(String dongCode) throws Exception;
	TaxIncomeDto getTC(String dongCode) throws Exception;
	AvgRentAmountDto getAMRA(String dongCode) throws Exception;
	AvgRentAmountDto getATRA(String dongCode) throws Exception;
	RentCountDto getRC(String dongCode) throws Exception;
	AgeAvgDto getAA(String dongCode) throws Exception;
}
