package com.ssafy.mytown.controller.statistics;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mytown.controller.member.FavoriteController;
import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.AvgDealAmountDto;
import com.ssafy.mytown.model.dto.statistics.AvgRentAmountDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;
import com.ssafy.mytown.model.service.statistics.StatisticsService;

@RestController
@RequestMapping("/stat")
@CrossOrigin("*")
public class StatisticsRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(StatisticsRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private StatisticsService statisticsMapper;
	
	@GetMapping("/ada/{userid}")
	public ResponseEntity<?> adaList(@PathVariable String userid) {
		
		try {
			return new ResponseEntity<List<AvgDealAmountDto>> (statisticsMapper.adaList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dc/{userid}")
	public ResponseEntity<?> dcList(@PathVariable String userid) {
		
		try {
			return new ResponseEntity<List<DealCountDto>> (statisticsMapper.dcList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tc/{userid}")
	public ResponseEntity<?> tcList(@PathVariable String userid) {
		try {
			return new ResponseEntity<List<TaxIncomeDto>> (statisticsMapper.tcList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ara/total/{userid}")
	public ResponseEntity<?> araToList(@PathVariable String userid) {
		try {
			return new ResponseEntity<List<AvgRentAmountDto>> (statisticsMapper.araTotList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ara/monthly/{userid}")
	public ResponseEntity<?> araMonList(@PathVariable String userid) {
		try {
			return new ResponseEntity<List<AvgRentAmountDto>> (statisticsMapper.araMonList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rc/{userid}")
	public ResponseEntity<?> rcList(@PathVariable String userid) {
		try {
			return new ResponseEntity<List<RentCountDto>> (statisticsMapper.rcList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ag/{userid}")
	public ResponseEntity<?> agList(@PathVariable String userid) {
		try {
			return new ResponseEntity<List<AgeAvgDto>> (statisticsMapper.agList(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
