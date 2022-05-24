package com.ssafy.mytown.controller.statistics;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mytown.controller.member.FavoriteController;
import com.ssafy.mytown.model.dto.statistics.DealCountDto;
import com.ssafy.mytown.model.service.statistics.DealCountService;

@RestController
@RequestMapping("/dc")
public class DealCountRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(DealCountRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private DealCountService dealCountService;
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> list(@PathVariable String userid) {
		try {
			return new ResponseEntity<List<DealCountDto>> (dealCountService.list(userid), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
