package com.ssafy.mytown.controller.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mytown.model.dto.member.FavoriteDto;
import com.ssafy.mytown.model.service.member.FavoriteService;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
public class FavoriteController {

	public static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	
	@Autowired
	private FavoriteService favoriteService;
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> list(@PathVariable String userid){
		try {
			return new ResponseEntity<List<FavoriteDto>> (favoriteService.list(userid), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody FavoriteDto favoriteDto) {
		try {
			favoriteService.register(favoriteDto);
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{userid}/dongCode/{dongCode}")
	public ResponseEntity<?> insert(@PathVariable String userid, @PathVariable String dongCode) {
		try {
			favoriteService.delete(userid, dongCode);
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> (FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}