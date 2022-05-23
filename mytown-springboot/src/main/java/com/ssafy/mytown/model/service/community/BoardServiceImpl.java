package com.ssafy.mytown.model.service.community;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.mapper.community.BoardMapper;

@Service
public class BoardServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private BoardMapper boardMapper;
}
