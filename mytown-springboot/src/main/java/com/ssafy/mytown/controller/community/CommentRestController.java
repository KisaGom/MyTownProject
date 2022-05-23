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

import com.ssafy.mytown.model.dto.community.CommentDto;
import com.ssafy.mytown.model.service.community.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class CommentRestController {

	private static final Logger logger = LoggerFactory.getLogger(CommentRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("{board_id}")
	public ResponseEntity<?> retrieveComment(@PathVariable int board_id){
		try {
			logger.debug("retrieveComment 호출");
			return new ResponseEntity<List<CommentDto>>(commentService.retrieveComment(board_id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> registComment(@RequestBody CommentDto commentDto){
		logger.debug("registComment 호출");
		if(commentService.registComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> modifyComment(@RequestBody CommentDto commentDto){
		logger.debug("modifyComment 호출");
		if(commentService.modifyComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteComment(@PathVariable int id){
		logger.debug("deleteComment 호출");
		if(commentService.deleteComment(id)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
