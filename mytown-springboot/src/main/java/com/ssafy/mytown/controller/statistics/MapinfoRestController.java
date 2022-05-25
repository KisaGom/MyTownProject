package com.ssafy.mytown.controller.statistics;

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

import com.ssafy.mytown.model.dto.statistics.AgeAvgDto;
import com.ssafy.mytown.model.dto.statistics.AvgDealAmountDto;
import com.ssafy.mytown.model.dto.statistics.AvgRentAmountDto;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.dto.statistics.RentCountDto;
import com.ssafy.mytown.model.dto.statistics.TaxIncomeDto;
import com.ssafy.mytown.model.service.statistics.MapinfoSerivce;

@RestController
@RequestMapping("/mapi")
@CrossOrigin("*")
public class MapinfoRestController {

	public static final Logger logger = LoggerFactory.getLogger(MapinfoRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MapinfoSerivce mapinfoService;
	
	@GetMapping("/ada/{dongCode}")
	public ResponseEntity<?> getADA(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<AvgDealAmountDto> (mapinfoService.getADA(dongCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dc/{dongCode}")
	public ResponseEntity<?> getDC(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<DealCountDto > (mapinfoService.getDC(dongCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/amra/{dongCode}")
	public ResponseEntity<?> getAMRA(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<AvgRentAmountDto> (mapinfoService.getAMRA(dongCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/atra/{dongCode}")
	public ResponseEntity<?> getATRA(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<AvgRentAmountDto> (mapinfoService.getATRA(dongCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rc/{dongCode}")
	public ResponseEntity<?> getRC(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<RentCountDto> (mapinfoService.getRC(dongCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/aa/{dongCode}")
	public ResponseEntity<?> getAA(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<AgeAvgDto> (mapinfoService.getAA(dongCode), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
