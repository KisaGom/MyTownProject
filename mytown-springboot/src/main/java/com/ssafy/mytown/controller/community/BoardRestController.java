package com.ssafy.mytown.controller.community;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mytown.controller.house.BaseAddrRestController;
import com.ssafy.mytown.model.dto.community.BoardDto;
import com.ssafy.mytown.model.service.community.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BoardRestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;
	
	//전체 게시물 리스트 반환
	@GetMapping()
	public ResponseEntity<?> retrieveBoard(){
		try {
			logger.debug("retrieveBoard 호출");
			return new ResponseEntity<List<BoardDto>>(boardService.retrieveBoard(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//해당 시도 게시물 리스트 반환
	@GetMapping("/sido/{sidoCode}")
	public ResponseEntity<?> retrieveSidoBoard(@PathVariable String sidoCode){
		try {
			logger.debug("retrieveSidoBoard 호출");
			return new ResponseEntity<List<BoardDto>>(boardService.retrieveSidoBoard(sidoCode),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//해당 구군 게시물 리스트 반환
	@GetMapping("/gugun/{gugunCode}")
	public ResponseEntity<?> retrievejGugunBoard(@PathVariable String gugunCode){
		try {
			logger.debug("retrieveBoard 호출");
			return new ResponseEntity<List<BoardDto>>(boardService.retrievejGugunBoard(gugunCode),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//해당 동 게시물 리스트 반환
	@GetMapping("/dong/{dongCode}")
	public ResponseEntity<?> retrieveDongBoard(@PathVariable String dongCode){
		try {
			logger.debug("retrieveBoard 호출");
			return new ResponseEntity<List<BoardDto>>(boardService.retrieveDongBoard(dongCode),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> detailBoard(@PathVariable int id){
		try {
			logger.debug("detailBoard 호출");
			return new ResponseEntity<BoardDto>(boardService.detailBoard(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> registBoard(@RequestBody BoardDto boardDto){
		logger.debug("registBoard 호출");
		if(boardService.registBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> modifyBoard(@RequestBody BoardDto boardDto){
		logger.debug("modifyBoard 호출");
		if(boardService.modifyBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBoard(@PathVariable int id){
		logger.debug("deleteBoard 호출");
		if(boardService.deleteBoard(id)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/like/{id}")
	public ResponseEntity<String> updateLike(@PathVariable int id){
		logger.debug("updateLike 호출");
		if(boardService.updateLike(id)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
