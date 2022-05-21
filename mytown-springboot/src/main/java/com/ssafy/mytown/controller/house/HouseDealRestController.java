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

import com.ssafy.mytown.model.dto.house.HouseDealDto;
import com.ssafy.mytown.model.service.house.HouseDealService;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseDealRestController {
	private static final Logger logger = LoggerFactory.getLogger(HouseDealRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private HouseDealService houseDealService;
	
	@GetMapping("/deal/{dongCode}")
	public ResponseEntity<?> getHouseDeal (@PathVariable String dongCode) throws Exception {
		return new ResponseEntity<List<HouseDealDto>> (houseDealService.getHouseDeal(dongCode), HttpStatus.OK);
	}
}
