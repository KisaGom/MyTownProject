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
	private BaseAddrService baseAddrService;

	@GetMapping("/sido")
	public ResponseEntity<?> getSido() throws Exception{
		try {
			logger.debug("sido: {}",baseAddrService.getSido());
			return new ResponseEntity<List<BaseAddrDto>> (baseAddrService.getSido(), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<?> getGugun(@PathVariable String sido) {
		try {
			logger.debug("gugun: {}",baseAddrService.getSido());
			return new ResponseEntity<List<BaseAddrDto>> (baseAddrService.getGugun(sido), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<?> getDong(@PathVariable String gugun) {
		try {
			logger.debug("dong: {}",baseAddrService.getSido());
			return new ResponseEntity<List<BaseAddrDto>> (baseAddrService.getDong(gugun), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/base/{dongCode}")
	public ResponseEntity<?> getAddrByCode(@PathVariable String dongCode) {
		try {
			return new ResponseEntity<BaseAddrDto> (baseAddrService.getAddrByDongCode(dongCode), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
