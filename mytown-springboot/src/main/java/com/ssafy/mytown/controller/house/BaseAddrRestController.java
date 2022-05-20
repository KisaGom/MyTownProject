package com.ssafy.mytown.controller.house;

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

import com.ssafy.mytown.model.dto.house.BaseAddrDto;
import com.ssafy.mytown.model.service.house.BaseAddrService;

@RestController
@RequestMapping("/addr")
@CrossOrigin("*")
public class BaseAddrRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAddrRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	BaseAddrService baseAddrService;

	@GetMapping("/sido")
	public ResponseEntity<?> getSido() {
		logger.info("getSido - 호출");
		try {
			return new ResponseEntity<List<BaseAddrDto>> (baseAddrService.getSido(), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<?> getGugun(@PathVariable String sido) {
		logger.info("getGugun - 호출");
		try {
			return new ResponseEntity<List<BaseAddrDto>> (baseAddrService.getSido(), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<?> getDong(@PathVariable String gugun) {
		logger.info("getDong - 호출");
		try {
			return new ResponseEntity<List<BaseAddrDto>> (baseAddrService.getSido(), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
